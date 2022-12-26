package character04

/**
  * @ClassName: Scala_currying
  * @Author: stf
  * @Date: 2022/11/16 17:58
  * @Description: 柯里化声明 和 柯里化调用 以及高阶函数第三种用法
  */
object Scala_currying {

  def main(args: Array[String]): Unit = {

    // TODO 需求一 ： 定义一个函数，func1(Int,Char,String) 判断传入的参数，如果(0,'0',"")返回false，否则true
    def func1(a: Int, b: Char, c: String): Boolean = {
      if (a == 0 && b == '0' && c == "") false
      else true
    }

    println(func1(0, '0', ""))
    println(func1(1, '0', ""))
    println(func1(0, '1', ""))
    println(func1(0, '0', "1"))
    println("====================================")


    // 化简
    // 第一步、去掉if和括号
    def func3(a: Int, b: Char, c: String): Boolean = a != 0 || b != '0' || c != ""


    // 第二步 匿名化
    val function: (Int, Char, String) => Boolean = (a: Int, b: Char, c: String) => a != 0 || b != '0' || c != ""

    println(function(0, '0', ""))
    println(function(0, '0', "1"))
    println(function(0, '1', ""))
    println(function(1, '0', ""))
    println("=======================================")

    // TODO 需求二 ： 定义一个函数，func2(Int)(Char)(String) 判断传入的参数，如果(0)('0')("")返回false，否则true
    def func2(a: Int) = {
      def func3(b: Char) = {
        def func4(c: String) = {
          a != 0 || b != '0' || c != ""
        }

        func4 _
      }

      func3 _
    }

    // 普通调用
    val function1: Char => String => Boolean = func2(0)
    val function2: String => Boolean = function1('0')
    val resBool: Boolean = function2("")
    println(resBool)
    println("========================================")

    // 柯里化调用
    println(func2(0)('0')(""))
    println(func2(1)('0')(""))
    println(func2(0)('1')(""))
    println(func2(0)('0')("1"))
    println("=====================")


    // 化简
    // 1、先从最内部开始
    def func4(a: Int) = {
      def func3(b: Char) = {
        def func4(c: String) = a != 0 || b != '0' || c != ""

        func4 _
      }

      func3 _
    }

    // 2、最内部匿名化
    def func5(a: Int) = {
      def func3(b: Char) = {
        (c: String) =>
          a != 0 || b != '0' || c != ""
          func4 _
      }

      func3 _
    }

    // 3、外部化简
    def func6(a: Int) = {
      def func3(b: Char) =
        (c: String) => a != 0 || b != '0' || c != ""

      func3 _
    }

    // 4、外部匿名化
    def func7(a: Int) = {
      (b: Char) => (c: String) => a != 0 || b != '0' || c != ""
    }

    // 5、最外部化简
    def func8(a: Int) = (b: Char) => (c: String) => a != 0 || b != '0' || c != ""

    // 6、最外部匿名化
    val func: Int => Char => String => Boolean = (a: Int) => (b: Char) => (c: String) => a != 0 || b != '0' || c != ""

    // 7、匿名函数化简数据类型 (适可而止,建议)
    val func12: Int => Char => String => Boolean = a => b => c => a != 0 || b != '0' || c != ""

    // 8、下划线
    val func123: Int => Char => String => Boolean = a => b => a != 0 || b != '0' || _ != ""
    println(func123(0)('0')(""))
    println("------------------------")


    // TODO 针对嵌套式函数  进行柯里化声明
    def func999(a: Int)(b: Char)(c: String) = a != 0 || b != '0' || c != ""

    println(func999(0)('0')(""))
    println(func999(1)('0')(""))
    println(func999(0)('1')(""))
    println(func999(0)('0')("1"))
    println("============================================")
  }
}
