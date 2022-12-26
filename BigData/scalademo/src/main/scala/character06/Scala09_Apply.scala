package character06

/**
  * @ClassName: Scala09_Apply
  * @Author: stf
  * @Date: 2022/11/17 22:02
  * @Description: TODO 伴生对象的使用和 Apply()函数的使用
  */
object Scala09_Apply {
  def main(args: Array[String]): Unit = {
    // 1、将主构造器私有化之后，无法通过new获取对象
    //    val person0 = new Person09

    // 2、通过伴生对象的getPerson09()方法获取
    //    val person0: Person09 = Person09.getPerson09

    // 3、apply()方法
    //    val person0:Person09 = Person09.apply()
    val person0: Person09 = Person09()
    println(person0.age)
    println(person0.name)
    person0.sayHi("三德子...")

    println("=======================")
    Person09("小梅")
    Person09.apply("小芳")
  }

}

// 伴生类
// 主构造器私有化，通过其他方式实例化对象
class Person09 private() {
  val name: String = "小桃红"
  var age: Int = 17

  // 辅助构造器，调用主构造器
  def this(name: String) {
    this()
  }

  def sayHi(name: String): Unit = {
    println(s"$name ,Hi...")
  }
}


// 伴生对象
// TODO 在当前一个类的伴生对象当中，可以访问这个类的所有成员变量和方法
object Person09 {
  def getPerson09: Person09 = {
    new Person09
  }

  // 马丁 为我们定义的通用方法apply()
  def apply(): Person09 = new Person09()

  // apply()方法不仅局限于构造对象，还可以重载
  def apply(name: String): Person09 = {
    println(s"$name,这是apply()的重载...")
    new Person09
  }

}
