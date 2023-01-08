package demos

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.{CanCommitOffsets, HasOffsetRanges, KafkaUtils, OffsetRange}
import utils.JDBCUtil

import java.sql.{Connection, PreparedStatement, ResultSet}
import scala.collection.mutable

/**

//获取起始偏移量
//begin from the offsets committed to the database
val fromOffsets = selectOffsetsFromYourDatabase.map { resultSet =>
  new TopicPartition(resultSet.string("topic"), resultSet.int("partition")) -> resultSet.long("offset")
}.toMap

val streamingContext = KafkaUtils.createDirectStream[String, String](
  streamingContext,
  PreferConsistent,
  Assign[String, String](fromOffsets.keys.toList, kafkaParams, fromOffsets)
)

streamingContext.foreachRDD { rdd =>
  val Ranges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges

  val results = yourCalculation(rdd)

  // begin your transaction

  // update results
  // update offsets where the end of existing offsets matches the beginning of this batch of offsets
  // assert that offsets were updated correctly

  // end your transaction
}

          ①查询之前写入数据库的偏移量 Offsets
          ②从Offsets位置获取一个流
          ③自己的运算，得到结果
          ④在一个事务当中，把结果和偏移量写入数据库中

  以wordCount为例：
      设计MYSQL中如何存数据，offsets
  数据：
      粒度：一个word是一行
      主键：word

  offset： groupId，topic，partitionId ----> offset
      粒度：一个组消费一个主题的一个分区是一行
      主键：(groupId，topic，partitionId)
 */
object ExactlyOnceTransactionDemo {

  val groupId = "my_id"
  val topic = "topicA"

  //查询mysql中已经存储的偏移量
def selectOffsetFromMysql(groupId:String,topic:String):Map[TopicPartition, Long]={

  // 用来存储查询到的偏移量
  val offsets= new mutable.HashMap[TopicPartition, Long]()
  var connection: Connection = null
  var ps: PreparedStatement = null
  val sql =
    """
      |select
      |partitionId,offset
      |from offset
      |where groupId=? and topic = ?
      |
      |""".stripMargin

    try {
      // 1 连接对象
      connection = JDBCUtil.getConnection()
      // 2 预编译
      ps = connection.prepareStatement(sql)
      // 3 填充占位符
      ps.setString(1,groupId)
      ps.setString(2,topic)

      // 4 执行语句
      val resultSet: ResultSet = ps.executeQuery()
      while (resultSet.next()){
        // Map( TopicPartition(String topic, int partition) , Long offset )
        offsets.put(new TopicPartition(topic , resultSet.getInt("partitionId")),
          resultSet.getLong("offset"))
      }
    }catch {
      case e:Exception=>{
        e.printStackTrace()
        throw new RuntimeException("查询偏移量失败！")
      }
    }finally {
     // 关闭资源
      if(connection!=null){
        connection.close()
        }
      if(ps!=null){
        ps.close()
      }
    }

  //可变转不可变
  offsets.toMap
}

  // 在同一个事务中写出 result 和 ranges
  def WriteResultAndOffsetInCommonTransaction(result: Array[(String, Int)], ranges: Array[OffsetRange]): Unit = {

    //写单词
    // 累加，将数据库中相同的单词的数量和我现在要写入的数量累加，再写入
    val sql1 =
      """
        |insert into `wordcount` Values(?,?)
        |on duplicate key update count=count+values(count)
        |""".stripMargin

    //写偏移量
    val sql2 =
      """
        |insert into `offset` Values(?,?,?,?)
        |on duplicate key update offset=values(offset)
        |""".stripMargin
    val sql3 =
      """
        |replace into `offset` Values(?,?,?,?)
        |""".stripMargin


    val offsets = new mutable.HashMap[TopicPartition, Long]()
    var connection: Connection = null
    var ps1: PreparedStatement = null
    var ps2: PreparedStatement = null

    try {
      // 1、获取连接对象
      connection = JDBCUtil.getConnection()

      // 2、开启事务 取消事务的自动提交，改为手动提交
      connection.setAutoCommit(false)

      // 3、预编译
      ps1 = connection.prepareStatement(sql1)
      ps2 = connection.prepareStatement(sql2)

      // 4、遍历数据数据，每遍历一个单词，生成一条sql，攒起来，统一执行
      for ((word, cnt) <- result) {
        ps1.setString(1,word)
        ps1.setLong(2,cnt)

        // 攒起来
        ps1.addBatch()
      }

      // 5、遍历ranges，每个offset，生成一条sql，攒起来，统一执行
      for (offsetRange <- ranges) {
        ps2.setString(1,groupId)
        ps2.setString(2,topic)
        ps2.setInt(3,offsetRange.partition)
        ps2.setLong(4,offsetRange.untilOffset)

        // 攒起来
        ps2.addBatch()
      }

      // 6、统一执行
      val res1: Array[Int] = ps1.executeBatch()
      val res2: Array[Int] = ps2.executeBatch()

      // 7、执行完毕，提交事务
      connection.commit()

      println("数据写入："+res1.size)
      println("偏移量写入："+res2.size)

    }catch {
      case e:Exception=>{
        //  出现错误，回滚事务
        connection.rollback()
        e.printStackTrace()
        throw new RuntimeException("写入失败！")
      }
    }finally {

      // 关闭资源
      if(ps1!=null){
        ps1.close()
      }

      if(ps2!=null){
        ps2.close()
      }
      if(connection!=null){
        connection.close()
      }
    }
  }

  def main(args: Array[String]): Unit = {

    // 第一步、①查询偏移量
    val offsetsMap: Map[TopicPartition, Long] = selectOffsetFromMysql(groupId, topic)

    val streamingContext = new StreamingContext(master = "local[*]", appName = "ExactlyOnceTransactionDemo", batchDuration = Seconds(5))

    val kafka = Map[String, Object](
      "bootstrap.servers" -> "hadoop102:9092,hadoop103:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> groupId,
      "auto.offset.reset" -> "latest",
      // 关闭自动提交
      "enable.auto.commit" -> "false"
    )

    //第二步、从查询到的偏移量，向后消费
    val ds1: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      PreferConsistent,
      Subscribe[String, String](Array(topic), kafka,offsetsMap)
    )

    // foreachRDD
    ds1.foreachRDD(rdd=>{

      if (!rdd.isEmpty()){
        //第三步、获取每个RDD的偏移量---driver端
        val ranges: Array[OffsetRange] = rdd.asInstanceOf[HasOffsetRanges].offsetRanges

        // 第四步、转换运算
        val result: Array[(String, Int)] = rdd.flatMap(record =>
          record.value().split(" ") )
          .map((_, 1))
          .reduceByKey(_ + _)
          .collect()

        // 第五步：将result和ranges在一个事务中写出
        WriteResultAndOffsetInCommonTransaction(result,ranges)
      }

    })
    streamingContext.start()
    streamingContext.awaitTermination()
  }
}
