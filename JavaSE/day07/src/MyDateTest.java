public class MyDateTest {
    public static void main(String[] args) {
        MyDate birthday = new MyDate();
        // birthday.years=1998;
        // birthday.months=12;
        // birthday.days = 4;
        String s=birthday.say();
        System.out.println("生日是"+s);

        MyDate today = new MyDate();
        // today.years = 2022;
        // today.months = 9;
        // today.days=27;
        today.setYears(2022);
        today.setMonths(9);
        today.setDays(27);
        System.out.println("==========================================");
        System.out.println(today.getYears());
        System.out.println(today.getMonths());
        System.out.println(today.getDays());

        String s1=today.say();
        System.out.println("今天是" + s1);

        System.out.println("------------------------------------------");
        MyDate tmp = new MyDate();
        tmp = birthday;
        birthday = today;
        today = tmp;

        System.out.println("生日是"+birthday.say());
        System.out.println("今天是"+today.say());

        System.out.println("------------------------------------------");

        MyDate d1 = new MyDate();
        MyDate d2 = new MyDate();
        System.out.println("d2 = " + d1.say());
        System.out.println("d2 = " + d2.say());

    }
}
