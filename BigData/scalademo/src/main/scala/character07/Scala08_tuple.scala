package character07

/**
  * @ClassName: Scala08_tuple
  * @Author: stf
  * @Date: 2022/11/19 16:21
  * @Description: Scala 元组
  */
object Scala08_tuple {

  def main(args: Array[String]): Unit = {

    val list: List[Any] = List("name", 22, "famle")
    val value: Any = list(0)
    println(value)
    //    value.split()  //error
    // TODO list当中可以存储不同类型的数据，但是不会存储数据的类型

    // 不同类型的数据，使用元组进行存储，元组可以存储数据 和数据类型
    // 创建方式一、

    // 创建一元组
    val tuple1: Tuple1[Int] = Tuple1[Int](1)
    println(tuple1)

    val tuple2: (String, Int) = Tuple2[String, Int]("age", 22)
    // 获取二元组中的第一个元素
    println(tuple2._1)
    // 获取二元组中的第二个元素
    println(tuple2._2)
    // 获取二元组当中内容
    println(tuple2)


    // 创建方式二、
    val tuple3: (String, Int, String) = ("name",22,"famale")
    println(tuple3)

    //TODO Map本质就是一个二元组

    // TODO 小结：通常情况下元组当中的数据不会很多，超过5个会封装到一个类中

  }
}
