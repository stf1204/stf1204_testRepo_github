package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: TODO: 小案例-----分布式(优化，解决可能会出现的数据倾斜问题)
  */
object Spark21_RDD_Opera_Transform_2 {

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

    val caseRDD: RDD[(String, (String, Int))] = cnt.map {
      case ((pro, ad), cnt) => (pro, (ad, cnt))
    }
    val top3: RDD[(String, ListBuffer[(String, Int)])] = caseRDD.aggregateByKey(ListBuffer[(String, Int)]())(
      (list, kv) => { // 区内逻辑
        if (list.size < 3) {
          list.append(kv)
          list
        } else {
          list.append(kv)
          list.sortBy(_._2)(Ordering.Int.reverse) take (3)
        }
      }
      , (list1, list2) => { // 区间逻辑
        list1.appendAll(list2)
        list1.sortBy(_._2)(Ordering.Int.reverse).take(3)
      }
    )
    top3.collect().foreach(println)
    sc.stop()
  }

}