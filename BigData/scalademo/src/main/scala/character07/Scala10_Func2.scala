package character07

/**
  * @ClassName: Scala10_Func2
  * @Author: stf
  * @Date: 2022/11/19 17:22
  * @Description: Scala 中的常用函数2
  */
object Scala10_Func2 {


  def main(args: Array[String]): Unit = {
    //    （1）获取集合的头部元素 head
    val list: List[Int] = List(1, 2, 3, 5, 4, 2, 7, 44, 476, 11)
    val head: Int = list.head
    println(head)

    //    （2）获取集合的尾（不是头的就是尾） tail
    val tails: List[Int] = list.tail
    println(tails)
    //    （3）集合最后一个数据 (last)
    println(list.last)

    //    （4）集合初始数据（不包含最后一个）   init
    println(list.init)

    //    （5）反转 reverse
    println(list.reverse)


    //    （6）取前（后）n个元素  take、takeRight
    // 前
    println(list.take(2))
    // 后
    println(list.takeRight(2))

    //    （7）去掉前（后）n个元素 drop dropRight
    // 前
    println(list.drop(2))
    // 后
    println(list.dropRight(2))
    println("===============================")
    //    （8）并集 union
    val list2 = List(3, 2, 1, 5, 3, 6)
    println(list.union(list2))

    //    （9）交集 intersect
    println(list.intersect(list2))

    //    （10）差集 diff
    println(list.diff(list2))

    //    （11）拉链 zip  多的元素会舍弃
    println(list)
    println(list2)
    println(list.zip(list2))
    println("===============================")
    //    （12）滑窗  sliding(2,5) 2表示窗口大小，5表示步长
    list.sliding(2, 5).foreach(println)
    println("===========================")

    // 需求： 一个集合中有一些数字， 求连续三个数之和的最大值
    val iterator: Iterator[List[Int]] = list.sliding(3, 1)
    var res: Int = iterator.next().sum
    for (elem <- iterator) {
      println(elem + "\t")
      res = math.max(elem.sum, res)
    }
    println(res)
  }
}
