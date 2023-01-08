package core.wordcount

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

/**
  * @ClassName: Spark01_Env
  * @Author: stf
  * @Date: 2022/11/21 18:40
  * @Description: Spark 连接环境
  */
object Spark05_WordCount_Ten {

  def main(args: Array[String]): Unit = {


    // 1、创建配置文件
    val conf = new SparkConf().setAppName("WordCount").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val words: RDD[String] = sc.makeRDD(List("a", "a", "b", "b", "c", "c", "d"))


    // TODO (一)、针对单值类型的数据

    // TODO  1、使用 groupBy 实现wordCount
    val word_iter: RDD[(String, Iterable[String])] = words.groupBy(word => word)
    val res1: RDD[(String, Int)] = word_iter.mapValues(_.size)
    res1.collect().foreach(println)
    println("-----------------------------------")

    // TODO 2、 使用 countByValue 实现wordCount
    val res2: collection.Map[String, Long] = words.countByValue()
    println(res2)
    println("------------------------------------")


    val words2: RDD[(String, Int)] = sc.makeRDD(List(
      ("a", 1), ("b", 1), ("a", 1), ("b", 1), ("a", 1), ("b", 1)
    ))


    // TODO (二)、针对 K-V 类型的数据

    // TODO 3、使用 reduceByKey 实现wordCount
    val res3: RDD[(String, Int)] = words2.reduceByKey(_ + _)
    res3.collect().foreach(println)
    println("--------------------------------------------")


    // TODO 4、使用 groupByKey 实现wordCount
    val word_iter2: RDD[(String, Iterable[Int])] = words2.groupByKey()
    val res4: RDD[(String, Int)] = word_iter2.mapValues(_.sum)
    res4.collect().foreach(println)
    println("--------------------------------------------")

    // TODO 5、使用 countByKey 实现wordCount
    val res5: collection.Map[String, Long] = words2.countByKey()
    println(res5)
    println("-----------------------------------------------")

    // TODO 6、使用  aggregateByKey  实现wordCount
    val res6: RDD[(String, Int)] = words2.aggregateByKey(0)(_ + _, _ + _)
    res6.collect().foreach(println)
    println("-----------------------------------------------")


    // TODO 7、使用  foldByKey 实现wordCount
    val res7: RDD[(String, Int)] = words2.foldByKey(0)(_ + _)
    res7.collect().foreach(println)
    println("-------------------------------------------------")

    //  TODO 8、使用 combineByKey 实现wordCount
    val res8: RDD[(String, Int)] = words2.combineByKey(word => word, (cnt1: Int, cnt2) => {
      cnt1 + cnt2
    }, (cnt1: Int, cnt2: Int) => {
      cnt1 + cnt2
    })
    res8.collect().foreach(println)
    println("--------------------------------------------------")



    // TODO   (三)、针对 Map类型的数据

    // TODO 9、使用 reduce 实现wordCount
    val map_data: RDD[mutable.Map[String, Int]] = words2.map {
      kv =>
        val stringToInt: mutable.Map[String, Int] = mutable.Map(kv)
        stringToInt
    }
    val res: mutable.Map[String, Int] = map_data.reduce(
      (map1, map2) => {
        map2.foreach {
          case (word, cnt) => {
            val oldCnt: Int = map1.getOrElse(word, 0)
            map1.update(word, oldCnt + cnt)
          }
        }
        map1
      }
    )
    println(res)
    println("=============================================")


    // TODO 10、使用 aggregate 实现wordCount
    val res10: mutable.Map[String, Int] = words2.aggregate(mutable.Map[String, Int]())((map1, kv) => {
      val oldCnt: Int = map1.getOrElse(kv._1, 0)
      map1.update(kv._1, oldCnt + kv._2)
      map1
    }, (map1, map2) => {
      map2.foreach(line => {
        val oldCnt: Int = map1.getOrElse(line._1, 0)
        map1.update(line._1, oldCnt + line._2)
      })
      map1
    })
    println(res10)
    println("===========================================")


    //  TODO 11、使用 fold 实现wordCount
    val res11: mutable.Map[String, Int] = map_data.fold(mutable.Map[String, Int]())((map1, map2) => {
      map2.foreach(line => {
        val oldCnt: Int = map1.getOrElse(line._1, 0)
        map1.update(line._1, oldCnt + line._2)
      })
      map1
    })
    println(res11)
    println("=============================================")


    sc.stop()
  }

}
