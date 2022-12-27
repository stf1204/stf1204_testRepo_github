import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Thread.sleep;

public class DateTest {


        //获取两个字符串中最长相同子串
        //提示：将短的那个串进行长度依次递减的子串与较长的串比较。
        //以length为长度从str2中取子串, 再让str1测试此子串是否存在
        @Test
        public void y(){
        String str1 = "abcwerthelloyuiodef ";
        String str2 = "cvhellobnm";

        // 控制子串长度
        int length = str2.length();
        while (length>0){
            //内层控制比较次数
            int begin = 0;
            while (str1.length()-begin-1>length){
                String sub = str2.substring(begin, begin + length);
                if (str1.indexOf(sub)!=-1){
                    System.out.println(length);
                }
                begin++;
            }
            length--;
        }
    }

    @Test
    public void test4() {
        //[0~1)
        double r1 = Math.random();
        System.out.println(r1);

        //round() 四舍五入
        System.out.println(Math.round(12.5));   // 13
        System.out.println(Math.round(-12.5));  // -12
    }
    @Test
    public void test3() {
        // LocalDate
        // LocalTime
        // LocalDateTime
        LocalDate now = LocalDate.now();
        System.out.println("now = " + now);

        LocalDate birthday = now.withYear(1998).withMonth(12).withDayOfMonth(4);
        System.out.println("birthday = " + birthday);

        LocalDate p1 = birthday.plusDays(100);
        System.out.println("p1 = " + p1);

        LocalTime now1 = LocalTime.now();
        System.out.println("now1 = " + now1);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime);

        DateTimeFormatter dtm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String f = dtm.format(localDateTime);
        System.out.println("f = " + f);

        LocalTime t = LocalTime.of(1999, 9, 9);
        System.out.println("t = " + t);


    }


    @Test
    public void test2() {
        Calendar c1 = Calendar.getInstance();
        int year = c1.get(Calendar.YEAR);
        // 比真实的月少一
        int month = c1.get(Calendar.MONDAY);
        int day = c1.get(Calendar.DAY_OF_MONTH);
        System.out.println(year + " " + month + " " + day);

        c1.set(Calendar.YEAR, 1998);
        c1.set(Calendar.MONDAY, 11);
        c1.set(Calendar.DAY_OF_MONTH, 4);
        System.out.println(year + " " + month + " " + day);

        c1.add(Calendar.DAY_OF_MONTH, 100);
        System.out.println(c1.getTime());
    }

    @Test
    public void test() {
        long l = System.currentTimeMillis();
        System.out.println(l);

        Date date = new Date();
        System.out.println("date = " + date);

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 日期格式化器
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String data = dateFormat.format(date);
        System.out.println("data = " + data);

        String s2 = dateFormat.format(l);
        System.out.println("s2 = " + s2);

        // 还可以解析字符串
        String s = "2022-10-06 16:59:05";
        try {
            Date ss = dateFormat.parse(s);
            System.out.println(ss);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
