package sql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

/**
  * @ClassName: Req
  * @Author: stf
  * @Date: 2022/11/29 11:51
  * @Description:
  */
object SparkSQL05_RDD_DataSet {
  def main(args: Array[String]): Unit = {

    // TODO 使用SparkSQL 读取数据，展示数据

    // 获取环境对象
    // Builder:构建器模式
    val spark = SparkSession.builder
      .master("local[*]")
      .appName("SQL")
      .getOrCreate()


    val df: DataFrame = spark.read.json("data/user.json")

    // TODO 1、RDD to DataSet  [RDD--->ds]
    val rdd: RDD[(String, Int)] = spark.sparkContext.makeRDD(List(
      ("zhangsan", 22), ("lisi", 22), ("wangwu", 22), ("zhaoliu", 22)
    ))
    val MyRdd: RDD[MyType] = rdd.map {
      case (name, age) => MyType(name, age)
    }
    import spark.implicits._
    // RDD to DS 需要转换规则
    val ds: Dataset[MyType] = MyRdd.toDS()
    //    ds.show()


    val rdd2: RDD[MyType] = ds.rdd
    rdd2.collect().foreach(println)


    spark.stop()
  }

  // 读数据是从文件当中读来的，所以默认设置为bigInt，用Int接不住
  // 且读取结果排序默认使用字典序
  case class MyType(name: String, age: Long) {}

}
