package core.rdd.operate.action

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark01_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:43
  * @Description: RDD Action算子： reduce算子、 aggregate算子、 fold算子
  */
object Spark06_RDD_Opera_Action {


  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("test2")
    val sc = new SparkContext(conf)

    val rdd1 = sc.makeRDD(
      List(
        1, 2, 3, 4
      ), 2)

    // TODO reduce算子，聚合操作
    val cnt: Int = rdd1.reduce(_ + _)
    println(cnt)

    // TODO aggregate算子，分区内+分区间 聚合
    // 与aggregateByKey不同，aggregate的初始值在分区内和分区间都要参与计算
    // 分区间计算属于一次性操作，只对初始值进行一次操作
    val cnt2: Int = rdd1.aggregate(10)(_+_,_+_)
    println(cnt2)

    // TODO fold算子   分区内和分区间的逻辑相同，可以用fold替换aggregate算子
    // aggregateByKey初始值只在分区内进行计算，而aggregate在分区内和分区间都要参与计算
    // 分区间计算属于一次性操作，只对初始值进行一次操作
    val cnt3: Int = rdd1.fold(10)(_+_)
    println(cnt3)

    sc.stop()

  }
}
