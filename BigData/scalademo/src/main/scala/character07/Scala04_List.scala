package character07

/**
  * @ClassName: Scala04_List
  * @Author: stf
  * @Date: 2022/11/18 19:33
  * @Description: Scala 集合 不可变List
  */
object Scala04_List {
  def main(args: Array[String]): Unit = {

    //    （1）List默认为不可变集合
    val list0: List[Int] = List(22, 33, 44, 55)
    val list223: List[List[Int]] = List(List(22, 33), List(44, 55))
    println(list223)

    //    （2）创建一个List（数据有顺序，可重复）
    val lists = List(1, 2, 2, 4, 3, 2, 1, 4, 5)

    //    （3）遍历List
    lists.foreach(println)
    println("========================")

    //    （4）List增加数据
    // 尾插法 :+
    val is: List[Int] = lists :+ 31
    is.foreach(println)
    println("========================")

    // 头插法 ::
    val head: List[Int] = 321 :: is
    head.foreach(println)
    println("=====================")

    //    （5）集合间合并：将一个整体拆成一个一个的个体，称为扁平化
    // TODO 扁平化的前提是：一定是嵌套结构

    val list1: List[Any] = head :: list0
    println(list1)

    // 针对复合结构 可以在操作元素复合的过程中就进行扁平化
    val list2: List[Any] = head ::: list0
    println(list2)

    //    （6）取指定数据
    println(list2(0))

    //    （7）空集合Nil
    val list5: List[Nothing] = List()
    println(list5)

    println(Nil)
  }

}
