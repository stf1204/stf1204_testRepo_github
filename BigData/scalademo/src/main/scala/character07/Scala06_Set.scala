package character07

import scala.collection.immutable.HashSet
import scala.collection.mutable

/**
  * @ClassName: Scala06_Set
  * @Author: stf
  * @Date: 2022/11/19 12:00
  * @Description: Scala 集合 set
  */
object Scala06_Set {

  def main(args: Array[String]): Unit = {

    // TODO 不可变集合
    // 默认使用的就是不可变集合
    // 数据无序， 不可重复
    val set1: Set[Int] = Set(1, 2, 3, 4, 5, 6, 3, 2, 5, 8, 5, 11)
    set1.foreach(println)

    // 增加元素
    println("========================================")
    val set2: Set[Int] = set1 + 22
    set2.foreach(println)

    // 底层使用HashSet
    println(set2.getClass.getSimpleName)
    val b1: Boolean = set2.isInstanceOf[HashSet[Int]]
    println(b1)
    // 结论： 当set集合的元素 去重后 个数大于 4 的时候底层使用HashSet来实现， 否则会提供特定的实现


    println("========================================")

    // 判断是否包含元素
    println(set2.contains(220))


    // TODO： 可变集合
    val set3: mutable.Set[Int] = mutable.Set(1, 2, 3, 2, 1, 6, 4, 7, 3, 8)
    println(set3)

    //增加元素
    set3.add(4221)
    println(set3)
    println("================================")

    // 删除元素
    set3.remove(4221)
    println(set3)
    println("==============================")

    // 查询元素
    println(set3.contains(2222))
  }

}
