package chapter03

import scala.io.StdIn

/**
  * @ClassName: Scala_if_else
  * @Author: stf
  * @Date: 2022/11/13 22:53
  * @Description: 逻辑控制
  */
object Scala_if_else {

  def main(args: Array[String]): Unit = {
    println("请输入年龄：")
    val age: Int = StdIn.readInt()

    // 正常if-else
    if (age < 18) {
      println("未成年")
    }
    else if (age >= 18 && age <= 60) {
      println("青年")
    }
    else {
      println("老年")
    }


    // 大括号可省略
    //    需求1：需求：输入年龄，如果年龄小于18岁，则输出“童年”。如果年龄大于等于18且小于等于60，则输出“中年”，否则，输出“老年”。
    if (age < 18) println("未成年")
    else if (age >= 18 && age < 60) println("青年")
    else println("老年")


    // 有返回值
    //    需求2：Scala中if else表达式其实是有返回值的，具体返回值取决于满足条件的代码体的最后一行内容。
    val unit: Unit = if (age < 18) println("未成年")
    else if (age >= 18 && age < 60) println("青年")
    else println("老年")

    // 改进
    val str: String = if (age < 18) "未成年"
    else if (age >= 18 && age < 60) "青年"
    else "老年"
    println(str)


    // 需求3：Scala中返回值类型不一致，取它们共同的祖先类型
    val res: Any = if (age < 18) "未成年"
    else if (age >= 18 && age < 60) "青年"
    else {
      100
    }


    // 需求4：Java中的三元运算符可以用if else实现
    val res2: Unit = if (age >= 18)
      println("青年")
    else
      println("老年")


  }

}
