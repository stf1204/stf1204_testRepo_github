package core.rdd.instance

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark01_RDD_Instance
  * @Author: stf
  * @Date: 2022/11/22 21:25
  * @Description: Spark中创建Rdd方式1
  */
object Spark02_RDD_Instance_Memory {

  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("test")
    val sc = new SparkContext(conf)

    // TODO 1、通过内存集合中构建
    //    parallelize： 并行，分区
    // Scala 中的集合一般采用伴生对象apply方法构建
    val se: Seq[Int] = Seq(1, 2, 3)
    val value: RDD[Int] = sc.parallelize(se)

    // makeRDD 底层调用parallelize方法
    val base: RDD[Int] = sc.makeRDD(se)

    value.collect().foreach(println)

    sc.stop()
  }
}
