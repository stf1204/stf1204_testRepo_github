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
object CommitOffsetDemo {

  /**
   *    org.apache.spark.streaming.DStream.MappedDStream
   *    cannot be cast to
   *    org.apache.spark.streaming.kafka010.CanCommitOffsets
   *
   * 结论：
   *      只有初始DS才能提交偏移量！只有初始DS是DirectKafkaInputStream，
   *      只有DirectKafkaInputStream才能asInstanceOf[HasOffsetRanges]
   */
  def main(args: Array[String]): Unit = {

    val streamingContext = new StreamingContext(master = "local[*]", appName = "CommitOffsetDemo", batchDuration = Seconds(5))

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

    //DirectKafkaInputStream 会每隔10s， 将采集的数据封装为KafkaRDD
    val ds1: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      PreferConsistent,
      Subscribe[String, String](Array(topic1), kafka)
    )

    // Driver端声明
    var ranges: Array[OffsetRange]=null
    val ds3: DStream[ConsumerRecord[String, String]] = ds1.transform {
      rdd => {
        //偏移量
        ranges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
        rdd
      }
    }

    val ds2: DStream[String] = ds3.map(record => record.value())

    ds2.foreachRDD{
      rdd=>{
        rdd.foreach(word=>println(Thread.currentThread().getName+":"+word))

       ds1.asInstanceOf[CanCommitOffsets].commitAsync(ranges)
      }
    }

    streamingContext.start()
    streamingContext.awaitTermination()
  }
}
