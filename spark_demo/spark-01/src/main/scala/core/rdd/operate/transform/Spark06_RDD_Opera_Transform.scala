package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: groupBy算子  分组，会导致shuffle
  */
object Spark06_RDD_Opera_Transform {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)

    val value: RDD[Int] = sc.makeRDD(List(0, 2, 'a', 'G', 5, 6, '%'),3)
    /*
          value.groupBy(ins => if (ins % 2 == 0) {
            "偶数"
          } else {
            "奇数"
          }
          ).collect().foreach(println)
    */
    // 化简
    // 下划线标识我们要处理的数据集当中的每一条数据

    // groupBy返回的数值类型为：  K-V ----> （组名， 相同标记的数据集合）
    value.groupBy(_ % 2).collect().foreach(println)
    sc.stop()

  }

}
