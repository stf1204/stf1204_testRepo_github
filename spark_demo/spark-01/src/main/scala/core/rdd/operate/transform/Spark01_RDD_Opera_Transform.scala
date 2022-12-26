package core.rdd.operate.transform

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark01_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:43
  * @Description: RDD 转换算子
  */
object Spark01_RDD_Opera_Transform {


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
  }
}
