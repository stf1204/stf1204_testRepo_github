package character04

/**
  * @ClassName: Scala_Function_args
  * @Author: stf
  * @Date: 2022/11/14 15:18
  * @Description: 参数
  */
object Scala_Function_args {

  def main(args: Array[String]): Unit = {
    def test(name: String, age: Int): Unit = {
      println(s"今年$name $age 大了...")
    }

    test("小红", 22)


    // （1）可变参数
    def sayHi(age: Int, name: String*) = {
      println(s"今年 $age 岁的$name...")
    }

    sayHi(22, "小明,小红")

    // (2)可变参数必须在参数列表的最后
    def sayHii(age: Int, name: String*) = {

      // 可变参数在函数值本质是一个数组
      for (elem <- name) {
        println(s"今年 $age 岁的$elem...")
      }
    }

    sayHii(22, "小明,小红")

    // (3)参数默认值
    def sayhi(name: String = "小芳", age: Int = 17): Unit = {
      println(name + age)
    }

    // (4)带名参数
    sayhi(age = 22)


  }
}
