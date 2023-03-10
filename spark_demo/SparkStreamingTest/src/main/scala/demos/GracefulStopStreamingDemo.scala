package demos

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.{Seconds, StreamingContext, StreamingContextState}

import java.net.URI

object TransformDemo{

  def main(args: Array[String]): Unit = {

    //1.初始化Spark配置信息
    val sparkconf = new SparkConf().setMaster("local[*]").setAppName("sparkStreaming")

    // 设置优雅的关闭
    sparkconf.set("spark.streaming.stopGracefullyOnShutdown", "true")

    //2.初始化SparkStreamingContext
    val ssc: StreamingContext = new StreamingContext(sparkconf, Seconds(3))

    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "hadoop102:9092,hadoop103:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "sz211125",
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> "true"
    )
    val topics = Array("topicA")
    val stream = KafkaUtils.createDirectStream[String, String](
      ssc,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )

    stream.map(_.value()).print()


    // 开启监控程序
    new Thread(new MonitorStop(ssc)).start()

    ssc.start()
    ssc.awaitTermination()
  }
}

// 监控程序
class MonitorStop(ssc: StreamingContext) extends Runnable{

  override def run(): Unit = {
    // 4.1 获取HDFS文件系统
    val fs: FileSystem = FileSystem.get(new URI("hdfs://hadoop102:8020"),new Configuration(),"atguigu")

    // 4.2 死循环判断是否需要关闭
    while (true){
      Thread.sleep(5000)
      // 4.3 判断/stopSpark路径是否存在
      val result: Boolean = fs.exists(new Path("hdfs://hadoop102:8020/stopSpark"))

      if (result){
        val state: StreamingContextState = ssc.getState()
        // 获取当前任务是否正在运行
        if (state == StreamingContextState.ACTIVE){
          // 优雅关闭
          ssc.stop( true, true)
          System.exit(0)
        }
      }
    }
  }
}