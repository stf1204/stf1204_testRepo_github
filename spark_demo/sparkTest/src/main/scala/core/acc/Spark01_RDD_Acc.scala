package core.acc

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Seq
  * @Author: stf
  * @Date: 2022/11/29 11:51
  * @Description:
  */
object Spark01_RDD_Acc {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf()
      .setAppName("ACC")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)

    val data: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), 2)

    var sum = 0
    data.foreach(
      word => {
        sum += word
        println("sum=" + sum)
      }
    )
    println(sum)


    sc.stop()
  }
}
