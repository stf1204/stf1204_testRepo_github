package chapter03

import scala.collection.immutable

/**
  * @ClassName: Scala_for_append
  * @Author: stf
  * @Date: 2022/11/13 23:27
  * @Description:
  */
object Scala_for_append {

  def main(args: Array[String]): Unit = {

    //    （1）循环守卫
    // for 循环当中进行判断
    for (i <- 0 to 20) {
      if (i % 2 == 0) {
        println(i)
      } else {}
    }
    println("========================================")

    // Scala简洁写法
    for (i <- 0 to 5 if i > 3) {
      println(s"$i")
    }
    println("------------------------------------------------")

    //    （2）循环步长
    for (i <- 0 to 20 by 2) {
      println(i)
    }

    // 小数
    for (i <- 0.0 to 3 by 0.5) {
      println(i)
    }

    // 负数
    for (i <- 5 to 3 by -1) {
      println(i)
    }
    //    （3）嵌套循环
    println("========================================")
    println("汤老师，三行四列的行列式怎么算？")
    for (i <- 1 to 3) {
      for (j <- 1 to 4) {
        print(s"$i * $j = ${i * j}\t")
      }
      println()
    }

    for (i <- 1 to 3; j <- 1 to 4) {
      print(s"$i * $j = ${i * j}\t")
      if (j == 4) {
        println()
      }
    }

    //    （4）引入变量
    println("```````````````````````````````````")
    for (i <- 0 to 10) {
      var sum: Int = 0
      sum += i
      println(s"$sum + $i = " + sum)
    }


    //    （5）循环返回值
    println("`````````````````````````````````````")
    val ints: immutable.IndexedSeq[Int] = for (i <- 0 to 10) yield {
      10
    }
    for (elem <- ints) {
      println(elem)
    }

    //    （6）倒序打印
    println("=========================================")
    for (i <- 0 to 10 reverse) {
      println(i)
    }
  }

}
