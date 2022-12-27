package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: groupBy算子  分组，会导致shuffle
  */
object Spark06_RDD_Opera_Transform_3 {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)


    val value: RDD[String] = sc.makeRDD(List("Hadoop","Flume","Spark","Flume","Spark","Flume","Spark","Hive","Scala","Flink"))

//    value.map((_,1)).reduceByKey(_+_).collect().foreach(println)


    // TODO groupBy实现WordCount   (1,10)
    value.groupBy(word=>word).mapValues(_.size).collect().foreach(println)


    sc.stop()

  }

}
