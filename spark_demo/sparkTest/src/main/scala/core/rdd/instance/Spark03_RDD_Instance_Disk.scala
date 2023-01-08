package core.rdd.instance

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark01_RDD_Instance
  * @Author: stf
  * @Date: 2022/11/22 21:25
  * @Description: Spark中创建Rdd方式2
  */
object Spark03_RDD_Instance_Disk {

  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("test")
    val sc = new SparkContext(conf)

    // TODO 2、通过环境对象帮我们构建数据模型

    //    val value: RDD[String] = sc.textFile("data/words.txt")
    //    val value: RDD[String] = sc.textFile("data/words.txt,data/word.txt")
    //    val value: RDD[String] = sc.textFile("data")
    val value: RDD[String] = sc.textFile("data/word*.txt")

    value.collect().foreach(println)

    sc.stop()
  }
}
