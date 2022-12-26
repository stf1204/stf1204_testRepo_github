package character04

/**
  * @ClassName: Scala_Func_High2
  * @Author: stf
  * @Date: 2022/11/16 11:20
  * @Description: 高阶函数的第三种用法：函数作为返回值进行传递
  */
object Scala_Func_High3 {

  def main(args: Array[String]): Unit = {

    //TODO : 高阶函数的第三种用法

    // 需求：定义一个求和函数，但是不是一次性传递两个参数，而是一个一个进行传递
    def func_xy(x: Int) = {
      // TODO 先对x进行一系列的处理

      def sum_func(y: Int): Int = {
        x + y
      }

      // 将内部的sum_func作为参数进行返回
      sum_func _
    }

    // 任何调用(传统调用方式)...
    val func_sum: Int => Int = func_xy(10)
    val res: Int = func_sum(20)
    println(res)

    // TODO 针对函数嵌套使用 推荐使用简易化的调用 （柯里化调用...currying）
    val re: Int = func_xy(10)(20)
    println(re)

    // 函数化简
    // 1、先从内部函数开始，只有一句，可省大括号
    def func_xy1(x: Int) = {
      def sum_func(y: Int): Int = x + y

      sum_func _
    }

    // 2、返回值类型可以推断出来，可省
    def func_xy2(x: Int) = {
      def sum_func(y: Int) = x + y

      sum_func _
    }

    // 3、内部函数化简为匿名函数
    def func_xy3(x: Int) = {
      (y: Int) => x + y
    }

    // 4、内部只有一句，外层大括号可省
    def func_xy4(x: Int) = (y: Int) => x + y

    // 5、外部函数化简为匿名函数
    val func: Int => Int => Int = (x: Int) => (y: Int) => x + y

    // 6、匿名函数化简 省去变量的类型（推荐适可而止，就此打住）
    val func1: Int => Int => Int = x => y => x + y

    // 7、用_替换参数，嵌套函数只有内部函数才考虑
    val func2: Int => Int => Int = x => x + _
  }
}
