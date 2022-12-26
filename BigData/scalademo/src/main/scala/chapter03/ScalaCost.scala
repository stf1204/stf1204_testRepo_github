package chapter03

/**
  * @ClassName: ScalaCost
  * @Author: stf
  * @Date: 2022/11/13 21:48
  * @Description:
  */
object ScalaCost {

  def main(args: Array[String]): Unit = {

    val num1: Byte = 22
    val num2: Byte = 22

    //    （1）自动提升原则：有多种类型的数据混合运算时，
    //    系统首先自动将所有数据转换成精度大的那种数据类型，然后再进行计算。
    val num3 = num1 + num2
    println(num1.getClass.getSimpleName)
    println(num2.getClass.getSimpleName)
    println(num3.getClass.getSimpleName)

    val f1: Float = 22.1f
    val i1: Int = 22
    val f2: Float = f1 + i1
    println(f2)

    val fl: Float = 1 + 10L + 22.3f
    val db: Double = 1 + 10L + 22.3f + 3.14

    //    （2）把精度大的数值类型赋值给精度小的数值类型时，就会报错，反之就会进行自动类型转换。
    val f3: Double = 22.8
    val d1: Double = f3

    val i2: Int = d1.toInt
    println(i2)

    val s1: String = "22.5"
    val d2: Double = s1.toDouble
    println(d2)
    //    （3）（byte，short）和char之间不会相互自动转换。
    val b3: Byte = 22

    // 强转
    val c1: Char = b3.toChar
    val byte: Byte = c1.toByte
    println(byte)
    //    （4）byte，short，char他们三者可以计算，在计算时首先转换为int类型。
    val tp1: Byte = 22
    val tp2: Char = 23
    val unit: Int = tp1 + tp2
    println(unit)

    println("3".toInt)
    println("3.14".toDouble.toInt)
    println(130.toByte)


    println("=========================================")
    val str: String = "200"
    val str1: String = "200"

    println(str == str1)
    println(str.equals(str1))
    println(str.eq(str1))
    println("=========================================")
    val str4 = new String("aaa")
    val str5 = new String("aaa")
    println(str4 == str5)        //T
    println(str4.equals(str5))   //T
    println(str4.eq(str5))       //F
    println("=========================================")
  }
}
