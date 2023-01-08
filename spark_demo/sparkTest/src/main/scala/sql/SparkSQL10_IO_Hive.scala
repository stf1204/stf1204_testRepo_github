package sql

import org.apache.spark.SparkConf
import org.apache.spark.sql._

/**
  * @ClassName: Req
  * @Author: stf
  * @Date: 2022/11/29 11:51
  * @Description:
  */
object SparkSQL10_IO_Hive {

  def main(args: Array[String]): Unit = {

    // 设置当前客户端以什么身份访问
    System.setProperty("HADOOP_USER_NAME","atguigu")

    // 1 创建上下文环境配置对象
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQLTest")

    // 2 创建SparkSession对象
    val spark: SparkSession = SparkSession
      .builder()
      .enableHiveSupport()
      .config(conf)
      .getOrCreate()

    // 3 连接外部Hive，并进行操作
    spark.sql("show tables").show()


    // 4 释放资源
    spark.stop()

  }

}
