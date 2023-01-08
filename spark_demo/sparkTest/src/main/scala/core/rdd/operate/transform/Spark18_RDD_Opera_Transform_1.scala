package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: TODO combineByKey  实现wordCount
  */
object Spark18_RDD_Opera_Transform_1 {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)
    val rdd1: RDD[(String, Int)] = sc.makeRDD(List(
      ("a", 1), ("a", 4), ("b", 8), ("c", 9),
      ("c", 3), ("d", 3), ("a", 4), ("b", 5)
    ), 2)
    // TODO 计算 key的总值，以及统计该单词出现次数， 最后计算平均值
    // 此方法会对每一个数据进行组装，数据量大了以后操作不方便，Spark提供combineByKey算子进行优化
    rdd1.map { case (word, cnt)
    => (word, (cnt, 1))
    }.reduceByKey((x, y) => {
      (x._1 + y._1, x._2 + y._2)
    }).mapValues(x => {
      x._1 / x._2
    })

    // combineByKey  分区内合并，分区间合并，逻辑不相同，并且数据的格式要发生转变
    // 参数一：转换第一条数据的格式，并且是只转换第一条
    // 参数二：分组内的合并逻辑
    // 参数三：分组之间的合并逻辑
    // TODO combineByKey 实现wordCount （6 / 10）
    rdd1.combineByKey(v => v, (k1: Int, k2) => {
      k1 + k2
    }, (k1: Int, k2: Int) => {
      k1 + k2
    }) //.collect().foreach(println)


    sc.stop()

  }
}
