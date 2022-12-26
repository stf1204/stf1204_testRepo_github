package core.rdd.operate.action

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark01_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:43
  * @Description: RDD Action算子： save：saveAsTextFile算子、saveAsObjectFile算子、saveAsSequenceFile算子
  */
object Spark08_RDD_Opera_Action {


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

    rdd1.saveAsTextFile("output")
    rdd1.saveAsObjectFile("output1")
    rdd1.saveAsSequenceFile("output2")
    sc.stop()

  }
}
