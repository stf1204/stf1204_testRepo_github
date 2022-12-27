package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: reduceByKey算子  聚合函数
  */
object Spark15_RDD_Opera_Transform {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)
    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("flume", 1), ("spark", 2), ("hadoop", 2), ("flume", 22), ("hadoop", 3)), 2)


    //    TODO groupByKey 可以实现 wordCount （2 / 10）
    val value: RDD[(String, Int)] = rdd.groupByKey(new HashPartitioner(2)).mapValues(_.sum)
    value.foreach(println)

    sc.stop()

  }

}
