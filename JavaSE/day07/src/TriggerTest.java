public class TriggerTest {
    public static void main(String[] args) {
        Trigger t1 = new Trigger();
        System.out.println("三角形默认的底:"+t1.getBase()+"三角形默认的高:"+t1.getHeight());

        System.out.println("------------------------");
        Trigger t2 = new Trigger(20,30);
        int base = t2.getBase();
        int height = t2.getHeight();

        System.out.println("自定义的三角形的底"+base);
        System.out.println("自定义的三角形的高"+height);
        System.out.println("---------------------------");

        // System.out.println(base);
        int s = (base*height)/2;
        System.out.println("自定义三角形的面积为:"+s);

    }
}
