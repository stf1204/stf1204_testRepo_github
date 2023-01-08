package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: Map算子 转换算子
  */
object Spark02_RDD_Opera_Transform {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)

    val rdd1: RDD[Int] = sc.makeRDD(List(1, 2, 4, 6))

    def mapFunc(num: Int): Int = {
      num * 2
    }

    // 函数式编程中，重要的是--函数的名称，函数的参数，函数的返回值
    // map算子需要传递一个参数，参数的类型为函数类型： Int => U
    // map算子会将数据集中的每一条数据执行函数逻辑，将执行后的结果返回。默认类型不变
    val newrdd: RDD[Int] = rdd1.map(mapFunc)
    newrdd.collect().foreach(println)
    sc.stop()
  }

}
