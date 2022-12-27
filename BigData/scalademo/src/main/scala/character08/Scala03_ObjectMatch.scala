package character08

/**
  * @ClassName: Scala03_ObjectMatch
  * @Author: stf
  * @Date: 2022/11/21 10:47
  * @Description: Scala match 对象匹配
  */
object Scala03_ObjectMatch {

  def main(args: Array[String]): Unit = {

    val array = Array(1, 2, 3, 4)
    array match {
      // 模糊查找
      case Array(1, 2, x, 4) => println("找到了...")
      case _ => println("没找到...")
    }

    val student = new Student("小明", 22)
    student match {
      case Student(x, 22) => println("小黄找到了....")
      case _ => println("小黄没找到啊....")
    }
  }

}

// 样例类
case class Student(var name: String, var age: Int) {

}

object Student {
  // 构建对象
  def apply(): Student = new Student("小明", 18)

 /* // 解析对象
  def unapply(arg: Student): Option[(String, Int)] = {
    if (arg == null) null
    else Some(arg.name, arg.age)
  }*/

}
