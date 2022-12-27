package core.acc

import org.apache.spark.util.AccumulatorV2
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

/**
  * @ClassName: Seq
  * @Author: stf
  * @Date: 2022/11/29 11:51
  * @Description: 自定义累加器,统计多个单词出现的次数
  */
object Spark05_RDD_Acc {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("ACC").setMaster("local[*]")
    val sc = new SparkContext(conf)


    val data = sc.makeRDD(List("a", "b", "x", "d", "b", "l", "k", "g", "f", "d"), 2)

    // TODO 使用自定义累加器步骤
    //  1、new对象
    //  2、向Spark注册  register
    //  3、使用累加器添加元素  add
    //  4、获取值  values
    val acc = new MyAcc
    sc.register(acc)

    data.foreach(word => acc.add(word))

    println(acc.value)


    sc.stop()
  }

  // TODO 自定义累加器步骤
  // 1、继承AccumulatorV2类
  // 2、定义泛型
  // IN:String
  // OUT:Map[String,Integer]
  // 3、重写方法(3 + 3)
  class MyAcc extends AccumulatorV2[String, mutable.Map[String, Int]] {
    override def isZero: Boolean = {
      wcMap.isEmpty
    }

    override def copy(): AccumulatorV2[String, mutable.Map[String, Int]] = {
      new MyAcc
    }

    override def reset(): Unit = {
      wcMap.clear()
    }

    override def add(v: String): Unit = {
      val oldCnt: Int = wcMap.getOrElse(v, 0)
      wcMap.update(v, oldCnt + 1)
    }

    override def merge(other: AccumulatorV2[String, mutable.Map[String, Int]]): Unit = {
      other.value.foreach(line => {
        val oldCnt: Int = wcMap.getOrElse(line._1, 0)
        wcMap.update(line._1, line._2 + oldCnt)
      })
    }


    override def value: mutable.Map[String, Int] = {
      wcMap
    }

    private val wcMap = mutable.Map[String, Int]()
  }

}
