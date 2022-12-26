package character04

/**
  * @ClassName: Scala_lazyFunc
  * @Author: stf
  * @Date: 2022/11/16 22:57
  * @Description: Scala懒加载  (lazy)
  */
object Scala_lazyFunc {

  def main(args: Array[String]): Unit = {

    def sayHi(name: String): String = {
      println("我是函数体....")
      println("sayHi")
      s"$name"
    }

    lazy val s: String = sayHi("小明")
    println("================================")
    println(s)

    // 懒加载的目的： 是为了提升性能
    // 立即加载：  （业务需求逻辑决定， 提升用户体验）
    // 预加载：（进一步提升用户体验/合理利用资源）
  }

}
