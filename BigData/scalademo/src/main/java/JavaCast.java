/**
 * @ClassName: JavaCast
 * @Author: stf
 * @Date: 2022/11/13 21:31
 * @Description:
 */
public class JavaCast {

    public static void main(String[] args) {

        // 隐式类型转换
        byte a1 = 22;
        short b1 = a1;
        int c1 = b1;
        long d1 = c1;
        float e1 = d1;
        double f1 = e1;
        System.out.println("f1 = " + f1);

        // 强制类型转换
        double a2 = 22.5;
        float b2 = (float) a2;
        long c2 = (long) b2;
        int d2 = (int) c2;
        short e2 = (short) d2;
        byte f2 = (byte) e2;
        System.out.println("f2 = " + f2);


    }

}
