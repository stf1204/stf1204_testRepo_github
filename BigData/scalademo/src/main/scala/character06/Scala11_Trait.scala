package character06

/**
  * @ClassName: Scala11_Trait
  * @Author: stf
  * @Date: 2022/11/17 22:47
  * @Description: TODO：trait 相当于Java中的interface
  */
object Scala11_Trait {

  def main(args: Array[String]): Unit = {

    //    （4）动态混入：可灵活的扩展类的功能
    //    （4.1）动态混入：创建对象时混入trait，而无需使类混入该trait
    //    （4.2）如果混入的trait中有未实现的方法，则需要实现
    val obj: test with trait1 = new test with trait1 {
      override val name1: String = "test11"
      override var age1: Int = 11

      override def sayHi1(): Unit = println("test1.trait1.sayHi1.....")
    }
    println("age1:" + obj.age1)
    println("name1:" + obj.name1)
    obj.sayHi1()
    obj.sayHi12()
  }

}

// 测试类
class test() {
}

// 抽象父类
abstract class Person11() {
  val name11: String = "小芳"
  val name22: String
  var age11: Int = 21
  var age22: Int

  def sayHi11(): Unit = {
    println("hi...小芳")
  }

  def sayHi22(): Unit
}

// 特质1、
//    （1）特质可以同时拥有抽象方法和具体方法
trait trait1 {
  val name1: String
  var age1: Int

  def sayHi1(): Unit

  def sayHi12(): Unit = {
    println("trait1.sayHi12.....")
  }
}

// 特质2、
trait trait2 {
  val name2: String
  var age2: Int

  def sayHi2(): Unit
}

// 子类 继承抽象父类 Person1、 特质1、 特质2
//    （2）一个类可以混入多个特质
class Person22() extends Person11 with trait1 with trait2 {
  val name: String = "小芳"
  var age: Int = 21

  def sayHi(): Unit = {
    println("hi...小芳")
  }

  override val name22: String = "person11"
  override var age22: Int = 11

  override def sayHi22(): Unit = println("person11.....")

  override val name1: String = "trait1"
  override var age1: Int = 1

  override def sayHi1(): Unit = println("trait1...")

  override val name2: String = "trait2"
  override var age2: Int = 2

  override def sayHi2(): Unit = println("trait2...")
}

//    （3）所有的Java接口都可以当做Scala特质使用
class Person23() extends Person11 with trait1 with java.io.Serializable {
  override val name22: String = "person111"
  override var age22: Int = 111

  override def sayHi22(): Unit = println("person111.....")

  override val name1: String = "trait111"
  override var age1: Int = 111

  override def sayHi1(): Unit = println("trait111...")
}

