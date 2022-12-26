package chapter01


// 定义一个scala伴生类
// scala中没有static静态关键字，所以没有办法直接执行，要让程序执行必须要有静态环境，scala提供了一个伴生类的伴生对象



class HelloScala {
//  def main(args: Array[String]): Unit = {
//    System.out.println("Hello Scala")
//  }
}

/*
  TODO: 分析Scala 代码的一些细节
  1、 scala 当中没有public关键字，Scala中认为public使用的次数是最多的，干脆省了，不写就代表public
  2、 object 表明对象的关键字
  3、 def 表明方法和函数的关键字
  4、 main 当前主方法的方法名
  5、 (args:Array[String]) 当前方法的参数列表
  6、 Array[String] Scala中的Array表示数组对象，对比java中的[]，马丁认为[]不是都面向对象的，所以用Array来表示数组对象
  7、 Scala定义参数的时候将类型放在后面，参数名放在前面。马丁认为参数名更重要，使用更多，所以放在更加突出的位置
  8、 ：是用来区分的区分符号
  9、 Unit 表示当前方法的返回值为没有返回值，相当于void
  10、Scala中的方法和方法体用 = 连接
      Scala中的一切等价关系都必须使用明确的 = 去连接，因为方法体和方法名也是一种等价关系，所以用 = 连接
  11、Scala中通过换行符来判断当前一行代码，分号可加可不加
  */

// 定义了一个伴生类的伴生对象
object HelloScala {
  def main(args: Array[String]): Unit = {
      System.out.println("Hello Scala")
  }
}