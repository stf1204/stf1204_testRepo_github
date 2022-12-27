package character07

import scala.collection.mutable

/**
  * @ClassName: Scala07_Map
  * @Author: stf
  * @Date: 2022/11/19 12:16
  * @Description: Scala 集合 Map
  */
object Scala07_Map {

  def main(args: Array[String]): Unit = {
    //  TODO 创建  不可变Map
    // 方式1.
    val map1: Map[String, Any] = Map(("name", "小明"), ("age", 22), ("gender", "男"))
    map1.foreach(println)

    // 方式2.
    val map2: Map[String, Any] = Map(("name" -> "小芳"), ("age" -> 22), ("gender" -> "nan"))
    println(map2)
    println(map2.getOrElse("name", null))
    println(map2.getOrElse("name", 0))
    println(map2.getOrElse("gender", "男"))
    println("--------------------------------------")

    // getOrElse 查询指定内容，没有的话返回默认值
    println(map1.getOrElse("name", null))
    println(map1.getOrElse("age", 0))
    println("=============================")

    // 遍历Map集合
    for (elem <- map1) {
      println(elem)
      // 获取key
      println(elem._1)
      // 获取value
      println(elem._2)
    }
    println("==============================")
    val op1: Option[Any] = map1.get("name")
    val name: Any = op1.getOrElse(0)
    println(name)

    // 先进行判断，再进行具体操作
    if (!op1.isEmpty) {
      println(op1.get)
    }

    // 添加元素
    val map4: Map[String, Any] = map1 + (("grade", 6))
    println(map4)
    println("=============================")


    // TODO  创建 可变Map    hashMap
    val map11: mutable.HashMap[String, Int] = new mutable.HashMap[String, Int]()
    val map112: mutable.HashMap[String, Any] = new mutable.HashMap[String, Any]()
    println(map11)

    // 增加数据
    map112.put("age", 22)
    map112.put("sex", "girl")
    map112.put("name", "小芳")
    println(map112)

    map11.put("age", 22)
    map11.put("grade", 2)
    map11.put("score", 92)
    println(map11)
    println("=============================")

    // 修改元素
    map11.update("age", 21)
    println(map11)
    println("===========================================")


    // 删除操作
    //    val option: Option[Int] = map11.remove("score")
    //获取删除的元素
    val option: Int = map11.remove("score").get
    println(option)
    println(map11)

  }
}
