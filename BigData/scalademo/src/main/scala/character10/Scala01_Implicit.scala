package character10

/**
  * @ClassName: Scala01_Implament
  * @Author: stf
  * @Date: 2022/11/21 15:27
  * @Description: Scala 中隐式转换
  */
object Scala01_Implicit {

  def main(args: Array[String]): Unit = {

    // 通过一个隐式函数的使用让 扩展类和 Int 产生关联
    // 当编译器第一次编译失败的时候，会在当前的环境中查找能让代码编译通过的方法，
    // 用于将类型进行转换，实现二次编译，用于拓展类的方法。
    // TODO 1、隐式函数

    val a:Int = 100
    val i1: Int = a.max(200)
    println(i1)

    println(a.MyMax(300))

    // 定义一个扩展类
    class MyRich(val self:Int){
      def MyMax(i:Int): Int ={
        if (self>i) self else i
      }
    }

    // 隐式函数
    implicit def ChangeMax(i:Int):MyRich = new MyRich(i)



    // TODO 隐式参数
    implicit val name:String = "张三"

    def sayHi(implicit name1:String="李四"): Unit ={
      println(s"hi $name1....")
    }

    sayHi
    sayHi()
    sayHi("王五")







  }

}
