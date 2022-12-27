import java.util.LinkedList;

public class LinkListTest {

    public static void main(String[] args) {

        LinkedList<String> l = new LinkedList<>();
        l.addFirst("d");
        l.addFirst("c");
        l.addFirst("b");
        l.addFirst("a");
        l.addLast("e");
        System.out.println("l = " + l);

        String gf = l.getFirst();
        System.out.println("gf = " + gf);
        String gl = l.getLast();
        System.out.println("gl = " + gl);
        System.out.println("l = " + l);

        String rf = l.removeFirst();
        System.out.println("rf = " + rf);
        System.out.println("l = " + l);

        String rl = l.removeLast();
        System.out.println("rl = " + rl);
        System.out.println("l = " + l);
    }
}
