package core.rdd.instance

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Instance_Memory_Partition
  * @Author: stf
  * @Date: 2022/11/23 14:54
  * @Description: Spark 指定分区个数
  */
object Spark02_RDD_Instance_Memory_Partition_Data {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
      .setAppName("test1...")
      .setMaster("local[*]")
//      .set("spark.default.parallelism", "4")
    val sc = new SparkContext(conf)

    // 【1，2】 【5，3】
    // 【1，2】 【5，3，4】
    // 【1】 【2，5】 【3，4】
    val rdd1: RDD[Int] = sc.makeRDD(List(1, 2, 5, 3, 4), 3)

    rdd1.saveAsTextFile("output")
    sc.stop()
  }
}
