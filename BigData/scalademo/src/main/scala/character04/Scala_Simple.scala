package character04

/**
  * @ClassName: Scala_Simple
  * @Author: stf
  * @Date: 2022/11/14 15:48
  * @Description: 函数化简
  */
object Scala_Simple {

  def main(args: Array[String]): Unit = {
    //    （1）return可以省略，Scala会使用函数体的最后一行代码作为返回值
    def sayHi(name: String = "小芳", age: Int = 22): Unit = {
      println(s"$name 今年$age 岁了")
    }

    sayHi()

    //    （2）如果函数体只有一行代码，可以省略花括号
    def sayHi1(name: String, age: Int): Unit = println(s"$name 今年 $age 岁了")

    sayHi1("小芳", 22)

    //    （3）返回值类型如果能够推断出来，那么可以省略（:和返回值类型一起省略）
    def sayHi2(name: String, age: Int) = println(s"$name 今年 $age 岁了")

    sayHi2("小芳", 22)

    //    特别注意事项：
    //    （4）如果有return，则不能省略返回值类型，必须指定
    def sayHi3(n: Int, a: Int): Int = return n + a

    println(sayHi3(11, 22))

    //补充  特殊情况下return 不能省略，if逻辑判断中
    def  sayHi0(name: String, age: Int): String ={
      if (name==null || "".equals(name)){
        return "空串"
      }
      s"$name,hi 今年$age 岁了嘛？"
    }
    val str: String = sayHi0("芳芳",22)
    println(str)

    //    （5）如果函数明确声明unit，那么即使函数体中使用return关键字也不起作用
    def sayHi4(n: Int, a: Int): Unit = return n + a

    println(sayHi4(11, 22))

    //    （6）Scala如果期望是无返回值类型，可以省略等号,但无法省略{}了
    def sayHi5(name: String, age: Int) {
      println(s"$name 今年 $age 岁了")
    }

    sayHi5("小芳", 22)

    //    （7）如果函数无参，但是声明了参数列表，那么调用时，小括号，可加可不加
    def hi() {
      println("hi...")
    }

    hi()
    hi

    //    （8）如果函数没有参数列表，那么小括号可以省略，调用时小括号必须省略
    def hi1 {
      println("hi1...")
    }

    hi1
    //    修改为匿名函数：
    //    （9）如果不关心名称，只关心逻辑处理，那么函数名（def）可以省略
    val function0: String => Unit = (name: String) => println(s"$name ")

    function0("小芳")


    val function1: (String, Int) => Unit = (name: String, age: Int) => println(s"$name 今年 $age 岁了")

    function1("小芳", 22)
  }
}
