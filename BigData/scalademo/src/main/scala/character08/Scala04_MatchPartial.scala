package character08

/**
  * @ClassName: Scala04_MatchPartial
  * @Author: stf
  * @Date: 2022/11/21 14:54
  * @Description: Scala 偏函数
  */
object Scala04_MatchPartial {

  def main(args: Array[String]): Unit = {

    // 全函数: 会把集合中的所有元素都进行处理
    // 偏函数: 部分函数 调用一个函数来处理集合数据， 可以根据业务需求只处理一部分


    // 判断List中的元素， 如果是int类型就加一， 如果是String就拼接   //
    val list: List[Any] = List(1, 4, 2, 6, 21, 31, 5, "name", "age", "gender")

    /*val list2: List[Any] = list.map(x => {
/*      //1、 传统实现方式
      if (x.isInstanceOf[String]) x.asInstanceOf[String] + "//"
      else if (x.isInstanceOf[Int]) x.asInstanceOf[Int] + 1
      else x*/

      //2、 模式匹配方式
      x match {
        case e:String => x.asInstanceOf[String] + "//"
        case e:Int => x.asInstanceOf[Int] + 1
        case _ => x
      }
    })
    println(list2)*/

    // 3、使用函数实现
    val list3: List[Any] = list.map({
      case e: String => e + "//"
      case e: Int => e + 1
      case e => e
    })
    println(list3)


    // 需求2； 如果是Int类型 加一， 如果是String 就过滤掉
    // 传统方案处理: 先过滤 后 映射
    val next: List[Any] = List(1, 4, 2, 6, 21, 31, 5, "name", "age", "gender")
    // 过滤
    val list1: List[Any] = next.filter(x => x.isInstanceOf[Int])
    println(list1)

    // 映射
    val next1: List[Int] = list1.map(x => x.asInstanceOf[Int] + 1)
    println(next1)

    // 一步到位 只处理关心的那部分数据
    println(next.collect({ case e: Int => e + 1 }))
  }

}
