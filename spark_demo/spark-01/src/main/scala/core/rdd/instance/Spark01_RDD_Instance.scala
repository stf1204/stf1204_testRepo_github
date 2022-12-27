package core.rdd.instance

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark01_RDD_Instance
  * @Author: stf
  * @Date: 2022/11/22 21:25
  * @Description:
  */
object Spark01_RDD_Instance {

  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("test")
    val sc = new SparkContext(conf)

    // TODO 如果想要构建RDD 不能直接new


    // 1、通过环境对象帮我们构建数据模型
//     sc.textFile("xxxxx")

    // 2、通过内存集合中构建
//    parallelize： 并行，分区
    val se: Seq[Int] = Seq(1,2,3)
    sc.parallelize(se)

    sc.stop()
  }
}
