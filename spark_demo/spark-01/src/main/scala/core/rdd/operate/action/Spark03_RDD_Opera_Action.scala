package core.rdd.operate.action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark01_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:43
  * @Description: RDD Action算子 (简单算子) count、take、takeOrdered、first
  */
object Spark03_RDD_Opera_Action {


  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("test2")
    val sc = new SparkContext(conf)

    val rdd1: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5, 9), 2)

    // collect:结果返回是一个数组
    // 不传参默认是全采集， 有参数是一个偏函数，是转换算子
    // 存储在driver端的内存中

//    rdd1.collect()
//    val cnt: Long = rdd1.count()
//    val ints: Array[Int] = rdd1.take(3)
//    val arr: Array[Int] = rdd1.takeOrdered(3)
//    val first_value: Int = rdd1.first()

  }
}
