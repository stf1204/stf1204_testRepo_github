package core.acc

import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Seq
  * @Author: stf
  * @Date: 2022/11/29 11:51
  * @Description: 累加器
  */
object Spark02_RDD_Acc {

  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("ACC").setMaster("local[*]")
    val sc = new SparkContext(conf)

    // 声明累加器
    // Long
    val acc: LongAccumulator = sc.longAccumulator("core/acc")

    val data: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), 2)


    data.foreach(
      word => {

        //使用累加器
        acc.add(word)
      }
    )
    //    println(sum)
    println(acc.value)

    sc.stop()
  }
}
