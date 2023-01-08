package core.rdd.persist

import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
  * @ClassName: Spark01_RDD_Instance
  * @Author: stf
  * @Date: 2022/11/22 21:25
  * @Description: cache、Persist 缓存
  */
object Spark02_RDD_Persist {

  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("test")
    val sc = new SparkContext(conf)

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

    // 设置缓存级别
    //    选择存储级别
    //      堆内内存： 受到Java内存管理的内存
    //      堆外内存 ：OFF_HEAP: 不受到Java虚拟机管理的内存；
    //      非堆内存：方法区，栈内存
    //      cache 与 persist 只对当前的应用有效;
    RDD3.persist(StorageLevel.MEMORY_AND_DISK)


    val RDD4: RDD[(String, Int)] = RDD3.reduceByKey(_ + _)

    val RDD5: RDD[(String, Iterable[Int])] = RDD3.groupByKey(new HashPartitioner(2))
    println(RDD4.collect().mkString(","))
    println("---------------------------------------")
    println(RDD5.collect().mkString(","))


    sc.stop()
  }
}
