package chapter03

/**
  * @ClassName: Scala_for
  * @Author: stf
  * @Date: 2022/11/13 23:15
  * @Description:  for循环
  */
object Scala_for_base {

  def main(args: Array[String]): Unit = {
    println("=====================")

    // scala中的for循环基础语法
    // 左闭右闭
    for (i <- 0 to 5) {
      println(s"$i...")
    }


    println("=============================")
    // 左闭右开
    for(i <- 0 until 5){
      println(s"$i...")
    }

    // for循环的本质
    // to是整数的方法  返回结果是一个集合
    // 使用变量i 循环遍历一遍 后面集合的内容
    println("=============================")
    val inclusive: Range.Inclusive = 0.to(5)
    for (elem <- inclusive) {
      println(elem+"...")
    }


    // 增强for循环
    println("=============================")
    for (i <- Array(1,2,3,4)){
      println(i)
    }

  }
}
