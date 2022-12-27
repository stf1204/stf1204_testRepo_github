package core.rdd.operate.action

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

/**
  * @ClassName: Spark01_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:43
  * @Description: RDD Action算子： reduce、aggregate、fold算子 实现 word Count
  */
object Spark07_RDD_Opera_Action {


  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("test2")
    val sc = new SparkContext(conf)

    val rdd1 = sc.makeRDD(
      List(
        ("a", 1), ("B", 1),
        ("a", 2), ("B", 3),
        ("a", 4), ("B", 7)
      ),
      2
    )

    // TODO reduce算子 实现wordCount （9 / 10）
    //  reduce map类型的数据， 相同的key进行聚合，是两个map进行操作
    //  (f: (T, T) => T): T wordCount最后结果为map集合，此方法返回的类型为传入的数据类型，所以要对数据进行改造
    val MapRDD: RDD[mutable.Map[String, Int]] = rdd1.map(kv => {
      val map = mutable.Map(kv)
      map
    })

    val res: mutable.Map[String, Int] = MapRDD.reduce((map1, map2) => {
      map2.foreach {
        case (word, cnt) => {
          val oldCnt: Int = map1.getOrElse(word, 0)
          map1.update(word, oldCnt + cnt)
        }
      }
      map1
    })
    println(res)


    // TODO aggregate算子 实现wordCount （10 / 10）
    // aggregate 可以设置初始值格式，所以不需要提前转换数据格式，只需设置初始值格式为map格式即可
    // 初始值使用一个 (map, kv)一个初始值map 与 元素中的二元组相加，
    val result: mutable.Map[String, Int] = rdd1.aggregate(mutable.Map[String, Int]())(
      (map1, kv) => {
        val value: Int = map1.getOrElse(kv._1, 0)
        map1.update(kv._1, value + kv._2)
        map1
      }, (map1, map2) => {
        map2.foreach {
          case (key, cnt) => {
            val OldCnt: Int = map1.getOrElse(key, 0)
            map1.update(key, cnt + OldCnt)
          }
        }
        map1
      }
    )
    println(result)


    // TODO fold算子 实现wordCount （11 / 10）

       val result1: mutable.Map[String, Int] = MapRDD.fold(mutable.Map[String, Int]())(
         (map1, map2) => {
           map2.foreach {
             case (key, cnt) => {
               val OldCnt: Int = map1.getOrElse(key, 0)
               map1.update(key, cnt + OldCnt)
             }
           }
           map1
         }
       )
    sc.stop()

  }
}
