package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: flatMap 扁平化map
  */
object Spark05_RDD_Opera_Transform_2 {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)

    // TODO 同一个flatMap中可以返回不同的容器类型（根据元素的情况）
    // 偏函数， 全函数
    // 针对不同的数据类型进行不同的操作，可以改变数据的数量
    val value: RDD[Any] = sc.makeRDD(List(List(1, 2, 3), 4, List(5, 6, 7)))

    //  TODO  当传入的集合中有不同的数据类型的时候，可以使用case模式匹配进行操作
    value.flatMap {
      // TODO  flatMap要求返回的是一个容器类型
      // TODO 如果遍历循环的元素本身有类型，可以返回元素本身，默认使用元素本身的类型
      case list: List[_] => list
      // TODO 如果元素不是容器类型，需要对非容器累类型进行包装，包装为容器类型
      case num: Int => Seq(num)
    }.collect().foreach(println)

    sc.stop()

  }

}
