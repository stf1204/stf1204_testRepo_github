package core.req

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayOps

/**
  * @ClassName: Req
  * @Author: stf
  * @Date: 2022/11/29 11:51
  * @Description: TODO 需求：  按照每个品类的点击、下单、支付的量（次数）来统计热门品类。------Base_3版本
  */
object Spark03_RDD_Req {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("Seq").setMaster("local[*]")
    val sc = new SparkContext(conf)

    // TODO 先获取数据集
    val dataRDD: RDD[String] = sc.textFile("data/user_visit_action.txt")

    // 之前的版本都是先过滤再进行逻辑处理，多次进行 reduceByKey 存在过多的shuffle，有待优化
    // 点击，下单，支付数据都在同一数据集中，不需要 过滤 再 union ，可以直接针对不同数据进行处理
    val Result: Array[(String, (Int, Int, Int))] = dataRDD.flatMap {
      lines => {
        val line: ArrayOps.ofRef[String] = lines.split("_")
        if (line(6) != "-1") {
          // 点击数据
          List((line(6), (1, 0, 0)))

        } else if (line(8) != "null") {
          // 下单数据
          line(8).split(",").map(data => {
            (data, (0, 1, 0))
          })
        } else if (line(10) != "null") {
          // 支付数据
          line(10).split(",").map(data => {
            (data, (0, 0, 1))
          })
        }
        else {
          // 其他数据
          Nil
        }
      }
    }.reduceByKey((k1, k2) => {
      (k1._1 + k2._1, k1._2 + k2._2, k1._3 + k2._3)
    }).sortBy(_._2, false).take(10)
    Result.foreach(println)

    sc.stop()
  }
}
