package sql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

/**
  * @ClassName: Req
  * @Author: stf
  * @Date: 2022/11/29 11:51
  * @Description:
  */
object SparkSQL03_RDD_DataFrame {
  def main(args: Array[String]): Unit = {

    // TODO 使用SparkSQL 读取数据，展示数据

    // 获取环境对象
    // Builder:构建器模式
    val spark = SparkSession.builder
      .master("local[*]")
      .appName("SQL")
      .getOrCreate()


    val df: DataFrame = spark.read.json("data/user.json")

    // 1、DataFrame TO RDD
    // df中是结构化的数据，所以转换以后，不关注列，泛型为row
    val rdd: RDD[Row] = df.rdd
//    rdd.collect().foreach(println)


    // spark为当前环境对象的名称，spark中存在大量的隐式转换，需要引入转换规则
    //  RDD 模型出现的早，二次编译转换, 需要使用隐式转换, 顺序按照指定的顺序
    import spark.implicits._

    //2、RDD TO DataFrame
    val test: RDD[(String, Int)] = spark.sparkContext.makeRDD(List(
      ("zhangsan", 18),
      ("lisi", 22),
      ("wangwu", 28)
    ))
    // RDD转DataFrame需要声明数据的结构信息
    // RDD本身只关注数据，不关注数据的结构信息
    val df2: DataFrame = test.toDF("name","age")
    df2.show()

    spark.stop()
  }
}
