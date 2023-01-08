package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Scala02_RDDTransform_2
  * @Author: stf
  * @Date: 2022/11/23 20:11
  * @Description: RDD之间的分区个数，以及数据流转顺序
  */
object Spark02_RDD_Opera_Transform_2 {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val list = List(1, 2, 3, 5)

    val RDD: RDD[Int] = sc.makeRDD(list, 2)

    // TODO 默认情况，RDD不会改变分区数量
    // 默认数据处理后所在分区不变
    // 计算过程中， 分区内有序， 分区之间无序
    // RDD不能保存数据，多个转换算子，所以一条数据应该完全处理完毕之后再处理下一条数据
    // RDD是组合成的一个完整的功能， 一次执行一条数据;

    val RDD2: RDD[Int] = RDD.map { data =>
      println(s"###data======> $data")
      data
    }
    val RDD3: RDD[Int] = RDD2.map { data =>
      println(s"@@@data==========> $data")
      data
    }
    RDD3.collect() //.foreach(println)

    sc.stop()
  }

}
