package HomeWork;

public class TestInterface {

    public static void main(String[] args) {

        // 面向接口，不care传入对象
        CompareObject c1 = new ComparableCircle(2);
        CompareObject c2 = new ComparableCircle(32);

        int i = c1.compareTo(c2);

        if (i>0){
            System.out.println(c1+"大于"+c2);
        }else if (i==0){
            System.out.println(c1+"等于"+c2);
        }else if (i<0){
            System.out.println(c1+"小于"+c2);
        }else{
            System.out.println("对象不可比较");
        }


    }
}
