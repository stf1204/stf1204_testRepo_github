package sql

import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

/**
  * @ClassName: Req
  * @Author: stf
  * @Date: 2022/11/29 11:51
  * @Description:
  */
object SparkSQL04_DataFrame_DataSet {
  def main(args: Array[String]): Unit = {

    // TODO 使用SparkSQL 读取数据，展示数据

    // 获取环境对象
    // Builder:构建器模式
    val spark = SparkSession.builder
      .master("local[*]")
      .appName("SQL")
      .getOrCreate()


    val df: DataFrame = spark.read.json("data/user.json")
    //df.show()
    df.createTempView("user")
//    spark.sql("select * from user").show()


    // df to ds 需要用到转换规则
    import spark.implicits._

    // TODO 1、DataFrame TO DataSet  [df-> ds]
    val ds: Dataset[MyType] = df.as[MyType]
//    ds.show()

    // TODO 2、DataSet TO DataFrame   [ds-> df]
    val f: DataFrame = ds.toDF()

    // rename
    val f1: DataFrame = ds.toDF("a1","n1")
    f1.show()

    spark.stop()
  }

  // 读数据是从文件当中读来的，所以默认设置为bigInt，用Int接不住
  // 且读取结果排序默认使用字典序
  case class MyType(name: String, age: Long) {}

}
