package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: reduceByKey、groupBy、groupByKey 算子  聚合函数
  */
object Spark17_RDD_Opera_Transform_Test {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)
    val rdd1: RDD[Int] = sc.makeRDD(List(1, 5, 7, 3, 1, 6, 1, 5, 8, 90, 2, 2, 4, 6), 3)

    // TODO 先对每个分区求最大值，再求sum
    val maxRDD: RDD[Int] = rdd1.mapPartitions { iter =>
      List(iter.max).iterator
    }

    // 1、设置分区数为一，进行最后的求和
    println(maxRDD.repartition(1).reduce(_ + _))

    // 2、对不同分区的数，设置相同的key，进行reduceByKey
    maxRDD.map(("a",_)).reduceByKey(_+_).map(_._2).collect().foreach(println)

    // 3、对不同分区的数，设置相同的key进行groupByKey，再进行聚合
    maxRDD.map(("sum",_)).groupByKey().mapValues(iter=>iter.sum).map(_._2).collect().foreach(println)

    sc.stop()

  }
}
