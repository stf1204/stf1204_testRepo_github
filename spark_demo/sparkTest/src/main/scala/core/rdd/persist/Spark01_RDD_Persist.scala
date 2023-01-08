package core.rdd.persist

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
  * @ClassName: Spark01_RDD_Instance
  * @Author: stf
  * @Date: 2022/11/22 21:25
  * @Description:
  */
object Spark01_RDD_Persist {

  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("test")
    val sc = new SparkContext(conf)

    val RDD1: RDD[String] = sc.makeRDD(List(
      "hello spark", "hello scala"
    ), 2
    )
    val RDD2: RDD[String] = RDD1.flatMap(_.split(" "))

    val RDD3: RDD[(String, Int)] = RDD2.map { x =>
      println("####")
      (x, 1)
    }

    val RDD4: RDD[(String, Int)] = RDD3.reduceByKey(_ + _)


    println(RDD4.collect().mkString(","))
    println("---------------------------------------")

    val RDD11: RDD[String] = sc.makeRDD(List(
      "hello spark", "hello scala"
    ), 2
    )
    val RDD22: RDD[String] = RDD11.flatMap(_.split(" "))

    val RDD33: RDD[(String, Int)] = RDD22.map { x =>
      println("####")
      (x, 1)
    }

    val RDD44: RDD[(String, Iterable[Int])] = RDD33.groupByKey(new HashPartitioner(2))
    println(RDD44.collect().mkString(","))


    sc.stop()
  }
}
