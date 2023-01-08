package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: TODO: cogroup算子
  */
object Spark23_RDD_Opera_Transform {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)

    // cogroup: group + connection
    val rdd1: RDD[(String, Int)] = sc.makeRDD(List(("a",1),("b",2),("c",3)))
    val rdd2: RDD[(String, Int)] = sc.makeRDD(List(("a",4),("d",5),("c",6)))

    /**
      * (a,(CompactBuffer(1),CompactBuffer(4)))
      * (b,(CompactBuffer(2),CompactBuffer()))
      * (c,(CompactBuffer(3),CompactBuffer(6)))
      * (d,(CompactBuffer(),CompactBuffer(5)))
      */
    val joinRDD: RDD[(String, (Iterable[Int], Iterable[Int]))] = rdd1.cogroup(rdd2)
    joinRDD.collect().foreach(println)


    sc.stop()
  }

}