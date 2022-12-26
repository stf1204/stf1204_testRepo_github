package core.rdd.io

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark01_RDD_Instance
  * @Author: stf
  * @Date: 2022/11/22 21:25
  * @Description: IO
  */
object Spark01_RDD_IO{

  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("test")
    val sc = new SparkContext(conf)

    val RDD1: RDD[(String, Int)] = sc.makeRDD(List(
      ("a", 1), ("b", 2)
    ),2
    )
    // TODO 存储文件
    RDD1.saveAsTextFile("output")
    RDD1.saveAsObjectFile("output2")
    RDD1.saveAsSequenceFile("output1")


    sc.stop()
  }
}
