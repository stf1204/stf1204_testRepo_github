package sql

import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * @ClassName: Req
  * @Author: stf
  * @Date: 2022/11/29 11:51
  * @Description:   UDF
  */
object SparkSQL06_UDF {
  def main(args: Array[String]): Unit = {

    // TODO 使用SparkSQL 读取数据，展示数据
    val spark: SparkSession = SparkSession.builder().appName("UDF").master("local[*]").getOrCreate()

    val df: DataFrame = spark.read.json("data/user.json")
    df.createTempView("user")

    // 用户自定义UDF函数，一对一，类似map，每一行都会执行
    spark.udf.register("prefixName", (name: String) => {
      "Name:" + name
    })

    val f: DataFrame = spark.sql("select prefixName(name) from user")

    f.show()

    spark.stop()

  }

}
