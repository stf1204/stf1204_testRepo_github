package character06

/**
  * @ClassName: Scala07_AbsClass
  * @Author: stf
  * @Date: 2022/11/17 21:17
  * @Description: Scala 当中的抽象类
  */
object Scala07_AbsClass {

  def main(args: Array[String]): Unit = {

    // 抽象类的使用方法
    // 1、实例化继承抽象类后的子类，用子类调方法
    val student0 = new Student07()
    println(student0.name)
    student0.sayHi("小芳")

    println("=========================")

    // 2、匿名子类调用，给抽象类赋值
    val func: Person07 = new Person07 {
      override val name: String = "abs_son"
      override var age: Int = 22

      override def sayHi(name: String): Unit = {
        println(s"${name}我是匿名子类...")
        inner("Li si")
      }

      // TODO 匿名子类中的 独有方法和属性，只能内部自己调用，外部无法访问
      def inner(name: String) = {
        println(s"$name ,我是匿名子类的内部函数...")
      }
    }

    println(func.name)
    println(func.age)
    func.sayHi("小桃红...")
    println("===================================")
  }
}

// 抽象类
abstract class Person07() {

  // 抽象属性(Scala中独有的属性)
  val name: String
  var age: Int

  // 抽象方法
  def sayHi(name: String)
}


// 实现类
class Student07 extends Person07() {
  override val name: String = "颤三"
  override var age: Int = 22

  override def sayHi(name: String): Unit = {
    println(s"sayHi $name...")
  }
}
