package demos

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.{Seconds, StreamingContext}

object JoinDemo {

  /**
   * 消费多个主题，将多个主题的数据进行关联。
   * 每个主题一个流
   * 只有DS[K,V]才能关联。
   * ----------------------------------------------------------------
   *
   * DS[K,V1] join DS[K,V2] = DS[K,(v1,v2)]
   *
   * -----------------------------------------------------------------
   * Option: 有  Some
   *         没有 none
   */
  def main(args: Array[String]): Unit = {

    val streamingContext = new StreamingContext(master = "local[*]", appName = "JoinDemo", Seconds(10))

    val kafka = Map[String, Object](
      "bootstrap.servers" -> "hadoop102:9092,hadoop103:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "my_id",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> "true")

    val topic1 = "topicA"
    val topic2 = "topicB"


    val ds1: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      PreferConsistent,
      Subscribe[String, String](Array(topic1), kafka)
    )
    val ds2: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      PreferConsistent,
      Subscribe[String, String](Array(topic2), kafka)
    )

    val ds3: DStream[(String, Int)] = ds1.map(_.value()).map((_, 1))
    val ds4: DStream[(String, Int)] = ds2.map(_.value()).map((_, 2))

    // join
//    val ds5: DStream[(String, (Int, Int))] = ds3.join(ds4)

    // leftOuterJoin
    // Option-->(None && Some)
//    val ds5: DStream[(String, (Int, Option[Int]))] = ds3.leftOuterJoin(ds4)

    // rightOuterJoin
//    val ds5: DStream[(String, (Option[Int], Int))] = ds3.rightOuterJoin(ds4)

//    fullOuterJoin
    val ds5: DStream[(String, (Option[Int], Option[Int]))] = ds3.fullOuterJoin(ds4)
    ds5.print(1000)

    streamingContext.start()
    streamingContext.awaitTermination()
  }
}
