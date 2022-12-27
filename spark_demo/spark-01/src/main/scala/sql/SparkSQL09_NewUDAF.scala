package sql

import org.apache.spark.sql.{Encoder, _}
import org.apache.spark.sql.expressions.Aggregator

/**
  * @ClassName: Req
  * @Author: stf
  * @Date: 2022/11/29 11:51
  * @Description:
  */
object SparkSQL09_NewUDAF {

  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .master("local[*]")
      .appName("test")
      .getOrCreate()
    val df: DataFrame = spark.read.json("data/user.json")
    df.createTempView("tmp")
    spark.udf.register("avgs", functions.udaf(new MyAvgAge))
    spark.sql("select avgs(age) from tmp").show()

    spark.stop()
  }

  case class MyBuffer(var total: Long, var Cnt: Long)

  class MyAvgAge extends Aggregator[Long, MyBuffer, Long] {
    override def zero: MyBuffer = {
      MyBuffer(0L, 0L)
    }

    override def reduce(buff: MyBuffer, age: Long): MyBuffer = {
      buff.total += age
      buff.Cnt += 1
      buff
    }

    override def merge(b1: MyBuffer, b2: MyBuffer): MyBuffer = {
      b1.total += b2.total
      b1.Cnt += b2.Cnt
      b1
    }

    override def finish(res: MyBuffer): Long = {
      res.total / res.Cnt
    }

    override def bufferEncoder: Encoder[MyBuffer] = Encoders.product

    override def outputEncoder: Encoder[Long] = Encoders.scalaLong
  }

}
