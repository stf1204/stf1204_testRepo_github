package chapter03

import scala.util.control.Breaks
import scala.util.control.Breaks._

/**
  * @ClassName: Scala_break
  * @Author: stf
  * @Date: 2022/11/14 10:05
  * @Description: break
  */
object Scala_break {

  def main(args: Array[String]): Unit = {

//    需求1：采用异常的方式退出循环
    try {
      for (i <- 0 to 22) {
        if (i >= 15) {
          throw new RuntimeException
        }
        println(i)
      }
    } catch {
      case e: RuntimeException => println("循环中断....")
    } finally {
      println("释放资源...")
    }


    //需求2：采用Scala自带的函数，退出循环
    // 中断的本质就是异常处理
    println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
    Breaks.breakable(
      for (i <- 0 to 20) {
        if (i == 15) {
          Breaks.break()
        }
        println(i)
      }
    )

    // 需求3：对break进行省略
    println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
    breakable(
      for (i <- 0 to 22) {
        if (i == 13) break()
        println(i)
      }
    )


  }
}
