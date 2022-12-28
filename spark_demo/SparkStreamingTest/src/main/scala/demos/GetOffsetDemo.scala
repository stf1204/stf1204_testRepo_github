package demos

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.{CanCommitOffsets, HasOffsetRanges, KafkaUtils, OffsetRange}
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
object GetOffsetDemo {


  def main(args: Array[String]): Unit = {

    val streamingContext = new StreamingContext(master = "local[*]", appName = "GetOffsetDemo", batchDuration = Seconds(5))

    /**
     * 所有Consumer参数都可以在ConsumerConfig中查看
     */
    val kafka = Map[String, Object](
      "bootstrap.servers" -> "hadoop102:9092,hadoop103:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "my_id",
      "auto.offset.reset" -> "latest",
      // 关闭自动提交
      "enable.auto.commit" -> "false"
    )

    val topic1 = "topicA"

    val ds1: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      PreferConsistent,
      Subscribe[String, String](Array(topic1), kafka)
    )

    // Driver端声明
    var ranges: Array[OffsetRange]=null
    val ds2: DStream[ConsumerRecord[String, String]] = ds1.transform {
      rdd => {
        //偏移量
        ranges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
        rdd
      }
    }

    ds2.map(record=>{
        record.value()
    }).foreachRDD{
      rdd=>{
        rdd.foreach(word=>println(word))
        //Driver端提交
       ds1.asInstanceOf[CanCommitOffsets].commitAsync(ranges)
      }
    }

    streamingContext.start()
    streamingContext.awaitTermination()
  }
}
