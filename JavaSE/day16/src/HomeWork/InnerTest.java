package HomeWork;

class Outer{
    private String name="Outer";

    protected class inner{
        private String name="Inner";
        // private static  int base;  内部类描述对象，不可用static

        public void say(){
            System.out.println("name="+name);
            System.out.println("this.name = " + Outer.this.name);
        }
    }

    public void say(){
        System.out.println(name);
        inner i3 = new inner();
        i3.say();  //外部类调用内部类
    }

    public class Inner2{
        public void test(){
            System.out.println("test...inner2...");
        }
    }
}

public class InnerTest {
    public static void main(String[] args) {
        Outer o1 = new Outer();
        o1.say();
        System.out.println("********************************");
        Outer.inner i1 = o1.new inner();
        Outer.inner i2 = new Outer().new inner();
        i1.say();
        i2.say();
        Outer.Inner2 i3 = o1.new Inner2();
        i3.test();
        System.out.println("*****************");
    }
}
