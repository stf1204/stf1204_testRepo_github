package character06

/**
  * @ClassName: Scala12_ObjectExtend
  * @Author: stf
  * @Date: 2022/11/18 16:47
  * @Description: TODO：对象扩展    枚举Enumeration、应用app   定义新类型 Type
  */
object Scala12_ObjectExtend {

  def main(args: Array[String]): Unit = {

    // TODO 6.7.1 类型检查和转换   asInstanceOf[]类转换   isInstanceOf[] 类判断      classof[] 类模板
    val student11: Parent12 = new Student1
    val student22: Parent12 = new Student2
    //  不推荐方式，先进行类型判断，再进行类型转换
    //    student11.asInstanceOf[Student1]
    //    student22.asInstanceOf[Student1]    // error

    if (student11.isInstanceOf[Student1]) {
      val student: Student1 = student11.asInstanceOf[Student1]
      student.sayHi
    }

    if (student22.isInstanceOf[Student2]) {
      val student2: Student2 = student22.asInstanceOf[Student2]
      student2.sayHi


      // Classof   类模板
      val value: Class[Student2] = classOf[Student2]
      println(value.getName)


    }
  }
}

// 父类
class Parent12 {
  def sayHi: Unit = {
    println("hi....")
  }
}

// 子类
class Student1 extends Parent12 {
  override def sayHi: Unit =
    println("hi...student1....")
}

class Student2 extends Parent12 {
  override def sayHi: Unit =
    println("hi...student2....")
}

// 枚举类
object Test extends Enumeration {
  val SPRING: String = "春"
  val SUMMER: String = "夏"
  val AUTUMN: String = "秋"
  val WINTER: String = "冬"
  val BLUE = Value(1, "blue")
}

// 应用类
object Test2 extends App {
  println(Test.AUTUMN)
}


// Type 定义新类型
object Test3 extends App {

  type test_type = String
  var name: test_type = "abc"

  def test(): test_type = "xyz"

}
