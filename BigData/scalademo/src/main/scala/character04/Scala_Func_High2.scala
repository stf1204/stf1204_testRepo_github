package character04

/**
  * @ClassName: Scala_Func_High2
  * @Author: stf
  * @Date: 2022/11/16 11:20
  * @Description: 高阶函数的第二种用法：函数作为参数进行传递
  */
object Scala_Func_High2 {

  def main(args: Array[String]): Unit = {

    //TODO : 高阶函数的第二种用法规则

    // 需要一个求和功能
    def sumAB(a: Int, b: Int): Int = {
      a + b
    }

    val i: Int = sumAB(10, 11)
    println(i)



    // 思考：如何将函数变得灵活，计算加法，乘法，除法，取余等等操作
    // 传统：我一个一个去定义
    // 最佳：将具体的计算逻辑封装为函数体现，定义一个模板函数去调用


    def OperaAB(a: Int, b: Int, func: (Int, Int) => Int): Int = {
      func(a, b)
    }

    // 调用封装好的模板函数
    val res: Int = OperaAB(10, 12, (a: Int, b: Int) => a + b)
    println(res)

    val res2: Int = OperaAB(11, 10, (a: Int, b: Int) => a - b)
    println(res2)

    // 化简操作
    val res3: Int = OperaAB(10, 12, (a, b) => a + b)
    println(res3)

    // 最简形式
    val res4: Int = OperaAB(10, 12, _ + _)
    println(res4)


    // TODO：结合实际使用的框架技术，来说明第二种用法的真正意义

    // 定义一个Mapper函数，继承MapReduce的Map框架，重写map方法，进行具体的逻辑处理
    // 定义一个Reducer函数，继承MapReduce的Reduce框架，重写reducer方法，进行具体的逻辑处理
    // 定义driver 进行逻辑控制


    // TODO MapReduce是基于面向对象的思想编写出来的，如何使用基于函数式编程，写一个MapReduce程序

    //步骤一：封装整体的流程
    def mapReduce(data: String, map: String => Int, reduce: Int => String): String = {
      // 进行执行的流程
      println("Mapper端处理...")
      val len: Int = map(data)
      println("Shuffle处理...")
      println("Mapper端处理...")
      val str: String = reduce(len)
      str
    }

    // 步骤二：具体进行流程计算
    val data = "大数据，我来了"
    val result: String = mapReduce(data, (data: String) => data.length, (len: Int) => "字符串的长度是：" + len)
    println(result)


  }

}
