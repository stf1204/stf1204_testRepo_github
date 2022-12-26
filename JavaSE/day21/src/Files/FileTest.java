package Files;

import java.io.File;

/**
 * @author stf
 * 遍历文件目录
 */
public class FileTest {

    public static void main(String[] args) {
        File file = new File("D://");

        //调用listFiles方法
        File[] fileArr = file.listFiles();
        for (File path : fileArr) {
            System.out.println("path = " + path);
        }
        // 文件总数
        System.out.println(fileArr.length);
    }
}
