package character06

/**
  * @ClassName: Scala10_Poly
  * @Author: stf
  * @Date: 2022/11/17 22:28
  * @Description:
  */
object Scala10_Poly {

  def main(args: Array[String]): Unit = {
    val person100: Person100 = new Person101
    // name是子类，sayHi()也是子类
    println(person100.name)
    person100.sayHi()
  }
}

// 父类
class Person100() {
  val name: String = "小芳"

  def sayHi(): Unit = {
    println("hi...小芳")
  }
}

// 子类
class Person101() extends Person100 {
  override val name: String = "小飞"

  override def sayHi(): Unit = {
    println("hi...小飞")
  }
}

