package character04

/**
  * @ClassName: Scala_Method_Simple
  * @Author: stf
  * @Date: 2022/11/14 16:14
  * @Description: 匿名函数化简
  */
object Scala_Method_Simple {

  def main(args: Array[String]): Unit = {

    def sayHi(name: String = "小芳", age: Int = 22): Unit = {
      println(s"$name $age")
    }

    sayHi()

    val function: (String, Int) => Unit = (name, age) => println(s"$name 今年 $age 岁了")
    function("bajie", 22)


    //    （1）参数的类型可以省略，会根据形参进行自动的推导
    val function1: (String, Int) => Unit = (name, age) => println(s"$name $age")
    function1("wukong", 31)

    //    （2）类型省略之后，只有一个参数时，则圆括号可以省略；其他情况：没有参数和参数超过1的永远不能省略圆括号。
    val functions: String => Unit = name => println(s"$name")
    functions("sha seng")

    val functions1: (String, Int) => Unit = (name, age) => println(s"$name $age")
    functions1("bai long ma", 21)

    //    （3）匿名函数如果只有一行，则大括号也可以省略
    val functions2: (String, Int) => Unit = (name, age) => println(s"$name $age")
    functions2("bai long ma", 21)

    //    （4）如果参数只出现一次，且按照顺序出现则参数省略且后面参数可以用_代替
    val functions3: (Int, Int) => Int = (_ + _)
    val i: Int = functions3(11, 21)
    println(i)

    val functions4: (String, Int) => String = ("姓名：" + _ + "\t年龄：" + _)
    val str: String = functions4("小芳", 21)
    println(str)

    //    不能化简为下划线的情况： 1.化简之后只有一个下划线  2.化简后的函数存在嵌套
    val f1: (Int, Int) => Int = (_ + _)
    val iiii: Int = f1(3, 2)
    println(iiii)


    // 存在歧义，val是为变量定义，而变量定义设置默认值就是_
    // 1、化简之后只有一个下划线
    //    val f2:(Int)=>Int = _
    //    f1(3)


    //    2.化简后的函数存在嵌套
    //    如果化简的下划线在函数里面  也会报错
//        val func1: String => Unit = println(_ + "hi")
    val func: String => Unit = println
    func("li lin")

    val unit: String => String = (name:String) => s"$name"
    println(unit("zhang san"))
  }

}
