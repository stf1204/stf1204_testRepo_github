package character07

/**
  * @ClassName: Scala01_Array
  * @Author: stf
  * @Date: 2022/11/18 18:30
  * @Description: Scala 集合  不可变数组
  */
// TODO : 可变不可变 指的是 集合的长度 与 类型是否可变以及是否能直接修改原始数据
object Scala01_Array {

  def main(args: Array[String]): Unit = {

    // 动态创建
    val array1: Array[Int] = new Array[Int](5)

    // 静态创建
    val array2 = Array(1, 2, 3, 4, 5)

    // 增加元素
    // 不可变数组只能通过 :+ 来进行尾部添加元素，并且添加元素以后返回新的数组
    val array3: Array[Int] = array1 :+ 200

    for (elem <- array3) {
      println(elem)
    }

    //删除元素
    println("=========================")
    for (elem <- array2) println(elem)
    val array4: Array[Int] = array2.drop(2)
    println("===========================")
    // 删除元素是从0开始，删除几个
    for (elem <- array4) println(elem)

    // 修改元素
    println("=======================")
    array4(0) = 666
    for (elem <- array4) println(elem)

    // 查询数组
    println("==================")
    for (elem <- array4) {
      println(elem)
    }
    // 获取元素
    println(array4(2))
    println("========================")

    // 1、foreach
    // 使用函数打印集合， （使用最多的方式）
    def MyPri(array: Int): Unit = {
      println(s"我是$array....")
    }

    array4.foreach(MyPri)
    println("---------------------------")
    array4.foreach((i: Int) => println(i))
    println("-------------------------")

    //简化写法
    array4.foreach(println)

    // 2、 mkString
    println(array4.mkString("_"))
    println(array4.mkString("[", "_", "]"))


  }
}
