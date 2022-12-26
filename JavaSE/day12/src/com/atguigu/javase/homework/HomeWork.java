package com.atguigu.javase.homework;

public class HomeWork {

    public static void main1(String[] args) {
            /*
1、使用二维数组打印一个 10 行杨辉三角.
1
1 1
1 2 1
1 3 3  1
1 4 6  4  1
1 5 10 10 5 1
1 6 15 20 15 6 1
....

【提示】
 1. 第一行有 1 个元素, 第 n 行有 n 个元素
 2. 每一行的第一个元素和最后一个元素都是 1
 3. 从第三行开始, 对于非第一个元素和最后一个元素的元素.
     yanghui[i][j] = yanghui[i-1][j-1] + yanghui[i-1][j];
 */

        int[][]arrarr=new int[10][];
        // System.out.println(arrarr.length);

        // 方法一
        // for (int i = 0; i < arrarr.length; i++) {
        //     arrarr[i]=new int[i+1];
        //     for (int j = 0; j < i+1; j++) {
        //         arrarr[i][0]=1;
        //         arrarr[i][i]=1;
        //         if (j!=0&&j!=i) {
        //             arrarr[i][j] = arrarr[i - 1][j] + arrarr[i - 1][j - 1];
        //         }
        //     }
        // }

        // 方法二
        for (int i = 0; i < arrarr.length; i++) {
            arrarr[i]=new int[i+1];
            // 固定第一个元素和最后一个元素
            arrarr[i][0]=arrarr[i][i]=1;
            for (int j = 1; j < i; j++) {  //i最大到9,j从第二个数开始，j最大到8，也就是最后第二个数
                arrarr[i][j] = arrarr[i - 1][j] + arrarr[i - 1][j - 1];

            }
        }


        // 遍历
        for (int i = 0; i < arrarr.length; i++) {
            for (int j = 0; j < i+1; j++) {
                System.out.print(arrarr[i][j]+" ");
            }
            System.out.println();
        }


    }


    //2、写一个可变参方法 public static int max(int... nums) {}
    public static int max(int... arr){
        int max=0x80000000;
        for (int i : arr) {
            if (max<i){
                max=i;
            }
        }
        if (max==0x80000000) {
            System.out.println("没有最大值");
            return 0;
        }else {
            return max;
        }
    }
    public static void main(String[] args) {
        System.out.println(max(1, 2, 3));

        System.out.println(max());
    }





}
