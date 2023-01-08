package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: partitionBy算子  分区
  */
object Spark13_RDD_Opera_Transform {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)

    val rdd1: RDD[Int] = sc.makeRDD(List(1, 4, 3, 6), 2)


    // TODO 分区 partitionBy

    // 只有kv类型才能进行隐式转换
    //    rdd1.partitionBy  // error

    // 二次编译： 可能隐式转换, 因为泛型不可变，只有相同的泛型才会传递过去，进行隐式转换
    rdd1.map((_, 1)).partitionBy(null)

    sc.stop()

  }

}
