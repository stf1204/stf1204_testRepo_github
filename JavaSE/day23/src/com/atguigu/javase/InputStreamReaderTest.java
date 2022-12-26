package com.atguigu.javase;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputStreamReaderTest {
    public static void main(String[] args) throws IOException {
        ReaderGbk();
    }

    /**
     *输入流：InputStreamReader
     * 是字节流和字符流的桥梁
     */
    public static void ReaderGbk() throws IOException {
        // 创建输入流字节对象，绑定输入源
        FileInputStream fis = new FileInputStream("D:\\aaa.txt");
        // 创建输入字符流对象，指定编码表为GBK表
        InputStreamReader isr = new InputStreamReader(fis,"gbk");
        int i =0;
        char[] chars = new char[1024];
        // 循环读取数据
        while ((i = isr.read(chars))!=-1){
            System.out.println("i = " + i);
            System.out.println(new String(chars,0,i));
        }
        // 关流
        isr.close();
    }
}
