package enumTest;

public class EnumTest {
    public  void printWeek(Week week){
        switch (week){
            case MON:
                System.out.println("星期一：MONDAY");
                break;
            case TUE:
                System.out.println("星期二：TUESDAY");
                break;
            case WED:
                System.out.println("星期三：WEDNESDAY");
                break;
            case THU:
                System.out.println("星期四：THURSDAY");
                break;
            case FRI:
                System.out.println("星期五：FRIDAY");
                break;
            case SAT:
                System.out.println("星期六：SATURDAY");
                break;
            case SUN:
                System.out.println("星期日：SUNDAY");
                break;
            default:
                System.out.println("输入信息有误，请重新输入");
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        if (n>7||n<1){
            System.out.println("星期数据不对，请重试！！！");
            return;
        }
        Week[] arr = Week.values();
        Week week = arr[n-1];
        // System.out.println(arr[0]);
        System.out.println(week);
        System.out.println("_____________________________");
        EnumTest e1 = new EnumTest();
        e1.printWeek(week);
        // for (int i = 0; i < arr.length; i++) {
        //     System.out.println(arr[n]);
        // }
    }
}
