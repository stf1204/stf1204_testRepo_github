package sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.{Encoder, _}
import org.apache.spark.sql.expressions.Aggregator

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * @ClassName: Req
  * @Author: stf
  * @Date: 2022/11/29 11:51
  * @Description:
  */
object SparkSQL11_Req_3 {

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
    spark.sql(
      """
        |select  u.*,c.area,c.city_name,p.product_name
        |from user_visit_action u
        |join city_info c on u.city_id = c.city_id
        |join  product_info p on u.click_product_id = p.product_id
        |where u.click_product_id != -1
      """.stripMargin).createTempView("t1")
    //  2、 缺什么，补什么；多什么，去什么
    spark.udf.register("CityRemark", functions.udaf(new CityRemarkUDAF))
    spark.sql(
      """
        |select area,product_name,count(*) cnt,CityRemark(city_name) remark
        |from t1
        |group by area,product_name
      """.stripMargin).createTempView("t2")
    //  3、 聚合函数，获取点击数量
    spark.sql(
      """
        |select *,rank() over(partition by area order by cnt desc) rank
        |from t2
      """.stripMargin).createTempView("t3")
    //  4、 将数据按照区域进行分组，组内对数据按照点击数量降序排序，展示top3
    spark.sql(
      """
        |select * from
        |t3 where rank <= 3
      """.stripMargin).show(false)
    // TODO：false 表示展示的时候不截断

    spark.stop()
  }


  // TODO 自定义城市备注UDAF
  //  1、继承aggregator类
  //  2、定义泛型
  //  IN：String（城市名称）
  //  Buffer：（total，cityMap）
  //  OUt：String（备注）
  //  3、重写方法（4 + 2）
  case class MyBuffer(var total: Long, var myMap: mutable.Map[String, Long])

  class CityRemarkUDAF extends Aggregator[String, MyBuffer, String] {

    // buffer赋初值
    override def zero: MyBuffer = {
      MyBuffer(0L, mutable.Map())
    }

    // buffer增加数据
    override def reduce(buff: MyBuffer, city: String): MyBuffer = {
      buff.total += 1L
      val map: mutable.Map[String, Long] = buff.myMap
      val oldCnt: Long = map.getOrElse(city, 0L)
      map.update(city, oldCnt + 1L)
      buff
    }

    // 分区间合并
    override def merge(b1: MyBuffer, b2: MyBuffer): MyBuffer = {
      b1.total += b2.total
      b2.myMap.foreach {
        case (city, cnt2) => {
          val map1: mutable.Map[String, Long] = b1.myMap
          val oldCnt: Long = map1.getOrElse(city, 0L)
          map1.update(city, oldCnt + cnt2)
        }
      }
      b1
    }

    // 最后结果组装
    override def finish(res: MyBuffer): String = {
      // 创建listBuffer，只需要将数据放入集合，用"， "进行拼接，即可获得展示结果
      val list: ListBuffer[String] = ListBuffer[String]()

      val total: Long = res.total
      val map: mutable.Map[String, Long] = res.myMap
      var init = 100L
      val top2: List[(String, Long)] = map.toList.sortBy(_._2)(Ordering.Long.reverse).take(2)


      top2.foreach {
        case (city, cnt) => {
          // *100 是因为分子太少，无法展示，此处只进行简单展示
          val res: Long = cnt * 100 / total
          init -= res
          list.append(s"${city} ${res}%")
        }
      }

      if (map.size > 2) {
        list.append(s"其他 ${init}%")
      }

      // 从listBuffer中取数据，进行最终结果的拼接
      list.mkString(", ")
    }

    override def bufferEncoder: Encoder[MyBuffer] = Encoders.product

    override def outputEncoder: Encoder[String] = Encoders.STRING
  }


}
