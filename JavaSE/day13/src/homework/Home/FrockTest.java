package homework.Home;

public class FrockTest {
    public static void main(String[] args) {
        System.out.println(Frock.getNextNum());
        System.out.println(Frock.getNextNum());
        Frock f1 = new Frock(11,"a",21);
        Frock f2 = new Frock(12,"b",22);
        Frock f3 = new Frock(13,"d",23);
        Frock f4 = new Frock(14,"z",31);
        System.out.println(f1);
        System.out.println(f2);
        System.out.println(f3);
        System.out.println(f4);

    }

}
