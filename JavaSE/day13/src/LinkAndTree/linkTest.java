package LinkAndTree;

public class linkTest {
    public static void main(String[] args) {
        link l = new link();
        for (int i = 0; i < 10; i++) {
            l.add(3*i+2);
            // l.add("a");
        }
        l.travel();
        boolean b = l.remove("a");
        System.out.println(b);
        System.out.println("***************");
        l.travel();
        System.out.println("***************");
        System.out.println(l.size());
        System.out.println("--------------------");
        l.travel2();
    }
}
