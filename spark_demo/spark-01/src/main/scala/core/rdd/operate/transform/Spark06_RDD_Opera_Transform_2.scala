package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: groupBy算子  分组，会导致shuffle
  */
object Spark06_RDD_Opera_Transform_2 {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)


    val value: RDD[String] = sc.makeRDD(List("Hadoop","Flume","Spark","Hive","Scala","Flink"))

    // TODO 按照首字母进行分组
    // value.groupBy(word=>word.substring(0,1)).collect().foreach(println)
    // value.groupBy(_.charAt(0)).collect().foreach(println)
    value.groupBy(_.head).collect().foreach(println)

    sc.stop()

  }

}
