package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: flatMap 扁平化map
  */
object Spark05_RDD_Opera_Transform_1 {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)

    val value: RDD[String] = sc.makeRDD(List("Hello Spark","Hello Scala"))

    // flatMap算子可以将数据集中的数据拆分成个体来使用
    value.flatMap(str=> {
      // 用到隐式转换，将字符串 转为字符数组
      str
//      str.split(" ")
    }).collect().foreach(println)

    sc.stop()

  }

}
