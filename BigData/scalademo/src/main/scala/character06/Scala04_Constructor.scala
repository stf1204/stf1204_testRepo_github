package character06

/**
  * @ClassName: Scala_Constructor
  * @Author: stf
  * @Date: 2022/11/17 16:06
  * @Description: TODO Scala中的构造器（主构造器，辅助构造器）
  */
object Scala_Constructor {


  def main(args: Array[String]): Unit = {

    // 调用主构造器
    val con1 = new constructor("小芳", 22)
    println("========================================")
    // 调用辅助无参构造器，直接调用主构造器
    val con2 = new constructor()
    println("========================================")

    // 调用辅助有参构造器，间接调用主构造器
    val con3 = new constructor(22)
    println("========================================")
  }

}

// TODO 主构造器，包名和主构造器共用同一个名。针对当前对象进行构造的时候一定会访问到主构造器
// 主构造器和辅助构造器相结合，作者这样设计是为了满足实例化类的时候让类中所有的内容都加载一遍
class constructor(name: String, age: Int) {
  println("主构造器被调用了.....")

  // TODO 辅助构造器，方法名是固定的，所有的辅助构造器都叫 this
  // 直接调用主构造器
  def this() {
    // 第一行必须必须调用主构造器
    this("aaa", 11)
    println("辅助构造器1被调用了.....")
  }

  // 间接调用主构造器
  def this(i: Int) {
    this()
    println("辅助构造器2被调用了.....")
  }
}
