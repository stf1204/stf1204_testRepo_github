import java.io.*;

/**
 * @author stf
 * 字符输入输出缓冲流
 */
public class BufferedWriterAndReader {
    public static void main(String[] args) throws IOException {
        BufferedWriter();
        BufferedReader();
    }

    /**
     * 字符输入缓冲流：BufferedWriter
     * 提速字符输入流
     */
    public static void BufferedWriter() throws IOException {
        // 创建字符输入流对象
        FileWriter fw = new FileWriter("D://bbb.txt");
        // 创建字符输入流缓冲对象
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("我是第一！嘿嘿嘿");
        // 调用特有函数-换一行
        bw.newLine();
        bw.flush();
        bw.write("我是第二！嘿嘿嘿");
        // 调用特有函数-换一行
        bw.newLine();
        bw.flush();

        //关流
        bw.close();
    }

    /**
     * 字符输出缓冲流：BufferedReader
     * 提速字符输出流
     */
    public static void BufferedReader() throws IOException {
        //创建字符输出流对象
        FileReader fr = new FileReader("D://bbb.txt");
        //创建字符输出流缓冲对象
        BufferedReader br = new BufferedReader(fr);
        // char[] chars = new char[1024];
        String line = null;
        //循环读，每次读一行
        while ((line = br.readLine())!=null){
            System.out.println(line);
        }
        // 关流
        br.close();

    }
}
