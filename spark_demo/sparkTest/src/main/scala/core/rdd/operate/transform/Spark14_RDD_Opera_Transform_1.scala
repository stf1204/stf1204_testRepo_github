package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{Partitioner, SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: partitionBy算子  分区
  */
object Spark14_RDD_Opera_Transform_1 {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)

    val rdd1: RDD[(String, Int)] = sc.makeRDD(List(("nba", 1), ("cba", 2), ("mlgb", 3), ("nba", 4)))

    // TODO : 自己定义分区规则
    // nba => 0
    // wba => 1
    // cba => 2;
    rdd1.partitionBy(new MyPartitioner).saveAsTextFile("output")

    sc.stop()

  }

  //TODO 自定义分区器对象
  // 继承类： Partitioner
  class MyPartitioner extends Partitioner {

    // TODO 分区数量
    override def numPartitions: Int = 3

    // TODO 根据key的值 指定 确定分区
    override def getPartition(key: Any): Int = {
      key match {
        case "nba" => 0
        case "mlgb" => 1
        case "cba" => 2
      }
    }
  }

}
