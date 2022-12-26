package sql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.expressions.Aggregator
import org.apache.spark.sql.{DataFrame, Dataset, Encoder, Encoders, Row, SparkSession, functions}

/**
  * @ClassName: Req
  * @Author: stf
  * @Date: 2022/11/29 11:51
  * @Description:
  */
object SparkSQL08_A {

  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .master("local[*]")
      .appName("test")
      .getOrCreate()
    val df: DataFrame = spark.read.json("data/user.json")

    // TODO 一、 DataFrame 使用方法
    // method1
    df.createTempView("tmp")
    spark.sql("select * from tmp") //.show()

    // method2
    df.select("name") //.show()


    //  TODO 二、DataFrame DataSet RDD 三者转换关系
    val df_to_rdd: RDD[Row] = df.rdd
    //    df_to_rdd.collect().foreach(println)

    // 模型之间的转换都是隐式的
    import spark.implicits._
    // 将列按照字典序排序
    val df_to_ds: Dataset[MyClass] = df.as[MyClass]
    //    df_to_ds.show()

    // 将列按照字典序排序
    val ds_to_df: DataFrame = df_to_ds.toDF()
    //    ds_to_df.show()

    val rdd: RDD[(String, Int)] = spark.sparkContext.makeRDD(List(
      ("zhangsan", 11),
      ("lisi", 12),
      ("zhaoliu", 22)
    ))
    val rdd_to_df: DataFrame = rdd.toDF("name", "age")
    //    rdd_to_df.show()


    val rdd_Type: RDD[MyClass] = rdd.map { case (name, age) => {
      MyClass(name, age)
    }
    }
    val rdd_to_ds: Dataset[MyClass] = rdd_Type.toDS()
    //    rdd_to_ds.show()

    val ds_to_rdd: RDD[MyClass] = rdd_to_ds.rdd
    //    ds_to_rdd.collect().foreach(println)


    //TODO: UDF & UDAF

    spark.udf.register("MyName", (name: String) => {
      "MyName==>" + name
    })
    spark.udf.register("avgAge", functions.udaf(new avgFunc))

    spark.sql("select MyName(name) from tmp").show()
    spark.sql("select avgAge(age) from tmp").show()

    spark.stop()
  }

  // 此处注意从文件中读来数据为BigInt类型，Int放不下
  case class MyClass(var name: String, var age: Long) {}

  // 自定义缓冲区类型
  case class MyCnt(var total_age: Long, var count: Int) {}

  // 自定义求平均函数
  class avgFunc extends Aggregator[Long, MyCnt, Long] {
    override def zero: MyCnt = {
      new MyCnt(0L, 0)
    }

    override def reduce(b: MyCnt, age: Long): MyCnt = {
      b.total_age += age
      b.count += 1
      b
    }

    override def merge(b1: MyCnt, b2: MyCnt): MyCnt = {
      b1.total_age += b2.total_age
      b1.count += b2.count
      b1
    }

    override def finish(res: MyCnt): Long = {
      res.total_age / res.count
    }


    override def bufferEncoder: Encoder[MyCnt] = Encoders.product

    override def outputEncoder: Encoder[Long] = Encoders.scalaLong
  }

}
