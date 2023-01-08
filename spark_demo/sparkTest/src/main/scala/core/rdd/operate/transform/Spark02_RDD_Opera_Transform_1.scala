package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: Map 转换算子
  */
object Spark02_RDD_Opera_Transform_1 {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)

    val rdd1: RDD[Int] = sc.makeRDD(List(1, 2, 4, 6))

    // 至简原则
    val newrdd: RDD[Int] = rdd1.map(_ * 2)
    newrdd.collect().foreach(println)
    sc.stop()
  }

}
