package com.atguigu.javase;


import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author stf
 */
public class FileInputStreamTest {
    public static void main(String[] args) throws IOException {
        readFile1();
        readFile2();
        readFile3();
    }



    /**
     * FileInputStream
     * int read() 读取单个字节
     * 将返回读取到的字节，读取到文件名末尾返回-1
     */
    public static void readFile1() throws IOException {
        FileInputStream in = new FileInputStream("d:/aaa.txt");
        /*
         int i = in.read();
         System.out.println("i = " + i);
         i = in.read();
         System.out.println("i = " + i);
         i = in.read();
         System.out.println("i = " + i);
         i = in.read();
         System.out.println("i = " + i);
         */
        int i = 0;
        while ((i = in.read()) != -1) {
            System.out.print((char) i);
        }
        in.close();
    }


    /**
     * FileInputStream
     * int read(byte[] b) 读取字节，存储在数组中
     */
    public static void readFile2() throws IOException {
        FileInputStream file = new FileInputStream("d:/aaa.txt");
        byte[] bytes = new byte[2];
        int i = 0;

        i = file.read(bytes);
        System.out.println("i = " + i);
        // System.out.println(bytes);
        // System.out.println(Arrays.toString(bytes));

        System.out.println(new String(bytes));

        i=file.read(bytes);
        System.out.println("i = " + i);
        System.out.println(new String(bytes));

        i=file.read(bytes);
        System.out.println("i = " + i);
        System.out.println(new String(bytes));

        i=file.read(bytes);
        System.out.println("i = " + i);
        System.out.println(new String(bytes));

        i=file.read(bytes);
        System.out.println("i = " + i);
        System.out.println(new String(bytes));

        i=file.read(bytes);
        System.out.println("i = " + i);
        System.out.println(new String(bytes));

        file.close();
    }



    public static void readFile3() throws IOException {
        // 创建字符输入流对象，构造方法，传递字符串文件名，数据源
        FileInputStream in = new FileInputStream("d://aaa.txt");
        // 定义字节数组，长度建议1024整数倍
        byte[] bytes = new byte[1024];
        int i=0;
        while (((i=in.read(bytes))!=-1)){
            // 直接输出数组不行，读到几个字节，就输出几个字节
            System.out.println(new String(bytes,0,i));
        }
        in.close();
    }

}


