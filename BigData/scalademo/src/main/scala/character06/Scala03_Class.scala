package character06

import scala.beans.BeanProperty

/**
  * @ClassName: Scala_Class
  * @Author: stf
  * @Date: 2022/11/17 15:06
  * @Description: TODO：Scala中的定义类和封装属性 @BeanProperty注解生成Get、Set方法
  */
object Scala_Class {

  // 内部类
  class class01 {
    @BeanProperty
    val name: String = "Wang wu"
    @BeanProperty
    var age: Int = 33
  }

  def main(args: Array[String]): Unit = {
    // 内部类
    class class02 {
    }

    val unit = new class04()
    println(unit.name)
    println(unit.age)
    unit.age = 40
    println(unit.age)
    println(unit.getAge)
    println(unit.getName)
    unit.setAge(5)
    println(unit.getAge)

  }


  // 内部类
  class class03 {
    @BeanProperty
    val name: String = "li si"
    @BeanProperty
    var age: Int = 21
  }

}

// 外部类
class class04 {
  // TODO  Scala中默认情况下不提供Get，Set方法 但是Scala也对属性提供读写权限

  @BeanProperty
  val name: String = "张三"

  @BeanProperty
  var age: Int = 22
}
