package character04

/**
  * @ClassName: Scala_Func_High
  * @Author: stf
  * @Date: 2022/11/14 17:36
  * @Description: 函数高阶用法一：函数作为变量进行传递
  */
object Scala_Func_High1 {

  def main(args: Array[String]): Unit = {

    // 将函数 sayhi 函数 作为一个值 赋值给一个 变量
    // 默认值， 匿名函数化简， 函数赋值给一个变量，sayhi _  代表函数本身
    def sayHi(name: String): String = s"$name"

    val Hi = sayHi _
    val str: String = Hi("wu kong")
    println(str)

    //    val simple:String=>String = name => s"$name"
    // 匿名函数的写法
    val hi: String => String = sayHi
    println(hi("ls ii"))

    // TODO 高阶函数的第一种用法， 在实际中的体现 就是匿名函数的使用

    val simple: String => String = name => s"$name"
    println(simple("zhang san"))
  }

}
