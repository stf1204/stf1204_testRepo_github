package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: repartition 算子     增加分区
  */
object Spark10_RDD_Opera_Transform {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)


    // TODO 缩减分区使用 coalesce算子
    // TODO 增加分区使用 reparation算子 底层调用coalesce算子，打开shuffle功能

    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 6, 1, 3, 7, 9, 8), 3)
    rdd.repartition(6).saveAsTextFile("output")
    sc.stop()

  }

}
