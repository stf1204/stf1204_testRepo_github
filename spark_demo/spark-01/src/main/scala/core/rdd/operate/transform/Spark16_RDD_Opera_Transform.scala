package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: reduceByKey算子  聚合函数
  */
object Spark16_RDD_Opera_Transform {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)
    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("flume", 1), ("spark", 2), ("hadoop", 2), ("flume", 22), ("hadoop", 3)), 2)

    // TODO reduceByKey 将数据集中的数据，根据key进行两两聚合，并且存在shuffle，可以指定分区数
    // 分布式计算存在分区内和分区间的计算，二者计算逻辑相同
    // 数据分区采用默认的 hashPartitoner


    //    TODO reduceByKey 可以实现 wordCount （3 / 10）
    rdd.reduceByKey {
      (x, y) =>
      println(s"$x + $y = ${x + y}")
        x + y
    }.collect().foreach(println)


    sc.stop()

  }

}
