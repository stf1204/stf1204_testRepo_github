package demos

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @ClassName: AtMostOnceDemo
 * @PACKAGE: demos
 * @Author: stf
 * @Date: 2022/12/27 - 23:00
 * @Description:
 * @Version: v1.0
 *
 */
object AtMostOnceDemo {

  def main(args: Array[String]): Unit = {

    val streamingContext = new StreamingContext(master = "local[*]", appName = "AtMOstOnceDemo", batchDuration = Seconds(5))

    /**
     * 所有Consumer参数都可以在ConsumerConfig中查看
     */
    val kafka = Map[String, Object](
      "bootstrap.servers" -> "hadoop102:9092,hadoop103:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "my_id",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> "true",
      // 设置自动提交的时间间隔，多久提交一次
    "auto.commit.interval.ms" -> "500"
    )

    val topic1 = "topicA"

    val ds1: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      PreferConsistent,
      Subscribe[String, String](Array(topic1), kafka)
    )

    ds1.map(record=>{
      Thread.sleep(500)

      if (record.value().equals("B")){
        throw new RuntimeException("异常程序")
      }
        record.value()
    }).print(1000)

    streamingContext.start()
    streamingContext.awaitTermination()
  }
}
