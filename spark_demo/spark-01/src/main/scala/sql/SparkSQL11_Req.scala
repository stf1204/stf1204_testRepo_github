package sql

import org.apache.spark.SparkConf
import org.apache.spark.sql._

/**
  * @ClassName: Req
  * @Author: stf
  * @Date: 2022/11/29 11:51
  * @Description:
  */
object SparkSQL11_Req{

  def main(args: Array[String]): Unit = {

    // 设置当前客户端以什么身份访问
    System.setProperty("HADOOP_USER_NAME","atguigu")

    // 1 创建上下文环境配置对象
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQLReq")

    // 2 创建SparkSession对象
    val spark: SparkSession = SparkSession
      .builder()
      .enableHiveSupport()
      .config(conf)
      .getOrCreate()

    spark.sql("use atguigu")

    // TODO 创建表，加载数据

    spark.sql(
      """
        |CREATE TABLE `user_visit_action`(
        |  `date` string,
        |  `user_id` bigint,
        |  `session_id` string,
        |  `page_id` bigint,
        |  `action_time` string,
        |  `search_keyword` string,
        |  `click_category_id` bigint,
        |  `click_product_id` bigint, --点击商品id，没有商品用-1表示。
        |  `order_category_ids` string,
        |  `order_product_ids` string,
        |  `pay_category_ids` string,
        |  `pay_product_ids` string,
        |  `city_id` bigint --城市id
        |)
        |row format delimited fields terminated by '\t'
      """.stripMargin)

    spark.sql(
      """
        |CREATE TABLE `city_info`(
        |  `city_id` bigint, --城市id
        |  `city_name` string, --城市名称
        |  `area` string --区域名称
        |)
        |row format delimited fields terminated by '\t'
      """.stripMargin)

    spark.sql(
      """
        |CREATE TABLE `product_info`(
        |  `product_id` bigint, -- 商品id
        |  `product_name` string, --商品名称
        |  `extend_info` string
        |)
        |row format delimited fields terminated by '\t'
      """.stripMargin)

    spark.sql(
      """
        |load data local inpath 'data/user_visit_action.txt' into table user_visit_action
      """.stripMargin)

    spark.sql(
      """
        |load data local inpath 'data/product_info.txt' into table product_info
      """.stripMargin)

    spark.sql(
      """
        |load data local inpath 'data/city_info.txt' into table city_info
      """.stripMargin)

    spark.sql("select * from city_info").show()

    spark.stop()
  }

}
