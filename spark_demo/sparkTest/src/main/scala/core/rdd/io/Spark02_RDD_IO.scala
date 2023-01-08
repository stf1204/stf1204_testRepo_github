package core.rdd.io

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark01_RDD_Instance
  * @Author: stf
  * @Date: 2022/11/22 21:25
  * @Description: IO
  */
object Spark02_RDD_IO {

  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("test")
    val sc = new SparkContext(conf)


    // TODO 读取文件
    println(sc.textFile("output").collect().mkString(","))
    println(sc.sequenceFile[String, Int]("output1").collect().mkString(","))
    println(sc.objectFile[(String, Int)]("output2").collect().mkString(","))


    sc.stop()
  }
}
