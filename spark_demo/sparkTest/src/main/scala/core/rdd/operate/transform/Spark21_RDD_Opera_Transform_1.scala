package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: TODO: 小案例-----分布式
  */
object Spark21_RDD_Opera_Transform_1 {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)

    // 获取数据
    val dataRDD: RDD[String] = sc.textFile("data/data.txt")
    // TODO 省份  + 点击广告  TOP3
    //  一行：时间戳 省份 城市 用户 广告

    // TODO 先聚合再分组, 将数据聚合，减少数据量
    //  先统计广告点击次数， 再按照省份分组

    val cnt: RDD[((String, String), Int)] = dataRDD.map {
      lines => {
        val datas: Array[String] = lines.split(" ")
        ((datas(1), datas(4)), 1)
      }
    }.reduceByKey(_ + _)

    val pro_ad: RDD[(String, Iterable[(String, Int)])] = cnt.map {
      case ((pro, ad), cnt) => (pro, (ad, cnt))
    }.groupByKey()

    val res: RDD[(String, List[(String, Int)])] = pro_ad.mapValues { line =>
      // 降序排列,取top3
      line.toList.sortBy(_._2)(Ordering.Int.reverse)
        .take(3)
    }
    res.collect().foreach(println)

    sc.stop()
  }

}