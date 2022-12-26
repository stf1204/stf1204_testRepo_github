/**
 * @ClassName: JavaPoly
 * @Author: stf
 * @Date: 2022/11/17 22:31
 * @Description: Java 当中的多态
 */
public class JavaPoly {
    public static void main(String[] args) {
        Person10 son = new Stud10();
        // name是父类，sayHi是子类
        System.out.println(son.name);
        son.sayHi();
    }

    public static class Person10 {
        String name = "小芳";

        public void sayHi() {
            System.out.println("hi...小芳");
        }
    }

    public static class Stud10 extends Person10 {
        String name = "小飞";

        @Override
        public void sayHi() {
            System.out.println("hi....小飞");
        }
    }

}

