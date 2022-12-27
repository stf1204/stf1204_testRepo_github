package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: mapPartitions 可以改变数据量的大小
  */
object Spark03_RDD_Opera_Transform_1 {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)

    val rdd1: RDD[Int] = sc.makeRDD(List(1, 2, 4, 6))

    // map的处理逻辑是一对一

    // mapPartitions 的处理逻辑是一个分区一个分区的处理
    // 可以改变数据的个数
    val RDD3: RDD[Nothing] = rdd1.mapPartitions(iter => Nil.iterator)

    sc.stop()

  }

}
