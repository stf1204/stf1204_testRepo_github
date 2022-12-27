package output

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import redis.clients.jedis.Jedis

/**
 * @ClassName: WriteToRedisDemo
 * @PACKAGE: output
 * @Author: stf
 * @Date: 2022/12/27 - 15:06
 * @Description:
 * @Version: v1.0
 *
 */
object WriteToRedisDemo {


  /**
   * 把运算结果写入到Redis中
   *    用String存：
   *          一个单词就是一个key
   *    用Hash存：
   *          Key：wordCount
   *          Value：hash
   *                【field,value】
   *                word, count
   *  -----------------------------------------------------------------------
   *  def foreachRDD(foreachFunc:(RDD[T])=>Unit):Unit
   *  ds2.foreachRDD 和 transform类似，都是将DStream运算转换为RDD运算
   *
   *  foreachFunc：没有返回值的函数
   *  foreachRDD：没有返回值。
   *
   *  ----------------------------------------------------------------------
   *
   *  以partition为单位的操作(可以减少创建Connection的次数)
   *
   *  读数据库：
   *          RDD.mapPartition  有返回值
   *  写数据库：
   *          RDD.foreachPartition    没有返回值
   *
   * @param args
   */
  def main(args: Array[String]): Unit = {

    val streamingContext = new StreamingContext(master = "local[*]", appName = "WriteToRedisDemo", Seconds(5))

    val kafka = Map[String, Object](
      "bootstrap.servers" -> "hadoop102:9092,hadoop103:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "my_id",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> "true"
    )

    val topics = Array("topicA")

    val ds: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      PreferConsistent,
      Subscribe[String, String](topics, kafka)
    )
    //    (String, Int):(word,n)
    val ds1: DStream[(String, Int)] = ds.flatMap(words => words.value().split(" "))
      .map((_, 1))
      .reduceByKey(_ + _)

//    ds1.saveAsTextFiles("WordCount",".txt")
    ds1.cache()
    ds1.print(1000)
//    ds2.foreachRDD 和 transform类似，都是将DStream运算转换为RDD运算
    ds1.foreachRDD{
//      以partition为单位的操作(可以减少创建Connection的次数)
      rdd=>rdd.foreachPartition{partition=>{
        // 一个分区创建一个链接
        val jedis = new Jedis("hadoop102", 6379)
        //使用链接获取每个分区内的数据
       partition.foreach {
         case (word, cnt) => {
           val oldCnt = jedis.hget("wordCount", word)
           if (oldCnt == null)
             jedis.hset("wordCount", word, cnt.toString)
           else
             jedis.hset("wordCount", word, (cnt + oldCnt.toInt).toString)
         }
       }
      }
      }
    }

    streamingContext.start()
    streamingContext.awaitTermination()

  }
}
