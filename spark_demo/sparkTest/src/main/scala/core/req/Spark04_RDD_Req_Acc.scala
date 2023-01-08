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
object Spark04_RDD_Req_Acc {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("Seq").setMaster("local[*]")
    val sc = new SparkContext(conf)

    // TODO 先获取数据集
    val dataRDD: RDD[String] = sc.textFile("data/user_visit_action.txt")

    // TODO 使用累加器实现累加功能

    //  1、new对象
    val acc = new MyAcc
    //  2、注册
    sc.register(acc)
    //  3、add数据
    dataRDD.foreach(datas => {
      val data: Array[String] = datas.split("_")
      if (data(6) != "-1") acc.add(data(6), "click")
      else if (data(8) != "null") {
        val word: Array[String] = data(8).split(",")
        word.foreach(a => acc.add(a, "order"))
      }
      else if (data(10) != "null") {
        val word: Array[String] = data(8).split(",")
        word.foreach(a => acc.add(a, "pay"))
      }
    }
    )

    //  4、values拿数据
    //  拿到的数据是未排序的数据
    val accValue: mutable.Map[String, MyType] = acc.value

    val list: List[MyType] = accValue.map(_._2).toList

    val last: List[MyType] = list.sortWith(
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
    )

    last.take(10).foreach(println)


    sc.stop()
  }


  // TODO 将点击，订单，支付数据封装为类的属性进行传递
  //     样例类中的构造参数 会自动作为类的属性存在， 不能修改
  //     想要修改需要设定为 var
  case class MyType(var cid: String, var cliCnt: Int, var ordCnt: Int, var payCnt: Int) {

  }

  // TODO 自定义累加器
  // 1、继承
  // 2、泛型
  //  IN: (String, String)  (品类， 行为类型)
  //  OUT: MAP(品类， （点击， 下单， 支付）)
  // 3、实现方法（3 + 3）
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
