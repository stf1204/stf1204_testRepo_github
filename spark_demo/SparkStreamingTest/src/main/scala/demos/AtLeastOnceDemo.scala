package demos

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.{CanCommitOffsets, HasOffsetRanges, KafkaUtils, OffsetRange}
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
object AtLeastOnceDemo {

  /**
   * 如何区分代码在Driver端还是Executor端？
   *
   *        ①：如果是DStream的普通(RDD也有的，同名)算子 例如： map,filter等，都是在Executor端运行
   *        ②：特殊算子：
   *                Transform 和foreachRDD
   *                只有RDD.算子（XXX） XXX是在Executor端运行，其他的都是在Driver端运行
   *  ----------------------------------------------------------------------------
   *      local[*] 本地模式以多线程模拟分布式运算。
   *      Executor的线程统一命名为：Executor task launch work for task id
   *      只要不是以以上的线程命名，都是Driver端
   * ----------------------------------------------------------------------------
   *      OffsetRange(topic: "topicA", partition:2, range:[62->62])
   *      一个OffsetRange代表消费一个主题的一个分区，当前批次拉取到的数据的起始Offset和终止Offset
   *      关注Until Offset
   * ----------------------------------------------------------------------------
   *  结论：
   *      偏移量offsets是在Driver端获取！！
   */
  def main(args: Array[String]): Unit = {

    val streamingContext = new StreamingContext(master = "local[*]", appName = "AtMOstOnceDemo", batchDuration = Seconds(5))

    val kafka = Map[String, Object](
      "bootstrap.servers" -> "hadoop102:9092,hadoop103:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "my_id",
      "auto.offset.reset" -> "latest",
      // 关闭自动提交
      "enable.auto.commit" -> "false",
      // 设置自动提交的时间间隔，多久提交一次
    "auto.commit.interval.ms" -> "500"
    )

    val topic1 = "topicA"

    val ds1: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      PreferConsistent,
      Subscribe[String, String](Array(topic1), kafka)
    )


    // Driver端声明
    var ranges: Array[OffsetRange]=null
    // transform
    val ds2: DStream[ConsumerRecord[String, String]] = ds1.transform {
      rdd => {
        //Driver端运行
        println("test1:"+Thread.currentThread().getName)

        //偏移量
        ranges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges

        ranges.foreach(println(_))
        rdd
      }
    }

    ds2.map(record=>{
      Thread.sleep(500)
      if (record.value().equals("B")){
        throw new RuntimeException("异常程序")
      }
      // DStream 对象
      record.value()
    }).foreachRDD{
      // DS中每个RDD
      rdd=>{
        // Executor端运行
        rdd.foreach(word=>
        println(Thread.currentThread().getName+":"+word))
        println("test2:"+Thread.currentThread().getName)

        //Driver端提交
       ds1.asInstanceOf[CanCommitOffsets].commitAsync(ranges)
      }
    }
    streamingContext.start()
    streamingContext.awaitTermination()
  }
}
