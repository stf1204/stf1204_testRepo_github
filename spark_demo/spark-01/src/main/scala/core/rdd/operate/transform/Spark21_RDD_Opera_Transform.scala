package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: TODO: 小案例-----单机版
  */
object Spark21_RDD_Opera_Transform {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)

    // 获取数据
    val dataRDD: RDD[String] = sc.textFile("data/data.txt")
    // TODO 省份  + 点击广告  TOP3
    //  一行：时间戳 省份 城市 用户 广告

    // 获取一个省份的所有广告
    val one_pro_ad: RDD[(String, Iterable[String])] = dataRDD.groupBy(_.split(" ")(1))

    //  对省份内广告的点击次数 进行统计
    val top3: RDD[(String, List[(String, Int)])] = one_pro_ad.mapValues {
      lines =>
        val ad_nu: Iterable[(String, Int)] = lines.map {
          ads =>
            val sb: Array[String] = ads.split(" ")
            (sb(4), 1)
        }
        val ad_al: Map[String, Int] = ad_nu.groupBy(_._1).mapValues(_.size)
        ad_al.toList.sortBy(_._2)(Ordering.Int.reverse).take(3)

    }
    top3.collect().foreach(println)

  }

}