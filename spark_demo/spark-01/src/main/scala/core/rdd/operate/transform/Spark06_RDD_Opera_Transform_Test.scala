package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: groupBy算子  分组，会导致shuffle，可以改变分区个数
  */
object Spark06_RDD_Opera_Transform_Test {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)


    val value: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 6, 1, 3), 3)

    // TODO 获取每个分区当中的最大值
    // 算子：map mapPartitions mapPartitionsWithIndex flatMap groupBy
    //    value.mapPartitions(list => List(list.max).iterator).collect().foreach(println)
    value.mapPartitionsWithIndex((par, list) =>
      // 使用传进来的list拿到最大值， 包装为List类型，返回新的iterator对象
      List((par,list.max)).iterator)
      .collect().foreach(println)

    sc.stop()

  }

}
