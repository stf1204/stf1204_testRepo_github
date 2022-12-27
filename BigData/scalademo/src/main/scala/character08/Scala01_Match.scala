package character08

/**
  * @ClassName: Scala01_Match
  * @Author: stf
  * @Date: 2022/11/20 23:24
  * @Description: Scala 当中的模式匹配
  */
object Scala01_Match {

  def main(args: Array[String]): Unit = {

    val a: Int = 10
    val b: Int = 20
    val d: String = "+"

    // 方法一 if-else
    /*if (d == "+") {
      println(a + b)
    }
    else if (d == "*") {
      println(a * b)
    }
    else if (d == "-") {
      println(a - b)
    }
    else println("error...")*/

    // 方法二 match
    d match {
      case "+" => println(a + b)
      case "-" => println(a - b)
      case "*" => println(a * b)
      case "/" => println(a / b)
      case _ => println("illegal")
    }

  }

}
