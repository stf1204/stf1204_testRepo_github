package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: intersection交集、union并集、subtract差集、zip拉链 算子
  */
object Spark12_RDD_Opera_Transform {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)

    val rdd1: RDD[Int] = sc.makeRDD(List(1, 5, 3, 6), 2)
    val rdd2: RDD[Int] = sc.makeRDD(List(6, 5, 8, 2, 3, 3, 2, 1), 4)


    // TODO 交集 并集 差集 拉链

    // 6,5,8,2,3,3,2,1
    // 1,5,3,3,6
    // 交集 1，3，5，6
    rdd1.intersection(rdd2).collect().foreach(print)
    println()

    // 并集6,5,8,2,3,3,2,1,1,5,3,3,6
    // 并集本身不会去除重复元素，存放的容器为list，所以也不会去除重复
    rdd1.union(rdd2).collect().foreach(print)
    println()

    // 差集（Scala中是diff函数，Spark当中是subtract算子）
    //    rdd1.subtract(rdd2).collect().foreach(print)
    rdd2.subtract(rdd1).collect().foreach(print)
    println()

    // 拉链
    //  SparkException: Can only zip RDDs with same number of elements in each partition
    //  IllegalArgumentException: Can't zip RDDs with unequal numbers of partitions: List(2, 4)

    //(1,3)(5,6)(3,2)(6,7) Spark当中的拉链只能拉链相同分区，相同个数的数据
    //(1,haha)(5,heihei)(3,ai)(6,hen) 对数据的类型没有要求----泛型检查，导致二次编译
    // 【泛型不可变】
    // 所谓的泛型是内部的数据类型约束。
    val rdd3: RDD[Int] = sc.makeRDD(List(3, 6, 2, 7), 2)
    val rdd4: RDD[String] = sc.makeRDD(List("haha", "heihei", "ai", "hen"), 2)
    rdd1.zip(rdd3).collect().foreach(print)
    println()
    rdd1.zip(rdd4).collect().foreach(print)
    println()


    sc.stop()

  }

}
