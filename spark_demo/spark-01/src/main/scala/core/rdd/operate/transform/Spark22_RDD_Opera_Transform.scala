package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: TODO: join、leftOutJoin、rightOutJoin、fullOutJoin
  */
object Spark22_RDD_Opera_Transform {

  def main(args: Array[String]): Unit = {

    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)

    val rdd1: RDD[(String, Int)] = sc.makeRDD(List(("a",1),("b",2),("c",3)))
    val rdd2: RDD[(String, Int)] = sc.makeRDD(List(("a",4),("d",5),("c",6)))

    // join-会将两张表连接在一起
    // 连接条件为：key相同，不相同的数据会直接过滤
    // 性能比较差，能不用尽量不要用，可能会产生shuffle，也可能会产生笛卡尔积。
    val joinRDD: RDD[(String, (Int, Int))] = rdd1.join(rdd2)
//    joinRDD.collect().foreach(println)

    // leftOuterJoin以左边表为主
    val joinRDD1: RDD[(String, (Int, Option[Int]))] = rdd1.leftOuterJoin(rdd2)
//    joinRDD1.collect().foreach(println)

    // rightOuterJoin以右边表为主
    val joinRDD2: RDD[(String, (Option[Int], Int))] = rdd1.rightOuterJoin(rdd2)
//    joinRDD2.collect().foreach(println)

    // fullOuterJoin会显示所有的不同数据，join不上用null填充
    val joinRDD3: RDD[(String, (Option[Int], Option[Int]))] = rdd1.fullOuterJoin(rdd2)
    joinRDD3.collect().foreach(println)

    sc.stop()
  }

}