package core.rdd.operate.action

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark01_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:43
  * @Description: RDD Action算子： foreach算子 与 Array中的foreach函数
  */
object Spark09_RDD_Opera_Action {


  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("test2")
    val sc = new SparkContext(conf)

    val rdd1 = sc.makeRDD(
      List(
        ("a", 1), ("B", 1),
        ("a", 2), ("B", 3),
        ("a", 4), ("B", 7)
      ),
      2
    )

    // Array 中的foreach函数， foreach函数是按照分区号进行存储到数组中，打印的时候按照数组顺序打印
    rdd1.collect().foreach(println)
println("===========================")
    // Spark 当中的foreach算子，分区间是无序的，所以分区间数据是无序的
    rdd1.foreach(println)
    sc.stop()

  }
}
