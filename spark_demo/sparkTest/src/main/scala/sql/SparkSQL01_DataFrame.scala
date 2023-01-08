package sql

import org.apache.spark.sql.SparkSession

/**
  * @ClassName: Req
  * @Author: stf
  * @Date: 2022/11/29 11:51
  * @Description:
  */
object SparkSQL01_DataFrame {
  def main(args: Array[String]): Unit = {

    // TODO 使用SparkSQL 读取数据，展示数据

    // 获取环境对象
    // Builder:构建器模式
    val spark = SparkSession.builder
      .master("local[*]")
      .appName("SQL")
      //.config("spark.some.config.option", "some-value")
      .getOrCreate()
    // 获取数据模型
    // 使用数据模型
    // 停止
    spark.stop()
  }
}
