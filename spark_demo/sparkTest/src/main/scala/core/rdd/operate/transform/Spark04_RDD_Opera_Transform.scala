package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: mapPartitionsWithIndex算子  带分区号的分区map
  */
object Spark04_RDD_Opera_Transform {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)

    val rdd1: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5, 6), 3)

    // TODO 只获取第二个分区的数据，其他分区的数据过滤---- 3,4
    // Spark 的分区是从0开始计数的
    rdd1.mapPartitionsWithIndex { (in, Iter) =>
      if (in == 1) {
        Iter
      } else {
        Nil.iterator
      }
    }.collect().foreach(println)

    sc.stop()

  }

}
