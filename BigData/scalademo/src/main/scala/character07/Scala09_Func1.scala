package character07

/**
  * @ClassName: Scala09_Func1
  * @Author: stf
  * @Date: 2022/11/19 16:32
  * @Description: Scala 中的常用函数
  */
object Scala09_Func1 {

  def main(args: Array[String]): Unit = {

    val list: List[Int] = List(1, 2, 3, 4, 2, 1, 5, 8, 4, 2)

    //    （1）获取集合长度
    println(list.length)

    //    （2）获取集合大小
    println(list.size)

    //    （3）循环遍历
    list.foreach(i => print(i + "\t"))
    println()

    println("================================")
    for (elem <- list) {
      print(s"$elem\t")
    }
    println()

    //    （4）迭代器 (只能迭代一次，类似打枪，打完就没了)
    println("==================================")
    val iterator: Iterator[Int] = list.iterator
    for (elem <- iterator) {
      println(elem)
    }
    //    （5）生成字符串
    println("==========================")
    println(list.mkString("[", "_", "]"))
    //    （6）是否包含
    println(list.contains(9))

  }
}
