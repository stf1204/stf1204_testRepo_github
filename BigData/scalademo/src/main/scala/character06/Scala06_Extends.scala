package character06

/**
  * @ClassName: Scala_Extends
  * @Author: stf
  * @Date: 2022/11/17 17:06
  * @Description: Scala 中的继承关系
  */
object Scala_Extends {

  def main(args: Array[String]): Unit = {
    // 直接调用子类的主构造器，进而调用父类主构造器
    //    val extend = new extend2()

    println("=========================")
    // 直接调用子类的主构造器，先调用父类的辅助构造器，进而调用父类主构造器
    val extend1 = new extend2("", 22)
    println(extend1.name)

    println("=================================")
    // 直接调用子类的辅助构造器，先调用子类的主构造器，进而调用父类的辅助构造器，最后调用父类的主构造器
    val extend2 = new extend2(21)
    println(extend2.name)
  }


}

// 父类主构造器
class extend1() {
  var name: String = _
  println("父类的主构造器被调用了....")

  // 父类辅助构造器
  def this(name: String) = {
    this()
    this.name = name
    println("父类的辅助构造器被调用了....")
  }
}

// TODO Scala中继承的本质 就是将父类的构造器执行一遍，继承的是父类的构造器
// 继承关于参数的使用注意事项：
// 1、使用继承的时候，可以将参数传递给父类构造器
// 2、子类主构造器的参数一定要大于等于 继承父类构造器的参数个数
// 子类主构造器
class extend2(name: String, age1: Int) extends extend1(name: String) {
  var age: Int = _
  println("子类的主构造器被调用了....")

  // 子类辅助构造器
  def this(age: Int) = {
    this("zhang san", 22)
    this.age = age
    println("子类的辅助构造器被调用了....")
  }
}
