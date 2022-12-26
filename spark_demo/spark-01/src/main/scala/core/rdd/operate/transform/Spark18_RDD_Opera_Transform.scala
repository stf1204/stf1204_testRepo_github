package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: TODO combineByKey  分区内合并，分区间合并，逻辑不相同，并且数据的格式要发生转变
  */
object Spark18_RDD_Opera_Transform {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)
    val rdd1: RDD[(String, Int)] = sc.makeRDD(List(
      ("a", 1), ("a", 4), ("b", 8), ("c", 9),
      ("c", 3), ("d", 3), ("a", 4), ("b", 5)
    ), 2)
    // TODO 计算 key的总值，以及统计该单词出现次数， 最后计算平均值
    rdd1.map { case (word, cnt) => (word, (cnt, 1)) }.reduceByKey { (t1, t2) =>
      (t1._1 + t2._1, t1._2 + t2._2)
    }.mapValues {
      case (total, cnt) => {
        total / cnt
      }
    }.collect().foreach(println)

    // combineByKey  分区内合并，分区间合并，逻辑不相同，并且数据的格式要发生转变
    // 参数一：转换第一条数据的格式
    // 参数二：分组内的合并逻辑
    // 参数三：分组之间的合并逻辑
    rdd1.combineByKey(v => (v, 1), (k1: (Int, Int), v) => {
      (k1._1 + v, k1._2 + 1)
    }, (t1: (Int, Int), t2: (Int, Int)) => {
      (t1._1 + t2._1, t1._2 + t2._2)
    }).mapValues(x => x._1 / x._2)


    sc.stop()

  }
}
