import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
/**
 * @ClassName: WordCountDemo
 * @PACKAGE:
 * @Author: stf
 * @Date: 2022/12/26 - 20:49
 * @Description:
 * @Version: v1.0
 *
 */

/**
 *  编程入口(上下文Context)和编程模型：
 *
 *  编程入口：
 *        SparkCore：SparkContext
 *        SparkSql：SparkSession(内置SparkContext)
 *        SparkStreaming：StreamingContext(内置SparkContext)
 *
 *  编程模型：
 *        SparkCore：RDD
 *        SparkSql：DataFrame & DataSet
 *        SparkStreaming： DStream
 *
 *  StreamingContext：编程的核心入口。用来从多种数据源创建DStream
 *
 *    ① 创建StreamingContext
 *    ②从StreamingContext中获取DStream
 *    ③调用DStream的算子(高度抽象原语) 计算
 *    ④ 以上三步都是懒加载
 *        启动APP
 *        StreamingContext.start()
 *        停止：
 *        StreamingContext.stop()
 *
 *        流式计算启动后一定24小时不间断执行
 *                StreamingContext.awaitTermination()等待发停止信号 或者 出现异常停止
 *
 */
object WordCountDemo {
  def main(args: Array[String]): Unit = {

    /**
     * batchDuration: Duration: 一个批次的持续时间。
     *                          多久为一批数据。
     *                          Milliseconds(毫秒级)
     *                          Seconds(秒级别)
     *                          Minutes(分钟级别)
     */


    /*
    val sparkConf = new SparkConf().setAppName("WordCount").setMaster("local[*]")
    val sparkContext = new SparkContext(sparkConf)
    val streamingContext = new StreamingContext(sparkContext, Seconds(10))
*/

//    val streamingContext = new StreamingContext(sparkConf, Seconds(10))

    //① 创建StreamingContext
    val streamingContext = new StreamingContext(master = "local[*]", appName = "WordCount", Seconds(10))
    /*// 如果想要获取其中的SparkContext
    val context: SparkContext = streamingContext.sparkContext*/

    //②从StreamingContext中获取DStream
    //    参考数据源（hdfs，kafka，Tcp Port）
    //    fileStream 流式读取HDFS的文件
    //    socketStream 流式读取 固定主机：Port下发送的数据


    /**
     * APP扮演的是消费者的角色，有消费者参数
     * 必须有：
     *      bootstrap.servers
     *      key.deserializer
     *      value.deserializer
     *      group.id
     *
     *
     *      auto.offset.reset 从哪个位置开始消费
     *            earliest：如果没有消费过主题，从主题的最早的位置消费
     *            latest：如果没有消费过主题，从主题的最后(当前，最新)位置消费
     *            none：从earliest 转为none，从已经提交的offset后 继续消费
     *
     *      enable.auto.commit 是否允许consumer自动提交offset
     *                          kafka 0.10版本以后，叫offset提交到_consumer_offset中
     */
    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "hadoop102:9092,hadoop103:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "my_id",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> "true"
    )


    /**
     * 要消费的主题
     *  理论上 一个流可以消费多个主题
     *  但是实际使用时，一个流只写一个主题
     *        原因： 不同主题中保存的数据是不一样的，如果一个流消费了不同的主题，流中混杂了两种数据，在处理时，需要对数据进行类型判断，
     *              判断是否是我们要处理的类型，编程逻辑难维护，杂乱。
     *  如果需要消费两个主题，应该一个主题一个流，每个流中只保存一种类型的数据。
     *
     */
    val topics = Array("topicA")


    /**
     * 如何从Kafka数据源中获取DStream
     * 全部是固定代码！！！！！！！
     *
     *
     * def createDirectStream[K,V](
     *   ssc: StreamingContext   程序入口
     *   locationStrategy:   位置策略：
     *                          kafka的broker和SparkApp Executor 的位置关系
     *                          (是不是同一机器，同一机架，同一机房)
     *                          调度Task到Executor，有本地化策略(任务移动而不是数据移动)
     *
     *                          如果当前消费的Topic的0号分区的leader位于102机器上，App恰好在02启动了Executor，那么这个Task就应该调度给这个Executor
     *                          而不是交给其他的Executor。
     *
     *                          99%都是 PreferConsistent
     *
     *   consumerStrategy:    消费策略：
     *                          独立消费者：明确指定要消费哪个主题的那个分区，从哪个offset开始消费
     *                                    Assign
     *
     *                          非独立消费者：告诉你要消费哪个主题，由kafka自动给分配消费分区，读取之前保存的offset，从该位置开始消费
     *                                    Subscribe(99%)
     *
     *
     *      ConsumerRecord[K，V]   从kafka消费到的一条数据，只获取V
     *
     *      ProducerRecord[K，V]   V 封装data
     *                             K 封装meta data 用于分区等
     *                                  partition=0
     * )
     */
    val ds: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      streamingContext,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )

    val ds1 = ds.map(record => record.value())

    // ds2中的[String]是一个单词
    val ds2:DStream[String] = ds1.flatMap(line => line.split(" "))

    val ds3: DStream[(String, Int)] = ds2.map(word => (word, 1)).reduceByKey(_ + _)

    // 输出，在屏幕打印，默认print()只打印前10行
    ds3.print(1000)



    //启动App
    streamingContext.start()

    // 阻塞进程，让进程一直运行
    streamingContext.awaitTermination()
  }
}
