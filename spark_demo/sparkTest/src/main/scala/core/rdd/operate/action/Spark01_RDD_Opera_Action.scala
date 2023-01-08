package core.rdd.operate.action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark01_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:43
  * @Description: RDD Action算子
  */
object Spark01_RDD_Opera_Action {


  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("test2")
    val sc = new SparkContext(conf)

    // TODO RDD提供了很多的方法，但是方法主要分为两大类：
    // 1. 第一大类用于将旧的RDD转换为新的RDD，实现功能的组合和扩展
    //    称之为转换算子
    //    在使用时，会根据数据的类型分为3大类
    //    1.1 单值类型 ： List(1,2,3,4)
    //    1.2 双值类型 : List(1,2,3,4), List(3,4,5,6)
    //    1.3 键值类型 : List( (a,1), (b, 1) )
    // 2.  第二大类用于执行RDD的操作 称之为行动算子
    val rdd1: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5))

    // map算子无法让RDD执行
    // lazy， 用到了函数的结果的时候，才会执行函数， 不使用 函数， 函数不执行， 延迟加载；
    // TODO 转换算子实现不同功能的组装
    val rdd2: RDD[Int] = rdd1.map(x => {
      println("*************")
      x * 2
    })

    // TODO 行动算子会触发jod的执行， 执行一次行动算子， 会触发一个新的job
    rdd2.collect()
  }
}
