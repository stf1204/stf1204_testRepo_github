package com.atguigu.javase.test;

public class ArrayArrayTest {
    public static void main(String[] args) {
        int[][]arrarr=new int[6][];
        for (int i = 0; i < arrarr.length; i++) {
            arrarr[i]=new int[(int)(Math.random()*4+3)];
            for (int j = 0; j < arrarr[i].length; j++) {
                arrarr[i][j]=(int)(Math.random()*100);
            }
        }

        for (int[] arr : arrarr) {
            for (int i : arr) {
                System.out.print(i+" ");
            }
            System.out.println();
        }

        //max min
        int max=arrarr[0][0];
        int min=arrarr[0][0];
        for (int[] arr : arrarr) {
            for (int i : arr) {
                if (i>max){
                    max=i;
                }
                if (i<min){
                    min=i;
                }
            }
        }
        System.out.println("min = " + min);
        System.out.println("max = " + max);
    }
    public static void main1(String[] args) {
        int[][]arrarr=new int[3][];
/*
        arrarr[0]=new int[3];
        arrarr[1]=new int[4];
        arrarr[2]=new int[6];

        arrarr[1][1]=20; //0,20,0,0
        arrarr[0][2]=8; // 0,0,8
        arrarr[2][3]=9; //0,0,0,9,0,0
*/

        for (int i = 0; i < arrarr.length; i++) {
            arrarr[i]=new int[1+2];
            for (int j = 0; j < arrarr[i].length; j++) {
                arrarr[i][j]=(int)(Math.random()*60);
            }
        }
        // 遍历
        for (int i = 0; i < arrarr.length; i++) {
            for (int j = 0; j < arrarr[i].length; j++) {
                System.out.print(arrarr[i][j]+" ");
            }
            System.out.println();
        }
        // 求平均值
        int sum=0;
        int count=0;
        for (int i = 0; i < arrarr.length; i++) {
            for (int j = 0; j < arrarr[i].length; j++) {
                sum += arrarr[i][j];
                count++;
            }
        }
        int avg = sum/count;
        System.out.println("avg = " + avg);

        // 最大值max
        int max=0x80000000;
        for (int[] arr : arrarr) {
            for (int i : arr) {
                if (i>max){
                    max=i;
                }
            }
        }
        if (max!=0x80000000){
            System.out.println("max = " + max);
        }

        // 最小值min
        int min = 0x7fffffff;
        for (int[] arr : arrarr) {
            for (int i : arr) {
                if (min>i){
                    min=i;
                }
            }
        }
        if (min!=0x7fffffff){
            System.out.println("min = " + min);
        }


    }
}
