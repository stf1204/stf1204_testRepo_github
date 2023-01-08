package core.rdd.instance

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Instance_Memory_Partition
  * @Author: stf
  * @Date: 2022/11/23 14:54
  * @Description: Spark 指定分区个数
  */
object Spark02_RDD_Instance_Memory_Partition {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
      .setAppName("test1...")
      .setMaster("local[*]")
      .set("spark.default.parallelism", "4")
    val sc = new SparkContext(conf)

    val seq: Seq[Int] = Seq(1, 2, 3, 4)
    val list = List(1, 2, 5, 2)
    val set: Set[Int] = Set(1, 2, 6, 3)
    val map: Map[String, Int] = Map("a" -> 1)

    // TODO makeRDD需要传入两个参数
    //  第一个参数表示数据源Seq/List
    //  第二个参数表示分区数量，存在默认值
    //  默认值：scheduler.conf.getInt("spark.default.parallelism", totalCores)默认值：
    val rdd1: RDD[Int] = sc.makeRDD(list)

    rdd1.saveAsTextFile("output")
    sc.stop()
  }

}
