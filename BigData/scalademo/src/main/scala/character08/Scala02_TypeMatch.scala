package character08

/**
  * @ClassName: Scala02_TypeMatch
  * @Author: stf
  * @Date: 2022/11/21 10:32
  * @Description: Scala 类型匹配
  */
object Scala02_TypeMatch {

  def main(args: Array[String]): Unit = {

    // 匹配类型
    //    TODO 需求： 提供任意数据类型，根据数据类型进行匹配，返回不同的结果
    val s = 1


    def func(x: Any): String = {
      // 普通的 if-else
      /* if (x.isInstanceOf[String]) "String"
       else if (x.isInstanceOf[Int]) "int"
       else if (x.isInstanceOf[Char]) "Char"
       else "匹配不到...."*/

      // match 模式匹配
      x match {
        case i: Char => "Char"
        case i: Int => "Int"
        case i: String => "Int"
        case _ => "other"
      }
    }

    val str: String = func(s)
    println(str)


  }

}
