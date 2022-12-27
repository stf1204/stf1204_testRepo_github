package character07

/**
  * @ClassName: Scala15_Par
  * @Author: stf
  * @Date: 2022/11/20 23:12
  * @Description: Scala 并行编程 par
  */
object Scala15_Par {
  def main(args: Array[String]): Unit = {

    val inclusive: Range.Inclusive = 1 to 100

    // 单线程运行
    /*for (elem <- inclusive) {
      println(elem + "----" + Thread.currentThread().getName)
    }*/


    // 并行执行
    inclusive.par.foreach(i => println(i + "--->" + Thread.currentThread().getName))
  }


}
