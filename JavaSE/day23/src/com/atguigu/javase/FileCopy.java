package com.atguigu.javase;

import java.io.*;

/**
 * @author stf
 */
public class FileCopy {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // copy1();    //time = 387402
        // copy2();    //time = 513
        copy3();    //time = 117
        long end = System.currentTimeMillis();
        System.out.println("time = "+ (end - start));

    }

    /**
     * 一次读取一个字节，一次写入一个字节
     *
     * 输入流：FileInputStream   read()
     * 输出流：FileOutputStream  write()
     *
     * 输入文件：D:\d.exe
     * 输出文件：E:\d.exe
     */
    public static void copy1(){
        //定义好需要的流对象
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            // 实例化输入输出流对象，绑定数据源
            fis = new FileInputStream("D:\\d.exe");
            fos = new FileOutputStream("E:\\d.exe");
            // 循环读数据、写数据
            int i =0;
            while ((i=fis.read())!=-1){
                fos.write(i);
            }
        }catch (IOException e){
            e.getMessage();
        }finally{
            // 释放资源
            if (fis!=null){
                try {
                    fis.close();
                }catch (IOException e){}
            }

            if (fos!=null){
                try {
                    fos.close();
                }catch (IOException e){}
            }
        }
    }


    /**
     * 一次读取一个字节数组，一次写入一个字节数组
     *
     * 输入流：FileInputStream   read(byte[])
     * 输出流：FileOutputStream  write(byte[],int off,int len)
     *
     * 输入文件：D:\d.exe
     * 输出文件：E:\d.exe
     */
    public static void copy2(){
        // 定义好需要的流对象
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            // 实例化输入、输出对象，绑定数据源
            fis = new FileInputStream("D:\\d.exe");
            fos = new FileOutputStream("E:\\d.exe");

            int i = 0;
            byte[] bytes = new byte[1024];
            //循环遍历读取、存储数据
            while ((i=fis.read(bytes))!=-1){
                fos.write(bytes,0,i);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {

            // 释放资源
            if (fis!=null){
                try {
                    fis.close();
                }catch (IOException e){}
            }

            if (fos!=null){
                try {
                    fos.close();
                }catch (IOException e){}
            }
        }
    }


    /**
     *用字节缓冲流提速，一次读取一个字节数组，一次写入一个字节数组

     * 输入流：BufferedInputStream   read(byte[])
     * 输出流：BufferedOutputStream  write(byte[],int off,int len)
     *
     * 输入文件：D:\d.exe
     * 输出文件：E:\d.exe
     */
    public static void copy3(){
        //定义输入字节流，输出字节流
        // FileInputStream fis = null;
        // FileOutputStream fos = null;
        // 缓冲输入字节流，缓冲输出字节流，对传入的输入输出流进行提速
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            //实例化对象，绑定数据
            bis = new BufferedInputStream(new FileInputStream("D:\\d.exe"));
            bos = new BufferedOutputStream(new FileOutputStream("E:\\d.exe"));
            int i=0;
            byte[] bytes = new byte[1024];
            //循环写入数据
            while ((i = bis.read(bytes))!=-1){
                // 写入bytes数组字符，从0开始写，写i个
                bos.write(bytes,0,i);
            }
        }catch (IOException e){
            e.getMessage();
        }finally {
            //释放资源
            if (bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bis!=null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
