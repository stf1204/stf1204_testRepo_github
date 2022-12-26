package character06

/**
  * @ClassName: Scala_Obj
  * @Author: stf
  * @Date: 2022/11/17 11:36
  * @Description:
  */
object Scala_Obj {

  def main(args: Array[String]): Unit = {
    // 获取当前包下的共享资源
    println(packageObj)
    println(sayHi("li san"))

  }

}

// 伴生类
class Scala_Obj{}
