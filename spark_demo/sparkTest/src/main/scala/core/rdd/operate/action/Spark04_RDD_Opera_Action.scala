package core.rdd.operate.action

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark01_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:43
  * @Description: RDD Action算子  countByKey算子、countByValue算子
  */
object Spark04_RDD_Opera_Action {


  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("test2")
    val sc = new SparkContext(conf)

    val rdd1 = sc.makeRDD(
      List(
        ("a",1),("b",2),("c",3),("a",4),("d",5),("c",6)
      ), 2)

    // countByKey 按照key进行计数操作
    val coll: collection.Map[String, Long] = rdd1.countByKey()
//    println(coll)

    //countByValue 将key-value 整体作为key进行计数
    val col: collection.Map[(String, Int), Long] = rdd1.countByValue()
//    println(col)

sc.stop()

  }
}
