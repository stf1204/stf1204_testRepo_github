package sql

import org.apache.spark.SparkConf
import org.apache.spark.sql._

/**
  * @ClassName: Req
  * @Author: stf
  * @Date: 2022/11/29 11:51
  * @Description:
  */
object SparkSQL11_Req_1 {

  def main(args: Array[String]): Unit = {

    // 设置当前客户端以什么身份访问
    System.setProperty("HADOOP_USER_NAME", "atguigu")

    // 1 创建上下文环境配置对象
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQLReq")

    // 2 创建SparkSession对象
    val spark: SparkSession = SparkSession
      .builder()
      .enableHiveSupport()
      .config(conf)
      .getOrCreate()

    spark.sql("use atguigu")

    // TODO 需求：各区域热门商品Top3
    //  1、先过滤，保留我们需要的点击数据
    //  2、 缺什么，补什么；多什么，去什么
    //  3、 聚合函数，获取点击数量
    //  4、 将数据按照区域进行分组，组内对数据按照点击数量降序排序，展示top3
    spark.sql(
      """
        |select *
        |from
        |(select area,product_name,cnt,rank() over(partition by area order by cnt desc) rank
        |from
        |(select area,product_name,count(*) cnt
        |from
        |(select u.*,c.city_name,c.area, p.product_name
        |from user_visit_action u
        |join city_info c on c.city_id=u.city_id
        |join product_info p on p.product_id = u.click_product_id
        |where u.click_product_id != -1)t1
        |group by area,product_name)t2)t3
        |where rank <= 3
      """.stripMargin).show()

    spark.stop()
  }


}
