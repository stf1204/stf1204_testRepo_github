package character07

import scala.collection.mutable.ListBuffer

/**
  * @ClassName: Scala05_ListBuffer
  * @Author: stf
  * @Date: 2022/11/18 20:10
  * @Description: Scala 集合 可变List
  */
object Scala05_ListBuffer {

  def main(args: Array[String]): Unit = {

    //  （1）创建一个可变集合ListBuffer
    val buffer = new ListBuffer[Int]

    //  （2）向集合中添加数据
    buffer.append(1, 2, 3, 4, 5)
    buffer.foreach(println)

    //  （3）删除元素
    buffer.remove(0)
    println(buffer)

    //  （4）查看修改元素
    buffer.update(1, 222)
    println(buffer)
  }
}
