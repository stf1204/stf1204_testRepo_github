package interfaceTest;

public class test {
    public static void main(String[] args) {
        System.out.println("aaa");
        USB u1 = new Udisk();

        u1.connect();
        u1.work();
        u1.disconnect();
        System.out.println("*****************************");
        Device d = new Mouse("G5");
        d.startup();
        d.shoutdown();
        System.out.println(d);
        // d.connect();
        USB u = (USB)d;  // 造型
        u.connect();
        u.work();
        u.disconnect();
        // u.startup();
        System.out.println(u);
        Mouse m = (Mouse) u;
        System.out.println(m);
    }
}
