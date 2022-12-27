package chapter03

/**
  * @ClassName: Scala_Function
  * @Author: stf
  * @Date: 2022/11/14 10:24
  * @Description:
  */
object Scala_Function {

  def main(args: Array[String]): Unit = {

    def sayHi(name: String): Unit = {
      println("我是函数...")
      println(s"$name" + "你好帅...")
    }


    //函数不允许重载
    //    def sayHi(name:String,age:Int): Unit ={
    //      println("我是函数...")
    //      println(s"$name" + "你好帅...")
    //    }

    sayHi("小芳")

  }

  def sayHi(name: String): Unit = {
    println("我是方法...")
    println(s"$name, 你也很帅啊...")
  }

  def sayHi(name: String, age: Int): Unit = {
    println("我是方法，我可以重载...")
    println(s"$name 很美，她今年芳龄 $age")
  }

  sayHi("阿飞")
  sayHi("小芳",21)
}
