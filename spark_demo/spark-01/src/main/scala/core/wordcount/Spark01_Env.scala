package core.wordcount

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark01_Env
  * @Author: stf
  * @Date: 2022/11/21 18:40
  * @Description:  Spark 连接环境
  */
object Spark01_Env {
  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("test")
    val sc = new SparkContext(conf)

    sc.stop()
  }

}
