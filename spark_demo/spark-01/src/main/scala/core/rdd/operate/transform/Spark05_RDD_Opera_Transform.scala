package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: flatMap算子  扁平化map
  */
object Spark05_RDD_Opera_Transform {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)

    val value: RDD[List[Int]] = sc.makeRDD(List(List(1,2,3),List(4,5,6)))

    // flatMap算子可以将数据集中的数据拆分成个体来使用
    value.flatMap(list=> {
      list
  /*
      val buffer: ListBuffer[Int] = ListBuffer()
      list.foreach{
        datas => buffer.append(datas)
      }
      buffer.toList
      */
    }).collect().foreach(println)

    sc.stop()

  }

}
