package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: TODO: 几种方式实现 wordCount
  *               (1)、reduceByKey
  *               (2)、aggregateByKey
  *               (3)、foldByKey
  *               (4)、combineByKey
  */
object Spark19_RDD_Opera_Transform {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)
    val rdd1: RDD[(String, Int)] = sc.makeRDD(List(
      ("a", 1), ("a", 4), ("b", 8), ("c", 9),
      ("c", 3), ("d", 3), ("a", 4), ("b", 5)
    ), 2)


    /**
      * reduceByKey：  TODO： 区内和区间的处理逻辑相同，并且无法指定初始值
      * combineByKeyWithClassTag[V]((v: V) => v,
                                    func,
                                    func,
                                    partitioner)


      * aggregateByKey:  TODO： 区内和区间的处理逻辑可以不同，可以指定初始值
      * combineByKeyWithClassTag[U](
                    (v: V) => cleanedSeqOp(createZero(), v),
                    cleanedSeqOp,
                    combOp,
                    partitioner)
      *
      * foldByKey：  TODO： 区内和区间的处理逻辑相同，可以指定初始值
      * combineByKeyWithClassTag[V](
                    (v: V) => cleanedFunc(createZero(), v),
                    cleanedFunc,
                    cleanedFunc,
                    partitioner)
      *
      * combineByKey  TODO：区内和区间的计算逻辑不同
      * combineByKeyWithClassTag(
                    createCombiner,   TODO：第一个数值的转换
                    mergeValue,
                    mergeCombiners,
                    defaultPartitioner(self))
      */


    // TODO  1、reduceByKey 没有初始值，区内和区间计算逻辑相同
    rdd1.reduceByKey(_ + _)

    // TODO  2、aggregateByKey  有初始值，区内和区间的处理逻辑可以不相同
    rdd1.aggregateByKey(0)(_ + _, _ + _)

    // TODO  3、foldByKey 有初始值，区内和区间的处理逻辑相同
    rdd1.foldByKey(0)(_ + _)

    // TODO  4、combineByKey 有初始值，且可以改变数据格式，区内和区间的计算逻辑不同
    rdd1.combineByKey(v => v, (k1: Int, k2) => {
      k1 + k2
    }, (k1: Int, k2: Int) => {
      k1 + k2
    })
    sc.stop()

  }
}
