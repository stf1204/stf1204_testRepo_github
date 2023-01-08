package core.req

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayOps

/**
  * @ClassName: Req
  * @Author: stf
  * @Date: 2022/11/29 11:51
  * @Description: TODO 需求：  按照每个品类的点击、下单、支付的量（次数）来统计热门品类。------Base_1版本
  */
object Spark01_RDD_Req {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("Seq").setMaster("local[*]")
    val sc = new SparkContext(conf)

    // TODO 先获取数据集
    val dataRDD: RDD[String] = sc.textFile("data/user_visit_action.txt")


    //  TODO 1、获取每个品类的点击数量  (click,cnt)
    val clickRDD: RDD[(String, Int)] = dataRDD.filter(lines => {
      val data: Array[String] = lines.split("_")
      data(6) != "-1"
    }) // 先获取点击行为
      .map(lines => {
      val data: Array[String] = lines.split("_")
      (data(6), 1)
    }).reduceByKey(_+_)

    // 基础版取前十
    //    clickRDD.reduceByKey(_ + _).sortByKey(false).take(10).foreach(println)

    //  TODO 2、获取每个品类的下单数量
    val orderRDD: RDD[(String, Int)] = dataRDD.filter(lines => {
      val data: Array[String] = lines.split("_")
      data(8) != "null"
    })
      .flatMap(lines => {
        val data: ArrayOps.ofRef[String] = lines.split("_")
        data(8).split(",").map(order => {
          (order, 1)
        })
      }).reduceByKey(_+_)


    //  TODO 3、获取每个品类的支付数量
    val payRDD: RDD[(String, Int)] = dataRDD.filter(lines => {
      val data: Array[String] = lines.split("_")
      data(10) != "null"
    })
      .flatMap(lines => {
        val data: ArrayOps.ofRef[String] = lines.split("_")
        data(10).split(",").map(pay => {
          (pay, 1)
        })
      }).reduceByKey(_+_)

    // TODO 4、先按照点击数排名，靠前的就排名高；如果点击数相同，再比较下单数；下单数再相同，就比较支付数
    // join leftjoin rihtjoin fulljoin cogroup

    // (品类,点击次数)
    // (品类,下单次数)
    // (品类,支付次数)
    // (品类,(点击次数,下单次数,支付次数))
    val coRDD: RDD[(String, (Iterable[Int], Iterable[Int], Iterable[Int]))] = clickRDD.cogroup(orderRDD, payRDD)

    val ResRDD: RDD[(String, (Int, Int, Int))] = coRDD.mapValues {
      case (click, order, pay) => {
        // headOption 获取可能存在的head，加上getOrElse设置不存在时的返回值
        val Click = click.headOption.getOrElse(0)
        val Order = order.headOption.getOrElse(0)
        val Pay = pay.headOption.getOrElse(0)
        (Click, Order, Pay)
      }
    }
    val top10: Array[(String, (Int, Int, Int))] = ResRDD.sortBy(_._2, false).take(10)
    top10.foreach(println)


    sc.stop()
  }
}
