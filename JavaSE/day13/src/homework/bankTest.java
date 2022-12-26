package homework;

public class bankTest {
    public static void main(String[] args) {
        bank b1 = new bank("bdsg12332", 3200);
        bank b2 = new bank("aa1237453332", 34200);
        bank b3 = new bank("1sdas23232", 31643);
        bank a1 = new bank("12asda3232", 3200);
        bank a2 = new bank("1sad23232", 3200);
        bank a3 = new bank("12323dsa2", 43200);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a3);
        bank.setBite(0.05);
        bank.setLast_num(10);
        System.out.println("利率："+bank.getBite());
        System.out.println("最小余额："+bank.getLast_num());
    }
}
