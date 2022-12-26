package character07

import scala.collection.mutable.ArrayBuffer

/**
  * @ClassName: Scala03_DimArray
  * @Author: stf
  * @Date: 2022/11/18 19:17
  * @Description: 多维数组的不同创建方式
  */
object Scala03_DimArray {

  def main(args: Array[String]): Unit = {
    // 创建方式1
    val buffer: ArrayBuffer[Array[Int]] = new ArrayBuffer[Array[Int]]()
    buffer.append(Array(1, 2, 3, 4))
    buffer.append(Array[Int](5))
    //遍历
    for (elem <- buffer) {
      elem.foreach(print)
      println()
    }
    // 创建方式2
    val buffered: ArrayBuffer[Array[Int]] = ArrayBuffer(Array(1, 2, 3, 4), Array[Int](5))
    // 遍历
    println("====================================")
    for (elem <- buffered) {
      for (elems <- elem) {
        print(elems + "\t")
      }
      println()
    }

    // 创建方式3   (推荐)
    println("==========================")
    val array: Array[Array[Int]] = Array.ofDim[Int](3, 4)
    array(0)(3) = 2000
    for (elem <- array) {
      for (elems <- elem) {
        print(elems + "\t")
      }
      println()
    }

  }
}
