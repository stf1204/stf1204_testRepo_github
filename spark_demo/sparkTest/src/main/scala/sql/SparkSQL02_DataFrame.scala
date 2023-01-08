package sql

import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * @ClassName: Req
  * @Author: stf
  * @Date: 2022/11/29 11:51
  * @Description:
  */
object SparkSQL02_DataFrame {
  def main(args: Array[String]): Unit = {

    // TODO 使用SparkSQL 读取数据，展示数据

    // 获取环境对象
    // Builder:构建器模式
    val spark = SparkSession.builder
      .master("local[*]")
      .appName("SQL")
      .getOrCreate()

    //  RDD 模型出现的早，二次编译转换, 需要使用隐式转换, 顺序按照指定的顺序
    import spark.implicits._

    // 获取数据模型
    val df: DataFrame = spark.read.json("data/user.json")

    // TODO 1、SQL方式访问DataFrame
    /*df.createTempView("tmp")
    spark.sql("select name from tmp where age>22").show()*/


    // TODO 2、DSL方式访问DataFrame
    // SparkSQL中存在一些转换操作，而这些转换其实是隐式操作，所以需要隐式转换
    df.select($"age" + 10).show()

    // 使用数据模型
    // 停止
    spark.stop()
  }
}
