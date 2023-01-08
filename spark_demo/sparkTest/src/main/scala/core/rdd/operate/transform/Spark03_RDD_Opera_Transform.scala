package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: mapPartitions算子  分区map执行
  */
object Spark03_RDD_Opera_Transform {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)

    val rdd1: RDD[Int] = sc.makeRDD(List(1, 2, 4, 6))

    // mapPartitions: 用于将一个分区的数据统一进行转换映射
    // 分区数据可能会很多 - 数据倾斜， 内存不一定放的下，mapPartitions 可能导致内存溢出
    // mapPartitions ： 效率较高， 依赖内存大小
    // 内存足够的情况下，可以使用
    val RDD2: RDD[Int] = rdd1.mapPartitions { list =>
      println("===============> ")
      list.map(_ * 2)
    }

    RDD2.collect().foreach(println)
    sc.stop()

  }

}
