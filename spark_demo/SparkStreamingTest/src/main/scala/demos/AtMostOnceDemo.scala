package demos

import org.apache.kafka.common.serialization.StringDeserializer
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
      "enable.auto.commit" -> "true")
  }
}
