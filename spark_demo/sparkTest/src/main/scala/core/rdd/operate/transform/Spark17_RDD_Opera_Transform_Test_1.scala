package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: aggregateByKey 算子  分区内和分区之间处理逻辑不同的函数
  */
object Spark17_RDD_Opera_Transform_Test_1 {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)
    val rdd1: RDD[(String, Int)] = sc.makeRDD(List(
      ("a", 1), ("a", 4), ("b", 8), ("c", 9),
      ("c", 3), ("d", 3), ("a", 4), ("b", 5)
    ), 2)

    // TODO 先对每个分区求最大值，再求sum
    //  分区内和分区之间的处理逻辑不相同----spark提供了aggregateByKey算子进行处理
    // 该算子存在柯里化调用
    // 第一个参数列表为初始值：zeroValue（零值）， 计算的初始值
    // 第二个参数列表有两个参数；
    //        第一个参数表示分区内，对相同 key  value的计算逻辑，
    //        第二个参数表示分区之间，对相同的key   value计算逻辑
    rdd1.aggregateByKey(0)({ (x, y) =>
      math.max(x, y)
    },
      { (x, y) => x + y }).collect().foreach(println)

    sc.stop()

  }
}
