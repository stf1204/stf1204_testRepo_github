package output

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**

 */
object SaveAsTextFileDemo {
  def main(args: Array[String]): Unit = {

    val streamingContext = new StreamingContext(master = "local[*]", appName = "SaveAsTextFileTest", Seconds(5))

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

    ds1.saveAsTextFiles("WordCount",".txt")

    streamingContext.start()
    streamingContext.awaitTermination()

  }
}
