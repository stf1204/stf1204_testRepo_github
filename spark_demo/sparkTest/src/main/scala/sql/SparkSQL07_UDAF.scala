package sql

import org.apache.spark.sql.expressions.Aggregator
import org.apache.spark.sql.{DataFrame, Encoder, Encoders, SparkSession, functions}

/**
  * @ClassName: Req
  * @Author: stf
  * @Date: 2022/11/29 11:51
  * @Description: UDAF
  */
object SparkSQL07_UDAF {
  def main(args: Array[String]): Unit = {

    // TODO 使用SparkSQL 读取数据，展示数据
    val spark: SparkSession = SparkSession
      .builder()
      .appName("UDF")
      .master("local[*]")
      .getOrCreate()

    val df: DataFrame = spark.read.json("data/user.json")
    df.createTempView("user")


    // 用户自定义UDAF聚合函数，多对一
    spark.udf.register("AvgAge", functions.udaf(new MyAvgAge))

    // 调用自定义的函数
    spark.sql("select AvgAge(age) from user").show()
    spark.stop()

  }

  // 自定义缓冲区数据格式
  case class MyBuffer(var total: Long, var cnt: Int)

  // 1、继承
  // 2、泛型
  // IN
  // OUT
  // BUFFER
  // 3、方法（4+2）
  class MyAvgAge extends Aggregator[Long, MyBuffer, Long] {
    // MyBuffer初始值
    override def zero: MyBuffer = {
      MyBuffer(0L, 0)
    }

    // MyBuffer与age聚合
    override def reduce(b: MyBuffer, age: Long): MyBuffer = {
      b.total += age
      b.cnt += 1
      b
    }

    // 分区间合并
    override def merge(b1: MyBuffer, b2: MyBuffer): MyBuffer = {
      b1.cnt += b2.cnt
      b1.total += b2.total
      b1
    }

    // 进行最后的聚合操作
    override def finish(last: MyBuffer): Long = {
      last.total / last.cnt
    }


    override def bufferEncoder: Encoder[MyBuffer] = Encoders.product

    override def outputEncoder: Encoder[Long] = Encoders.scalaLong
  }

}
