package sql

import org.apache.spark.sql._
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, LongType, StructField, StructType}

/**
  * @ClassName: Req
  * @Author: stf
  * @Date: 2022/11/29 11:51
  * @Description:
  */
object SparkSQL09_OldUDAF_SQL {

  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .master("local[*]")
      .appName("test")
      .getOrCreate()
    val df: DataFrame = spark.read.json("data/user.json")

    df.createTempView("tmp")

    // SQL => 弱类型
    // UDAF => 强类型
    // Spark3.0 => 弱类型 + 强类型
    spark.udf.register("Myavg", new MyAvg)

    spark.sql("select Myavg(age) from tmp").show()

    spark.stop()
  }

  // 旧版本的弱类型
  class MyAvg extends UserDefinedAggregateFunction {

    // 输入数据类型
    override def inputSchema: StructType = {
      StructType(
        Array(
          StructField(
            "name", LongType
          )
        ))
    }

    // Buffer 数据类型
    override def bufferSchema: StructType = {
      StructType(
        Array(
          StructField(
            "total", LongType
          ),
          StructField(
            "cnt", LongType
          )
        ))
    }

    // 输出数据类型
    override def dataType: DataType = LongType

    // 计算稳定性
    override def deterministic: Boolean = true

    // buffer初始化
    override def initialize(buffer: MutableAggregationBuffer): Unit = {
      buffer.update(0, 0L)
      buffer.update(1, 0L)
    }

    // buffer加元素
    override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
      buffer.update(0, input.getLong(0) + buffer.getLong(0))
      buffer.update(1, buffer.getLong(1) + 1)
    }

    // 分区合并
    override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
      buffer1.update(0, buffer1.getLong(0) + buffer2.getLong(0))
      buffer1.update(1, buffer1.getLong(1) + buffer2.getLong(1))
    }

    // 计算最终结果
    override def evaluate(buffer: Row): Any = {
      buffer.getLong(0) / buffer.getLong(1)
    }
  }

}
