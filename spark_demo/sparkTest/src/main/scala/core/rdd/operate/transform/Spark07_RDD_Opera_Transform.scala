package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: filter算子  过滤，符合条件留下，不符合条件过滤
  */
object Spark07_RDD_Opera_Transform {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)


    val value: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 6, 1, 3), 3)

    // 过滤可能会产生数据倾斜
    value.filter(_ % 2 == 0).collect().foreach(println)
    sc.stop()

  }

}
