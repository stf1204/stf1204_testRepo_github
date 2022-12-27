package Files;

import java.io.File;
import java.io.FileFilter;

/**
 * FileFilter接口类 只有一个accept方法，返回值为true时，listFile会保留，false会过滤
 * @author stf
 */
public class FIleFilter implements FileFilter {

    @Override
    public boolean accept(File pathname) {
        // 重写抽象方法
        // 拿到file目录名，先获取文件名，再转小写，最后进行判断
        return pathname.getName().toLowerCase().endsWith("java");
    }
}
class test{
    public static void main(String[] args) {
        File file = new File("D:\\Atguigu\\05_Code\\JavaSE\\day01");

        // new 接口类对象 作为值传入listFile()
        File[] files = file.listFiles(new FIleFilter());
        for (File f:files){
            System.out.println("f = " + f);
        }
    }
}
