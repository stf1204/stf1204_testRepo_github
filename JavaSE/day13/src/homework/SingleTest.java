package homework;

public class SingleTest {
    public static void main(String[] args) {
        // Single s = new Single();
        Single s1 = Single.getLast();
        Single s2 = Single.getLast();
        System.out.println(s1.equals(s2));
        System.out.println(s1==s2);

        System.out.println(s1);
        System.out.println(s2);
        s1.setAge(2);
        System.out.println(s1);
        System.out.println(s2);

        System.out.println("*************************************");
        Single2 g2 = Single2.getS2();
        Single2 g21 = Single2.getS2();
        System.out.println(g2.equals(g21));
        g2.setName("小飞");
        System.out.println(g2);
        System.out.println(g21);
    }

}
