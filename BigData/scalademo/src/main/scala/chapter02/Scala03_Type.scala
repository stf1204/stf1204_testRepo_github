package chapter02

/**
  * @ClassName: Scala03_Type
  * @Auther: stf
  * @Date: 2022/11/13 12:10
  * @Description:
  */
object Scala03_Type {

  def main(args: Array[String]): Unit = {


    // 所有的代码都是代码块
    // 表示运行一段代码  同时将最后一行的结果作为返回值
    // 千万不要写return
    val t: Int = {
      println("我是代码块1...")
      10 + 10
    }


    // 代码块为1行的时候  大括号可以省略
    val sum: Int = 10 + 10


    // 如果代码块没有计算结果  返回类型是unit
    val unit3: Unit = {
      println("我是代码块2...")
      println("hello")
    }
    println("aaa")
    // 当代码块没办法完成计算的时候  返回值类型为nothing
    //    val value: Nothing = {
    //      println("我是代码块3...")
    //      throw new RuntimeException
    //    }

    println("我无法执行了...")
  }

  val test1: Byte = 127
  //  val test2:Byte = 128  //error

//    val test3: Byte = test1 + 1
//    println(test3);

  val test4 = 100000L

  // 浮点数介绍
  // 默认使用double
  val test5 = 11.2

  // 如果使用float 在末尾添加f
  val test6 = 3.2f

  val test7:Boolean = true
  val test8:Boolean = false

}
