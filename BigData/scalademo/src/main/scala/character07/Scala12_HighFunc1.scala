package character07

/**
  * @ClassName: Scala12_HighFunc
  * @Author: stf
  * @Date: 2022/11/20 19:32
  * @Description: 集合计算 高级函数
  */
object Scala12_HighFunc1 {

  def main(args: Array[String]): Unit = {

    val list1: List[Int] = List(1, 2, 4, 32, 33, 8, 4, 5)
    //    （1）过滤
    //    遍历一个集合并从中获取满足指定条件的元素组成一个新的集合。
    val list2: List[Int] = list1.filter((i: Int) => i % 2 == 0)
    println(list2)

    //    （2）转化/映射（map）
    //    将集合中的每一个元素映射到某一个函数。
    val list3: List[Int] = list1.map(double => double * 2)
    println(list3)
    val list4: List[List[Int]] = list1.map(add => List(add, add))
    println(list4)
    println("=============================")

    //    （3）扁平化
    val flatten: List[Int] = list4.flatten
    println(flatten)
    // 新需求 将List("name xiaohong", "gender sex") 转变为 List("name","xiaohong","gender","sex")
    val list5: List[String] = List("name xiaohong", "gender sex")
    // String底层是char数组
    println(list5.flatten)
    val list6: List[Array[String]] = list5.map(one => one.split(" "))
    println(list6)

    // 需要将array转为list，list才有flatten方法
    val list7: List[List[String]] = list5.map(one => one.split(" ").toList)
    // 化简
    list5.map(_.split(" ").toList)
    println(list7)
    println(list7.flatten)

    //    （4）扁平化+映射 注：flatMap相当于先进行map操作，在进行flatten操作。
    //    集合中的每个元素的子元素映射到某个函数并返回新集合。
    println("=====================================")
    println(list5.flatMap(string => string.split(" ")))
    // 化简
    println(list5.flatMap(_.split(" ")))
    //    （5）分组（groupBy）
    //    按照指定的规则对集合的元素进行分组。
    val list8: List[String] = List("Java", "Mysql", "Linux", "Hadoop", "Hive", "Flume", "Kafka", "Scala", "Spark", "Flink")
    val charToList: Map[Char, List[String]] = list8.groupBy(ob => ob.charAt(0))
    println(charToList)
    // 化简
    println(list8.groupBy(_.charAt(0)))
  }

}
