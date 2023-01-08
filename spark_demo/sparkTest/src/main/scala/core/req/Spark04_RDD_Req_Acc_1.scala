package core.req

import org.apache.spark.rdd.RDD
import org.apache.spark.util.AccumulatorV2
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

/**
  * @ClassName: Req
  * @Author: stf
  * @Date: 2022/11/29 11:51
  * @Description: TODO 需求：  按照每个品类的点击、下单、支付的量（次数）来统计热门品类。------Acc版本
  */
object Spark04_RDD_Req_Acc_1 {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("Seq").setMaster("local[*]")
    val sc = new SparkContext(conf)

    // TODO 先获取数据集
    val dataRDD: RDD[String] = sc.textFile("data/user_visit_action.txt")

    val acc = new MyAcc
    sc.register(acc)

    dataRDD.foreach(line => {
      val lines: Array[String] = line.split("_")
      if (lines(6) != "-1") {
        acc.add(lines(6), "click")
      }
      else if (lines(8) != "null") {
      lines(8).split(",").foreach(
        a=>acc.add(a,"order")
      )
      } else if (lines(10) != "null") {
        lines(10).split(",").foreach(
        a=>acc.add(a,"pay")
        )
      }
    })

    val accValue: mutable.Map[String, MyType] = acc.value
    val value = accValue.map(_._2).toList
    val top10: List[MyType] = value.sortWith(
      (c1, c2) => {
        if (c1.cliCnt > c2.cliCnt)
          true
        else if (c1.cliCnt == c2.cliCnt) {
          if (c1.ordCnt > c2.ordCnt)
            true
          else if (c1.ordCnt == c2.ordCnt) {
            c1.payCnt > c2.payCnt
          }
          else false
        }
        else false
      }
    ).take(10)

    top10.foreach(println)

    sc.stop()

  }

  // 样例类
  case class MyType(var cid: String, var cliCnt: Int, var ordCnt: Int, var payCnt: Int) {

  }


  class MyAcc extends AccumulatorV2[(String, String), mutable.Map[String, MyType]] {
    override def isZero: Boolean = {
      myMap.isEmpty
    }

    override def copy(): AccumulatorV2[(String, String), mutable.Map[String, MyType]] = {
      new MyAcc
    }

    override def reset(): Unit = {
      myMap.clear()
    }

    override def add(line: (String, String)): Unit = {
      val (cid, actionType) = line
      val oldCnt: MyType = myMap.getOrElse(cid, MyType(cid, 0, 0, 0))

      actionType match {
        case "click" => oldCnt.cliCnt += 1
        case "order" => oldCnt.ordCnt += 1
        case "pay" => oldCnt.payCnt += 1
      }
      myMap.update(cid, oldCnt)
    }


    override def merge(other: AccumulatorV2[(String, String), mutable.Map[String, MyType]]): Unit = {
      other.value.foreach {
        case (cid, otherCnt) => {
          val oldCnt: MyType = myMap.getOrElse(cid, MyType(cid, 0, 0, 0))
          oldCnt.cliCnt += otherCnt.cliCnt
          oldCnt.ordCnt += otherCnt.ordCnt
          oldCnt.payCnt += otherCnt.payCnt
          myMap.update(cid, oldCnt)
        }
      }
    }

    override def value: mutable.Map[String, MyType] = {
      myMap
    }

    private val myMap = mutable.Map[String, MyType]()
  }

}
