package character07

/**
  * @ClassName: Scala12_HighFunc2
  * @Author: stf
  * @Date: 2022/11/20 20:09
  * @Description: 集合计算 高级函数
  */
object Scala12_HighFunc2 {

  def main(args: Array[String]): Unit = {

    // TODO 高级函数使用前提  每一个高级函数 都内置一个循环

    // TODO 类似于reduce阶段
    //    （6）简化（归约）
    val list1: List[Int] = List(1, 2, 3, 54, 6, 2, 6, 3, 1, 6)
    var sum = 0
    for (elem <- list1) {
      sum += elem * 2
    }
    println(sum)

    // Reduce参数列表 op: (A1, A1) => A1
    // 1、传入的第一个参数是每次运算的结果，第二个参数是集合中的每一个元素，返回值为结果
    // 2、第一个传入值，第二个传入值，返回值 类型必须一致
    // 3、初始化结果值是 当前集合中的第一个元素
    val i: Int = list1.reduce((sum, a1) => sum + a1 * 2)
    println(i)

    // reduceLeft
    println(list1.reduceLeft((sum, re) => sum + re * 2))

    // reduceRight 不建议使用，运算逻辑是从右往左计算
    println(list1.reduceRight((sum, re) => sum + re * 2))

    //    （7）折叠 （相当于也是归约操作，是reduce的一种优化和扩展）
    // fold(初始值)((参数一，集合元素)=>运算逻辑)
    val i1: Int = list1.fold(0)((sum, re) => sum + re * 2)
    println(i1)


    // 分析： 无论是使用reduce 还是 fold 都无法改变计算逻辑当中返回值的类型
    val unit: (String, Int) = list1.foldLeft(("元素的2倍结果值", 0))((sum, res) => (sum._1, sum._2 + res * 2))
    println(unit)


    // 小结：
    // 1、不考虑结果的初始值，并且保证传入的数据和返回的结果数据类型一致---------->reduce/reduceLeft
    // 2、考虑结果的初始值，需要手动赋值时，并且保证传入的数据和返回的结果数据类型一致--------->fold
    // 3、考虑结果的初始值，需要手动赋值时，并且希望传入的数据和返回的结果值存在差异化--------->foldLeft
  }

}
