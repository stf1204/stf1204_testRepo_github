package com.atguigu.javase.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author stf
 * 客户端使用ServerSocket
 * accept()获取客户端套接字
 */
public class Server {
    public static void main(String[] args) throws IOException {
        // 创建服务器连接对象
        ServerSocket ss = new ServerSocket(9999);
        // 获取客户端对象
        Socket client = ss.accept();
        //获取输入流对象
        InputStream cis = client.getInputStream();
        byte[] bytes = new byte[1024];
        int i = 0;
        //读取输入内容
        i = cis.read(bytes);
        System.out.println(new String(bytes, 0, i));

        //获取输出流对象
        OutputStream cos = client.getOutputStream();
        //回应消息
        cos.write("服务器说：你也好，我是服务器".getBytes());

        client.close();
        ss.close();
    }
}
