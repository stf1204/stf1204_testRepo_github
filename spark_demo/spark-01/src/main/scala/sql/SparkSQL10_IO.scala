package sql

import org.apache.spark.sql._

/**
  * @ClassName: Req
  * @Author: stf
  * @Date: 2022/11/29 11:51
  * @Description:
  */
object SparkSQL10_IO {

  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .master("local[*]")
      .appName("test")
      .getOrCreate()
    val df: DataFrame = spark.read.json("data/user.json")

    // TODO spark默认读取和保存的数据类型是parquet类型
    // Spark提供了通用的数据读取方法df.read.load
    // Spark提供了通用的数据存储方法df.write.save
    // 读取数据的目的就是为了构建数据模型

    val df1: DataFrame = spark.read.load("data/users.parquet")

    val df2: DataFrame = spark.read.format("json").load("data/user.json")
//    df2.show()

//    df2.write.save("output")
    // mode 可以修改保存的模式
//    df2.write.mode("overwrite").save("output")
    df2.write.mode("append").save("output")

    spark.stop()
  }

}
