package com.atguigu.javase.test;

public class VarArgsTest {

    /*
可变参数：就是参数的个数可变，可变参数的本质就是 数组
一个方法中最多一个可变参数，因为可变参数必须放在参数列表的最后
     */
    public static int sum(int... arr){
        int sum=0;
        for (int i = 0; i < arr.length; i++) {
            sum+=i;
        }
        return sum;
    }

    public static int avg(int... arr){
        int count=0;
        int sum=0;
        for (int i : arr) {
            sum+=i;
            count++;
        }
        int avg=sum/count;
        return avg;
    }

    public static int max(int... arr){
        int max=0x80000000;
        for (int i : arr) {
            if (i>max){
                max=i;
            }
        }
        return max;
    }

    public static int min(int... arr){
        int min=0x7fffffff;
        for (int i : arr) {
            if (i<min){
                min=i;
            }
        }
        return min;
    }

    public static void main(String... args) {  //命令行参数的本质也是可变参

    }
}
