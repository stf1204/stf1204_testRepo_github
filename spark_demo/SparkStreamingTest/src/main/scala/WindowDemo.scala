import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @ClassName: TransformDemo
 * @PACKAGE:
 * @Author: stf
 * @Date: 2022/12/27 - 11:12
 * @Description:
 * @Version: v1.0
 *
 */

/**
 *  再SparkStreaming中有三个与时间操作有关的函数::
 *
 *      batchDuration：Duration    将多长时间消费的数据封装为一个RDD
 *                                 一个批次的采集时间间隔
 *      window：       Duration    要计算的时间范围
 *
 *      slide：        Duration    多久提交一次Job运算
 *
 *      batchDuration 在构造 StreamingContext 时候必须指定。
 *            默认情况下，window 和 slide 等于 batchDuration
 *            今天上午的所有操作都是 消费10s的数据作为一个batch，每间隔10s，提交一个job，每个job算 过去10s的数据。
 *
 *  --------------------------------------------------------------------
 *
 *  在获取流的任意位置 调用window()
 *
 *
 *  --------------------------------------------------------------------
 *
 *   object not serializable(class: org.apache.kafka.clients.consumer.ConsumerRecord)
 *   解决办法：
 *          方法一：
 *                不要在获取了初始的ds：InputStream[ConsumerRecord]之后立即调用window
 *                而应该先取出 ConsumerRecord中的value，之后再window
 *          方法二：
 *                将ConsumerRecord使用kryo，进行序列化
 */
object WindowDemo {
  def main(args: Array[String]): Unit = {

    val streamingContext = new StreamingContext(master = "local[*]", appName = "WindowTest", Seconds(5))

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

    // windowDuration: Duration, slideDuration: Duration
    // 计算的时间窗口是10，每隔15秒提交一次job，计算10秒的数据
    val ds2 = ds1.window(Seconds(10), Seconds(15))

    ds2.print(1000)

    streamingContext.start()
    streamingContext.awaitTermination()

  }
}
