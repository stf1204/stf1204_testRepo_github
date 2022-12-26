package core.acc

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.broadcast.Broadcast

/**
  * @ClassName: Seq
  * @Author: stf
  * @Date: 2022/11/29 11:51
  * @Description: 自定义累加器,统计多个单词出现的次数
  */
object Spark05_RDD_broadcast {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[*]").setAppName("Acc")
    val sc = new SparkContext(conf);

    val rdd1 = sc.makeRDD(
      List(
        ("a", 1), ("b", 2)
      )
    )

    val rdd2 = sc.makeRDD(
      List(
        ("a", 3), ("b", 4)
      )
    )

    val map = Map( ("a", 3), ("b", 4) )
    val bc: Broadcast[Map[String, Int]] = sc.broadcast(map)


    rdd1.map {
      case ( word, cnt) => {
        // 取值用bc.value
        (word, (cnt, bc.value.getOrElse(word, 0)))
      }
    }.foreach(println)

    //joinRDD.foreach(println)

    sc.stop()
  }
}
