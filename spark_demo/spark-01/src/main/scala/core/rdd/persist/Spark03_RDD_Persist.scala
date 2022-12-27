package core.rdd.persist

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
  * @ClassName: Spark01_RDD_Instance
  * @Author: stf
  * @Date: 2022/11/22 21:25
  * @Description: CheckPoint 缓存
  */
object Spark03_RDD_Persist {

  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("test")
    val sc = new SparkContext(conf)

    sc.setCheckpointDir("cp")

    val RDD1: RDD[String] = sc.makeRDD(List(
      "hello spark", "hello scala"
    ), 2
    )
    val RDD2: RDD[String] = RDD1.flatMap(_.split(" "))

    val RDD3: RDD[(String, Int)] = RDD2.map { x =>
      println("####")
      (x, 1)
    }

    // TODO 利用缓存

    //    RDD3.cache()
    //    RDD3.persist()

    // TODO 设置检查点，检查路径
    // 报错： Checkpoint directory has not been set in the SparkContext
    // 检查点文件一般设置为分布式存储系统，存在 HDFS 中
    // 检查点与 cache 一般联合使用： 将结果存在缓存里边， 检查点文件执行的时候会使用缓存，速度快一点;
    RDD3.cache()
    RDD3.checkpoint()


    val RDD4: RDD[(String, Int)] = RDD3.reduceByKey(_ + _)

    val RDD5: RDD[(String, Iterable[Int])] = RDD3.groupByKey(new HashPartitioner(2))
    println(RDD4.collect().mkString(","))
    println("---------------------------------------")
    println(RDD5.collect().mkString(","))


    sc.stop()
  }
}
