import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author stf
 */
public class FileWriterAndReader {
    public static void main(String[] args) throws IOException {
        WriteFile();
        System.out.println("------");
        ReadFile();
    }

    /**
     *字符输入流
     * FileWriter
     */
    public static void WriteFile() throws IOException {
        //创建字符便捷输出流
        FileWriter fw = new FileWriter("D:\\bbb.txt");
        //输出内容
        fw.write("你好，我好，大家好！！！");
        //关流
        fw.close();
    }

    /**
     * 字符输出流
     *FileReader
     */
    public static void ReadFile() throws IOException {
        // 创建字符便捷输入流
        FileReader fr = new FileReader("D://bbb.txt");
        int i = 0;
        char[] chars = new char[1024];
        // 遍历
        while ((i=fr.read(chars))!=-1){
            System.out.print(new String(chars,0,i));
        }
        // 关流
        fr.close();
    }
}
