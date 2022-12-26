package com.atguigu.javase;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author stf
 */
public class OutputStreamWriterTest {

    public static void main(String[] args) throws IOException {
        // write();
        writeGBK();
    }

    /**
     * 输出流：OutputStreamWriter
     *是字符流和字节流的桥梁
     */
    public static void write() throws IOException {
        //字节输出流对象，绑定输出文件
        FileOutputStream fos = new FileOutputStream("D://aaa.txt");
        // 字符输出流对象，需要传递一个字节输出流对象
        // 字符对象会指定字节输出流对象对照编码表进行字符存储
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        //存储
        osw.write("你好");
        // 字符对象数据会暂时存储在内存中，需要通过flush刷新，才会写入磁盘
        osw.flush();
        osw.close();

    }




    /**
     * 输出流：OutputStreamWriter
     *是字符流和字节流的桥梁
     */
    public static void writeGBK() throws IOException {
        //字节输出流对象，绑定输出文件
        FileOutputStream fos = new FileOutputStream("D://aaa.txt");
        // 字符输出流对象，需要传递一个字节输出流对象
        // 字符对象会指定字节输出流对象对照编码表进行字符存储
        // IDEA默认会指定utf8进行编码，需要指明GBK编码
        OutputStreamWriter osw = new OutputStreamWriter(fos,"GBK");
        //存储
        osw.write("你好");
        // 字符对象数据会暂时存储在内存中，需要通过flush刷新，才会写入磁盘
        osw.flush();
        osw.close();

    }
}
