package character04

import scala.annotation.tailrec

/**
  * @ClassName: Scala_Recursion
  * @Author: stf
  * @Date: 2022/11/16 21:03
  * @Description:  递归与闭包的关系
  */
object Scala_Recursion {

  // 闭包
  //TODO : 闭包就是Scala中针对函数嵌套场景的一种优化,如果内部函数使用了外部函数的变量,正常情况下,会导致外部函数进行压栈如果嵌套比较频繁，而且业务逻辑复杂，可能造成外部函数无法弹栈，照此分析,就会存在栈溢出的问题。但是Scala中有闭包优化处理，如果出现以上场景，scala就会自动将外部变量进行常量化,将内部函数和外部函数变量的绑定关系解除。

  // TODO 闭包的具体应用场景： （高阶函数的第三种用法）
  // 什么情况下回出现闭包---> 函数嵌套使用的时候--> 高阶函数的第三种用法
  // TODO 高阶函数第三种用法场景： 具有强调适用性比较强场景， 通常使用函数嵌套， 将内部函数作为返回结果来使用

  def main(args: Array[String]): Unit = {

    // TODO Scala种计算5的阶乘

    //使用循环
    var sum = 1
    val n = 5
    for (i <- 1 to n) {
      sum *= i
    }
    println(sum)


    /**
      * 实现递归的必要条件
      * 1、需要调用自身
      * 2、要有跳出条件
      * 3、递归中传入的参数一定要有规律
      * 4、一定要手动指定返回值类型
      *
      * @param i
      * @return
      */

    //使用递归

    def func(i: Int): Int = {
      if (i <= 1) 1
      else func(i - 1) * i
    }

    println(func(5))


    // 上边实现的递归与Java中实现的递归逻辑上没有区别
    // 存在问题： 递归中是函数嵌套使用， 会有函数压栈问题， 会有栈溢出错误的可能

    // 2.  造成压栈问题原因： 当调用内部函数 ， 由于内部函数使用了外部函数的变量
    //    Scala中本来有闭包环境可以解决压栈问题， 但是内部函数中在执行的时候，由于函数外部又 * n1
    //    所以 整体无法形成闭包， 最终导致压栈问题

    // 3. 解决方案： 使用伪递归优化
    //    思路： 人为干预， 让他形成闭包环境



    // 使用尾递归
    @tailrec //注解  用来检查是否是尾递归
    def func1(n1: Int, res: Int = 1): Int = {
      if (n1 <= 1) res
      else func1(n1 - 1, res * n1)
    }

    println(func1(10))
  }
}
