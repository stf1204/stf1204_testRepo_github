package demos

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * 无状态的计算：
 * 每个批次之间是独立的，各算个的。
 * 有状态的计算：
 * 当前批次的计算需要使用上一次计算的结果。没人用
 * DStream.XXXStateXXX() SparkStreaming提供的有状态的计算的算子
 * 弊端：会定期生成小文件写入HDFS。
 *
 * ---------------------------------------------------------------------
 * select
 * a,XXX(b)    ----------------UDF(输入一行输出一行) map
 *
 * from XXX
 * where XXX      ----------------------filter
 * group by       ----------------------reduceByKey | groupByKey
 * order by       ----------------------sortBy | sortByKey
 * limit XXX      ----------------------take(x)
 *
 * -------------------------------------------------------------------
 * transform:
 * (transformFunc: RDD[T] => RDD[U]): DStream[U]
 * 将DStream[T] 中的RDD[T]取出来，进行运算，然后再调用 transformFunc，转换为 RDD[U],再封装为DStream[U]
 *
 * 算子：    RDD.map RDD.filter
 * 抽象原语： DStream.map  DStream.filter
 *
 * 算子远比抽象原语丰富，有些方法只有算子有，如果要用此类方法，就需要进行类型转换，转为RDD调用该方法，然后再封装为DStream
 *
 */
object TransformDemo {
  def main(args: Array[String]): Unit = {

    val streamingContext = new StreamingContext(master = "local[*]", appName = "TransformTest", Seconds(10))

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
    val ds1: DStream[(String, Int)] = ds.flatMap(words => words.value().split(" ")).map((_, 1)).reduceByKey(_ + _)

    val ds2: DStream[(String, Int)] = ds1.transform(word => word.sortByKey())

    ds2.print(1000)

    streamingContext.start()
    streamingContext.awaitTermination()

  }
}
