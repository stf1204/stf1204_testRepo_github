package com.atguigu.javase;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author stf
 * 文件操作三部曲
 * 1、new文件输入对象、输出对象
 * 2、对象调用写或读方法
 * 3、关闭系统资源
 */
public class FileOutputStreamTest {
    
    public static void main(String[] args) throws IOException {
      writeFile1();
      writeFile2();
      writeFile3();
      writeFile4();
    }


    /**
     * FileOutPutStream流对象，自动创建文件，如果文件已经存在，会覆盖
     * write(int i) 写入单个字节
     */
    public static void writeFile1() throws IOException{
        // 创建字节输出流，构造方法，传递字符串文件名（输出目的地）
        FileOutputStream file = new FileOutputStream("d:/aaa.txt");
        // 调用方法，写单个字节
        file.write(100);
        // 释放资源
        file.close();
    }


    /**
     * FileOutputStream
     * write(byte[] b) 写入字节数组
     */
    public static void writeFile2() throws IOException {
        // 创建字节输出流，并指定输出地址
        FileOutputStream file = new FileOutputStream("d:/aaa.txt");
        // 创建输出数组
        byte[] bytes = {97,98,99,100,101,102};
        // 输出
        file.write(bytes);
        // 关闭资源
        file.close();
    }


    /**
     * FileOutputStream
     * write(byte[] b,off start,len length) 写入部分字节数组，从哪儿开始，写入几个
     */
    public static void writeFile3() throws IOException {
        // 创建字节输出流对象
        FileOutputStream file = new FileOutputStream("d://aaa.txt");
        // 创建输出内容
        byte[] bytes = {97,98,99,100,101,102};
        //输出数组，从哪儿开始，输出几个
        file.write(bytes,1,3);
        //释放资源
        file.close();
    }


    /**
     * FileOutputStream
     * write(byte[] b,off start,len length) 写入部分字节数组，从哪儿开始，写入几个
     */
    public static void writeFile4(){
        FileOutputStream file = null;
        try {
            // 创建字节输出流对象,如果创建失败，对象还是null。第二个参数为  是否要追加，默认false
            file = new FileOutputStream("d://aaa.txt",true);
            // 创建输出内容
            byte[] bytes = {97,98,99,100,101,102};
            //输出数组，从哪儿开始，输出几个
            file.write(bytes,1,2);
            //释放资源
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            //关闭资源之前，需要先对资源对象进行判空处理，防止空指针异常
            if (file!=null) {
                try {
                    file.close();
                }catch (Exception e){
                    e.getMessage();
                }
            }
        }
    }


}
