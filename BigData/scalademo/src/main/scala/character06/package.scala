package object character06 {

  //TODO 当前包下只能有一个包对象
  // TODO 包对象的作用: 综合管理一些当前包下的共享资源

  // 定义属性
  val packageObj: String = "packageObject"

  // 定义方法
  def sayHi(name: String): Unit = {
    println(s"hi $name...")

    //包对象不能访问其他对象中的资源
  }
}
