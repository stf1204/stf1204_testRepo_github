package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: sortBy 算子     排序
  */
object Spark11_RDD_Opera_Transform {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)


    // TODO sortBy默认使用升序，第二个参数可以指定升、降序

    //    val core.rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 6, 1, 3, 7, 9, 8), 3)
    val rdd: RDD[String] = sc.makeRDD(List("1", "2", "89", "97", "57", "28"))
    // sortBy算子是将每一条数据增加一个排序标记，然后根据标记进行排序，默认为升序
    rdd.sortBy(str => str.toInt % 2, false).collect().foreach(println)
    sc.stop()

  }

}
