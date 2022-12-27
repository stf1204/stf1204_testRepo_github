package core.rdd.operate.transform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @ClassName: Spark02_RDD_Opera_Transform
  * @Author: stf
  * @Date: 2022/11/23 15:47
  * @Description: sortBy 算子     排序
  */
object Spark11_RDD_Opera_Transform_1 {


  def main(args: Array[String]): Unit = {
    val c: SparkConf = new SparkConf().setAppName("test").setMaster("local[*]")
    val sc = new SparkContext(c)

    // TODO 自定义类的排序
    val rdd: RDD[test] = sc.makeRDD(List(
      test("小明", 22, 82)
      , test("小宋", 29, 221)
      , test("小芳", 23, 41)
      , test("小贝", 25, 2251)
      , test("小花", 21, 621)
    ))
    // TODO 元组的排序， 先按照元组排第一个， 后按照元组排第二个

    rdd.sortBy(user => {
      (user.age, user.count)
    }, true).collect().foreach(println)

    sc.stop()


  }

  // 样例类 马丁帮我们生成了很多的方法
  // 特殊对象的排序， 声明case的样例类
  // 样例类就当作普通类来使用
  case class test(name: String, age: Int, count: Double) {}

}


