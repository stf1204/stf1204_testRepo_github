package com.atguigu.javase.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author stf
 * 客户端使用Sockect套接字对象
 */
public class Client {
    public static void main(String[] args) throws IOException {
        //获取连接对象
        Socket s = new Socket("localhost", 9999);
        //获取输出对象
        OutputStream os = s.getOutputStream();

        // 调用String的getBytes()方法
        os.write("客户端说：你好，服务器！".getBytes());

        //获取输入流对象
        InputStream is = s.getInputStream();

        byte[] bytes = new byte[1024];
        int i = 0;
        //读取回应消息
        i = is.read(bytes);
        System.out.println(new String(bytes, 0, i));

        s.close();
    }
}

