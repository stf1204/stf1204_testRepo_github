package core.req

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayOps

/**
  * @ClassName: Req
  * @Author: stf
  * @Date: 2022/11/29 11:51
  * @Description: TODO 需求：  按照每个品类的点击、下单、支付的量（次数）来统计热门品类。------Base_2版本
  */
object Spark02_RDD_Req {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("Seq").setMaster("local[*]")
    val sc = new SparkContext(conf)

    // TODO 先获取数据集
    val dataRDD: RDD[String] = sc.textFile("data/user_visit_action.txt")

    // TODO 优化：dataRDD会重复使用，可以进行缓存
    dataRDD.cache()

    //  TODO 1、获取每个品类的点击数量  (click, cnt)
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

    // (品类id,点击次数)
    // (品类id,下单次数)
    // (品类id,支付次数)
    // (品类id,(点击次数,下单次数,支付次数))
    /*   val coRDD: RDD[(String, (Iterable[Int], Iterable[Int], Iterable[Int]))] = clickRDD.cogroup(orderRDD, payRDD)

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
       */


    // TODO：优化二：cogroup分区不相同的时候会进行shuffle操作，性能慢

    // (品类id,点击次数) ===> (品类id,(点击次数,0,0))
    // (品类id,下单次数) ===> (品类id,(0,下单次数,0))
    //                  ===> (品类id,(点击次数,下单次数,0))
    // (品类id,支付次数) ===> (品类id,(0,0,支付次数))
    //                  ===> (品类id,(点击次数,下单次数,支付次数))
    val cli: RDD[(String, (Int, Int, Int))] = clickRDD.map {
      case (click_id, cnt) => {
        (click_id, (cnt, 0, 0))
      }
    }

    val ord: RDD[(String, (Int, Int, Int))] = orderRDD.map {
      case (order_id, cnt) => {
        (order_id, (0, cnt, 0))
      }
    }

    val pa: RDD[(String, (Int, Int, Int))] = payRDD.map {
      case (pay_id, cnt) => {
        (pay_id, (0, 0, cnt))
      }
    }

    // 三份数据集union在一起，统一进行reduceByKey
    val resRDD: RDD[(String, (Int, Int, Int))] = cli.union(ord).union(pa)
    val result: RDD[(String, (Int, Int, Int))] = resRDD.reduceByKey { (k1, k2) => {
      (k1._1 + k2._1, k1._2 + k2._2, k1._3 + k2._3)
    }}
    val top10: Array[(String, (Int, Int, Int))] = result.sortBy(_._2, false).take(10)
    top10.foreach(println)


    sc.stop()
  }
}
