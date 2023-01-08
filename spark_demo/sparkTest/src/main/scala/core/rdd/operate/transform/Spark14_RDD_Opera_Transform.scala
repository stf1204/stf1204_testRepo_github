package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: partitionBy算子  分区
  */
object Spark14_RDD_Opera_Transform {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)

    val rdd1: RDD[Int] = sc.makeRDD(List(1, 4, 3, 6), 2)


    // TODO 分区 partitionBy

    // TODO rePartition可以改变分区数，侧重点在改变分区的个数
    // TODO PartitionBy 也可以改变数据的分区数，侧重点在改变数据的分布位置
    // partitionBy需要传入一个参数，分区器对象

    // Partitioner 是一个抽象类， 需要构建子类， 默认Spark提供了三个子类
    // 1、HashPartitioner 默认使用这个分区器，Spark中的分区规则默认都是使用这个分区器规则。
    // 2、RangePartitioner 范围分区器
    // 3、PythonPartitioner 只能类内部使用，我们无法使用
    rdd1.saveAsTextFile("output1")
    rdd1.map((_, 1)).partitionBy(new HashPartitioner(2)).saveAsTextFile("output2")


    //    排序: RangePartitioner 对数据有要求，数据需要能排序，使用不方便


    // HashMap 扩容为什么会2倍2倍的扩容呢: 默认数据的存放策略
    // 位的与运算（&）与模运算（%）
    // 扩容两倍才能保证 最低的几位都是1 ， 保证位运算之后的数据是连续的
    // % 运算可以不考虑数据的分区数量 也能保证数据均衡；

    /*
    HashMap		    => hash(key.HashCode) & (length-1)
    HashPartitioner => key.hashCode  % length

    01110000
  &
    00001111
    */

    sc.stop()

  }

}
