package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: groupBy算子  分组，会导致shuffle，可以改变分区个数
  */
object Spark06_RDD_Opera_Transform_4 {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)


    val value: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 6, 1, 3), 3)

    //    value.map((_,1)).reduceByKey(_+_).collect().foreach(println)


    // TODO groupBy可以改变分区数
    // 当存在shuffle的过程时，会将原有的过程分为两段，前一段全部完成，后一段才能开始
    // 所有带shuffle的算子，都可改变分区
    value.groupBy(f = _ % 2, numPartitions = 2).saveAsTextFile("output")


    sc.stop()

  }

}
