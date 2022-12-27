package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: coalesce 算子     缩减分区
  */
object Spark09_RDD_Opera_Transform {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)


    // TODO 缩减分区使用 coalesce算子
    // TODO 增加分区使用 reParations算子 底层调用coalesce算子，打开shuffle功能
    // coalesce 默认不打开shuffle功能，可能会导致数据倾斜
    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 6, 1, 3, 7, 9, 8), 3)
    rdd.coalesce(2, true).saveAsTextFile("output")
    sc.stop()

  }

}
