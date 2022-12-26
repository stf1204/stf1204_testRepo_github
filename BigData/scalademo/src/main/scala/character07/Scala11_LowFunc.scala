package character07

/**
  * @ClassName: Scala11_LowFunc
  * @Author: stf
  * @Date: 2022/11/20 12:31
  * @Description: 集合计算 初级函数
  */
object Scala11_LowFunc {

  def main(args: Array[String]): Unit = {

    val unit = List(1, 2, 8, 9, 500, 0, 3, 2)

    //    （1）求和 sum
    println(unit.sum)

    //    （2）求乘积 product
    println(unit.product)

    //    （3）最大值 max
    println(unit.max)

    //    （4）最小值 min
    println(unit.min)

    //    （5）排序 sorted 、sortBy、sortWith
    println(unit.sorted)

    val tuples: List[(String, Int)] = List(("one", 1), ("two", 2), ("three", 2), ("six", 6), ("three", 3), ("five", 5),
      ("four", 4))
    // 如果使用默认的 sorted 会按照二元组中的第一个元素 进行升序
    println(tuples.sorted)

    // sorted 从大到小排序
    println(unit.sorted(Ordering[Int].reverse))

    // 使用sortBy可以指定排序规则
    println(tuples.sortBy((ts: (String, Int)) => ts._2))

    // sortBy 从大到小排序
    println(tuples.sortBy((ui: (String, Int)) => ui._2)(Ordering[Int].reverse))

    // 使用sortWith 更灵活，可以自定义排序逻辑
    println(tuples.sortWith((left: (String, Int), right: (String, Int)) => left._2 < right._2))

    // sortWith 从大到小排序
    println(tuples.sortWith((le: (String, Int), re: (String, Int)) => le._2 > re._2))


    // 先按照第二列排序，如果相同再按照第一列排序
    println(tuples.sortWith((le: (String, Int), re: (String, Int)) =>
      if (le._2 != re._2)
        le._2 < re._2
      else le._1 < re._1))



  }

}
