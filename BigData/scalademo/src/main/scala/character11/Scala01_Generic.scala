package character11

/**
  * @ClassName: Scala01_Generic
  * @Author: stf
  * @Date: 2022/11/21 15:41
  * @Description: Scala 泛型介绍
  */
object Scala01_Generic {

  def main(args: Array[String]): Unit = {

    class Parent{}
    class Child extends Parent {}
    class Test[-T] {}
    // T 不变
    // +T 协变
    // -T 逆变

    // TODO 通过多态的方式实现

    //不变: T  Son类 是Father的子类，MyList[Son]和MyList[Father] :相当于解除了父子关系
    //    val unit: Test[Child] = new Test[Child]

    //协变: +T Son类 是Father的子类，MyList[Son] 和MyList[Father]:两者是父子关系, 和前边关系保持一致
//    val unit1: Test[Parent] = new Test[Child]

    //逆变: -T Son类 是Father的子类，MyList[Son] 和MyList[Father]:两者是父子关系, 父子关系反转
    val unit3: Test[Child] = new Test[Parent]

  }

}
