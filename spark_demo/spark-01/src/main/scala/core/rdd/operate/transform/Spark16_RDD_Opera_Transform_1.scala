package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: reduceByKey、groupBy、groupByKey 算子  聚合函数
  */
object Spark16_RDD_Opera_Transform_1 {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)
    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("flume", 1), ("spark", 2), ("hadoop", 2), ("flume", 22), ("hadoop", 3)), 2)

    // TODO reduceByKey 将数据集中的数据，根据key进行两两聚合，并且存在shuffle，可以指定分区数
    // 分布式计算存在分区内和分区间的计算，二者计算逻辑相同
    // 数据分区采用默认的 hashPartitoner


    // -----画图 groupbykey 与 reducebykey 哪一个效率更高
    // shuffle的性能决定了当前作业的性能
    //    优化shuffle =>	 1.更换硬件
    //                    2. 增加shuffle的缓冲区
    //                    3. 保证数据结果不变的情况下，减少shuffle的次数
    //   					           【小文件传输慢，时间花在了打开\关闭文件上了】
    //                    4. 保证数据结果不变的情况下，减少传输数据量


    //    TODO groupBy
    rdd.groupBy(_._1, numPartitions = 2).foreach(println)
    //        (hadoop,CompactBuffer((hadoop,2), (hadoop,3)))
    //        (flume,CompactBuffer((flume,1), (flume,22)))
println("============================")
    //    TODO groupByKey
    rdd.groupByKey(new HashPartitioner(2)).foreach(println)
    //    (hadoop,CompactBuffer(2, 3))
    //    (flume,CompactBuffer(1, 22))
    println("============================")


    //    TODO reduceByKey
    //    (hadoop,5)
    //    (flume,23)
    rdd.reduceByKey(_ + _).collect().foreach(println)
    println("============================")


    sc.stop()

  }

}
