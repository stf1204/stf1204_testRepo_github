package character06

/**
  * @ClassName: Scala08_OverWrite
  * @Author: stf
  * @Date: 2022/11/17 21:40
  * @Description: TODO： 抽象父类属性 方法 和子类之间的关系
  */
object Scala08_OverWrite {

  def main(args: Array[String]): Unit = {

    val student0: Student08 = new Student08
    println(student0.age)
    println(student0.age2)
    println(student0.name)
    println(student0.name2)
    student0.sayHi("康熙")
    student0.sayHi2("乾隆")
  }

}

// 抽象类
abstract class Person08() {
  // 抽象属性和方法
  val name: String
  var age: Int

  def sayHi(name: String): Unit


  // 非抽象方法和属性
  val name2: String = "三德子"
  var age2: Int = 21

  def sayHi2(name: String): Unit = {
    println(s"$name hi2...")
  }
}

// 子类
class Student08 extends Person08 {
  // 重写变量
  override val name: String = "法印"
  override var age: Int = 22

  // 父类中的非抽象属性也能重写
  // TODO 父类的非抽象属性中，var修饰的属性不能重写，只能修改，val修饰的属性可以重写
  // 原因：val修饰的是不可变量，但我们可以通过重写进行修改，而var本身是可以修改的，直接修改即可
  override val name2: String = "小丽"
  //  override var age2: Int = 22    error 会报错
  age2 = 2222


  // 父类中的非抽象方法也能重写
  override def sayHi2(name: String): Unit =
    println("hi~")

  // 重写方法
  override def sayHi(name: String): Unit = {
    println(s"$name hi.....")
  }
}
