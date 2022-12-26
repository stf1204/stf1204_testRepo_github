package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: TODO: sortByKey() 默认是对数据按照key进行升序排序
  */
object Spark20_RDD_Opera_Transform {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)
    val rdd1: RDD[(String, Int)] = sc.makeRDD(List(
      ("a", 1), ("a", 4), ("b", 8), ("c", 9),
      ("c", 3), ("d", 3), ("a", 4), ("b", 5)
    ), 2)
    //    rdd1.sortBy()

    // sortByKey() 默认是对数据按照key进行升序排序
    val rdd2: RDD[(String, Int)] = rdd1.sortByKey(false)
    rdd2.collect().foreach(println)


    sc.stop()

  }
}
