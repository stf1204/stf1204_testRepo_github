package core.wordcount

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark01_Env
  * @Author: stf
  * @Date: 2022/11/21 18:40
  * @Description:  Spark 连接环境
  */
object Spark02_WordCount {

  def main(args: Array[String]): Unit = {

    /*val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("test")
    val sc = new SparkContext(conf)
    val words: RDD[String] = sc.textFile("data/words.txt")
    val word: RDD[String] = words.flatMap(_.split(" "))
    val value: RDD[(String, Iterable[String])] = word.groupBy(word=>word)
    val values: RDD[(String, Int)] = value.mapValues(_.size)
    values.foreach(println)*/

    // 1、创建配置文件
    val conf = new SparkConf().setAppName("WordCount").setMaster("local[2]")
    val sc = new SparkContext(conf)

    // 2、读取文件
    val file: RDD[String] = sc.textFile("data/words.txt")

    // 3、对信息 进行处理
    val words: RDD[String] = file.flatMap(_.split(" "))

    // 4、分组
    val word_tup: RDD[(String, Iterable[String])] = words.groupBy(word=>word)

    // 5、计数
    val value: RDD[(String, Int)] = word_tup.map {
      x => (x._1, x._2.size)
    }
    // 6、 遍历打印
    value.collect().foreach(println)

    sc.stop()
  }

}
