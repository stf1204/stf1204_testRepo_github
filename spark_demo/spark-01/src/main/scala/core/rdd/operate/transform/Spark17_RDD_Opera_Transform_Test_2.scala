package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description:  TODO： aggregateByKey foldByKey 算子 实现wordCount案例
  */
object Spark17_RDD_Opera_Transform_Test_2 {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)
    val rdd1: RDD[(String, Int)] = sc.makeRDD(List(
      ("a", 1), ("a", 4), ("b", 8), ("c", 9),
      ("c", 3), ("d", 3), ("a", 4), ("b", 5)
    ), 2)

    // TODO aggregateByKey实现wordCount案例（4 \ 10）
    rdd1.aggregateByKey(0)((x, y) => x + y, _ + _).collect().foreach(println)


    // TODO 如果分区内和分区之间的处理逻辑相同，写两遍冗余了，可以使用foldByKey算子
    // TODO foldByKey实现wordCount案例（5 \ 10）
    rdd1.foldByKey(0)(_ + _).collect().foreach(println)
    sc.stop()

  }
}
