package character09

/**
  * @ClassName: Scala01_ExceptionTest
  * @Author: stf
  * @Date: 2022/11/21 15:21
  * @Description: Scala 异常
  */
object Scala01_ExceptionTest {

  def main(args: Array[String]): Unit = {
    try {
      1 / 0
    } catch {
      case e: ArithmeticException => println("数字异常...\t" + e.getMessage)
      case e: RuntimeException => println("数字异常...\t" + e.getStackTrace)
    } finally {
      println("关闭资源...")
    }
    println("其他代码块...")
  }
}
