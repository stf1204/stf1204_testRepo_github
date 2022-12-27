package chapter02

/**
  * @ClassName: Scala02_ValAndVar
  * @Auther: stf
  * @Date: 2022/11/12 20:27
  * @Description: 变量和常量
  */
object Scala02_ValAndVar {
  def main(args: Array[String]): Unit = {
    // (1)、类型推导，是java的jdk1.8的新特性
    // 数字类型会进行类型推导，一般只会推导常用的 Long 和 int类型
    val test1: Byte = 10
    val test2 = 10
    val i2 = 100L
    println(test1.getClass.getSimpleName)
    println(test2.getClass.getSimpleName)
    println(i2.getClass.getSimpleName)


    // (2)、Scala是强数据类型语言，类型确定后，就不能修改
    var str: String = "hello"
    str = "say hello"
    //    str = 100  // error

    var String: String = "I am String\t"
    String = String + "我可以改变"
    println(String)

    val String2: String = "I am String\t我不可以改变"
    println(String2)

    //    val b2:Byte = (2 + 2)
    //    println(b2)

    //(3)、变量声明时，必须要有初始值
    val num: Int = 0
    //    val num2:Int = _

    println(num)

    //  (4)、var修饰的对象引用可以改变，val修饰的对象则不可改变，
    //       但对象的状态（值）却是可以改变的。（比如：自定义对象、数组、集合等等）

    class MyClass {
      val name: String = "zhang san"
      var age: Int = 20
    }

    class MyTest {
      // scala中类的属性 如果是var变量也能使用默认值  但是必须要有等号
      var nb: Int = _
      val name: String = "li si "
    }

    val My1 = new MyClass()
    var My2 = new MyClass()

    My1.age = 22
    //    My2.name = "li si"


    val MyTest2: MyTest = new MyTest()
    //    （1）字符串，通过+号连接。
    println(MyTest2.name + "xiao xue sheng")

    //    （2）重复字符串拼接。
    println(MyTest2.name * 10)

    //    （3）printf用法：字符串，通过%传值。
    val my: String = "%s 今年 %d 大了\n"
    printf(my, "小明", 22)

    //    （4）字符串模板（插值字符串）：通过$获取变量值。
    val name: String = "wang wu"
    var age: Int = 22
    age = age + 1

    val name1: String = "zhao liu"
    var age1: Int = 21
    age1 += 1

    println(s"$name 今年 $age 大了")
    println(s"$name1 今年 $age1 大了")

    val Str: String = s"name:${name + 1},age:${age + 2}"
    println(Str)

    //    （5）长字符串  原始字符串 (输入什么样子，输出就是什么样子)

    //多行字符串，在Scala中，利用三个双引号包围多行字符串就可以实现。
    // 输入的内容，带有空格、\t之类，导致每一行的开始位置不能整洁对齐。
    //应用scala的stripMargin方法，在scala中stripMargin默认是“|”作为连接符，
    // 在多行换行的行头前面加一个“|”符号即可。
    println(
      """
        |select id,
        |       name,
        |       age,
        |       gender
        |from student
        |where id =2022 and name = "zhao liu"
      """.stripMargin)

  }


}
