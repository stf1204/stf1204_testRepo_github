package core.rdd.operate.action

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark01_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:43
  * @Description: RDD Action算子  countByKey、countByValue 实现wordCount
  */
object Spark05_RDD_Opera_Action {


  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("test2")
    val sc = new SparkContext(conf)

    val rdd1 = sc.makeRDD(
      List(
        ("a", 1), ("b", 1), ("c", 1), ("a", 1), ("d", 1), ("c", 1)
      ), 2)

    // TODO 使用countByKey算子实现wordCount (7  /  10)
    //    (c,1)
    // 此方法对数据的要求，只能进行简单的count操作，无法进行累加
    // (c,3)======> 需要转换为 (c,1)，(c,1)，(c,1)====>再进行countByKey
    //    rdd1.countByKey().foreach(println)

    // TODO 使用countByValue算子实现wordCount （8  / 10）
    //  ((c,1),2)
//    rdd1.countByValue().foreach(println)

    sc.stop()

  }
}
