/**
 * @ClassName: Java_Class
 * @Author: stf
 * @Date: 2022/11/17 15:06
 * @Description: TODO： Java 当中定义类，以及属性的封装
 */
public class JavaBean {

    public static class java {

        private String name = "xiao hong";
        private int age = 22;

        // 构造器
        public java(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public java() {
        }

        public java(String name) {
            this.name = name;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return super.toString();
        }


        public static void main(String[] args) {
            java javatest = new java();

            System.out.println(javatest.age);
            System.out.println(javatest.name);

            javatest.setAge(200);
            javatest.setName("小明");

            System.out.println(javatest.getName());
            System.out.println(javatest.getAge());
        }
    }
}
