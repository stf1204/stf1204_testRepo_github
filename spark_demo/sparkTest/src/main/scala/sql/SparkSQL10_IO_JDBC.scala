package sql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql._

/**
  * @ClassName: Req
  * @Author: stf
  * @Date: 2022/11/29 11:51
  * @Description:
  */
object SparkSQL10_IO_JDBC {

  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .master("local[*]")
      .appName("test")
      .getOrCreate()

    val df: DataFrame = spark.read.format("JDBC")
      .option("url", "jdbc:mysql://hadoop102:3306/gmall")
      .option("driver", "com.mysql.jdbc.Driver")
      .option("user", "root")
      .option("password", "000000")
      .option("dbtable", "user_info")
      .load()

    df.createTempView("user")

    spark.sql("select id,name from user").show()


    val rdd: RDD[User] = spark.sparkContext.makeRDD(List(User(3000, "zhangsan"),User(3001, "lisi")))

    import spark.implicits._
    val ds: Dataset[User] = rdd.toDS
    // 注意：id是主键，不能和MySQL数据库中的id重复
    ds.write
      .format("jdbc")
      .option("url", "jdbc:mysql://hadoop102:3306/gmall")
      .option("driver", "com.mysql.jdbc.Driver")
      .option("user", "root")
      .option("password", "000000")
      .option("dbtable", "user_info")
      .mode(SaveMode.Append)
      .save()



    spark.stop()
  }
  case class User(id: Int, name: String)
}
