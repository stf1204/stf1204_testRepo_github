package sql

import org.apache.spark.sql.{Encoder, _}
import org.apache.spark.sql.expressions.Aggregator

/**
  * @ClassName: Req
  * @Author: stf
  * @Date: 2022/11/29 11:51
  * @Description:
  */
object SparkSQL09_OldUDAF_Class {

  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .master("local[*]")
      .appName("test")
      .getOrCreate()
    val df: DataFrame = spark.read.json("data/user.json")

    // 旧版本当中将df对象包装为ds对象
    import spark.implicits._
    val ds: Dataset[User] = df.as[User]

    // 通过new自定义UDAF类，ds调用，进行查找
    // TODO Spark3.0之前，无法将强类型UDAF应用在弱类型SQL中
    //  ，之前都是将UDAF应用在DataFrame的另一种形态DSL中
    // 需要将UDAF的结果作为查询列,需要一个转换的过程
    val udaf = new MyAvg
    ds.select(udaf.toColumn).show()

    spark.stop()
  }

  // 从文件读取，数字默认Long类型
  case class User(name: String, age: Long)

  case class MyBuffer(var total: Long, var cnt: Long)

  // TODO 自定义UDAF函数（旧版本，强类型）
  // 继承、泛型、实现（4+2）
  class MyAvg extends Aggregator[User, MyBuffer, Long] {
    override def zero: MyBuffer = {
      MyBuffer(0L, 0L)
    }

    override def reduce(buff: MyBuffer, user: User): MyBuffer = {
      buff.total += user.age
      buff.cnt += 1
      buff
    }

    override def merge(b1: MyBuffer, b2: MyBuffer): MyBuffer = {
      b1.total += b2.total
      b1.cnt += b2.cnt
      b1
    }

    override def finish(res: MyBuffer): Long = {
      res.total / res.cnt
    }

    override def bufferEncoder: Encoder[MyBuffer] = Encoders.product

    override def outputEncoder: Encoder[Long] = Encoders.scalaLong
  }

}
