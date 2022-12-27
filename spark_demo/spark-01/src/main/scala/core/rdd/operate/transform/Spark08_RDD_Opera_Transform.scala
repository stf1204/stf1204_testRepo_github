package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: Spark中的distinct算子 使用的是分布式逻辑进行去重
  *              Scala 中的distinct使用的是HashSet帮我们去重
  */
object Spark08_RDD_Opera_Transform {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)


    val value: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 6, 1, 3, 7, 9, 8), 3)

    // case _ => map(x => (x, null)).reduceByKey((x, _) => x, numPartitions).map(_._1)
    value.distinct(2).saveAsTextFile("output") //.collect().foreach(println)
    sc.stop()

  }

}
