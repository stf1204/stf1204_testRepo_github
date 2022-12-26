package character07

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * @ClassName: Scala02_ArrayBuffer
  * @Author: stf
  * @Date: 2022/11/18 18:57
  * @Description: Scala集合 可变长度数组
  */
object Scala02_ArrayBuffer {

  def main(args: Array[String]): Unit = {

    // 动态创建可变数组
    // 长度不指定，默认是16
    val arrays1: ArrayBuffer[Int] = new ArrayBuffer[Int]()
    arrays1.append(1)
    arrays1.append(2)
    arrays1.append(3)
    arrays1.append(4)

    // 静态创建可变数组
    val arrays2: ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, 4)
    arrays1.foreach(println)
    println("===========================================")
    arrays2.foreach(println)
    println("===========================================")

    // 可变数组增加元素
    arrays1.append(2222)
    arrays1.foreach(println)
    println("====================================")

    // 删除元素
    arrays1.remove(4)
    arrays1.remove(0, 2)
    arrays1.foreach(println)
    println("====================================")

    //修改元素
    arrays1.update(1, 200000)
    arrays1.foreach(println)
    println("====================================")

    // 获取元素
    println(arrays1(1))


    // TODO 可变与不可变数组的转化
    val ints: Array[Int] = new Array[Int](3)

    val ints1: ArrayBuffer[Int] = new ArrayBuffer[Int]()
    println("=================================")
    // 不可变转可变
    val buffer: mutable.Buffer[Int] = ints.toBuffer
    buffer.append(222)
    buffer.foreach(println)
    println("===============================")

    // 可变转不可变
    val toArray: Array[Int] = ints1.toArray
    val unit: Array[Int] = toArray :+ 23
    unit.foreach(println)

  }

}
