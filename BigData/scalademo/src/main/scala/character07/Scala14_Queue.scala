package character07

import scala.collection.immutable.Queue
import scala.collection.mutable


/**
  * @ClassName: Scala14_Queue
  * @Author: stf
  * @Date: 2022/11/20 23:04
  * @Description: Scala Queue
  */
object Scala14_Queue {

  def main(args: Array[String]): Unit = {
    val queue: Queue[Int] = Queue(1, 2, 3, 5)
    println(queue)
    //  TODO 不可变队列
    // 入队列
    val queue1: Queue[Int] = queue.enqueue(6)
    println(queue1)

    // 出队列
    val dequeue: (Int, Queue[Int]) = queue1.dequeue
    println(dequeue)


    // TODO 可变队列
    val queue3: mutable.Queue[Int] = mutable.Queue(1, 2, 3, 4, 5, 7)
    println(queue3)


    // 入队
    val unit: Unit = queue3.enqueue(22)
    println(queue3)

    // 出队
    val i: Int = queue3.dequeue()
    println(i)
    println(queue3)
  }
}
