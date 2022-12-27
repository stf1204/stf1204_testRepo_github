package Files;

import java.io.File;
import java.io.IOException;

/**
 * @author stf
 */
public class FilesTest {

    public static void main(String[] args) {
        PanDuan.isExists();
        PanDuan.isFile();
        PanDuan.isDirectory();
        CreateNewFileTest.createFile();
        DeleteTest.deleteFile();

    }


    /**
     * File对象创建1
     * constructor1
     */
    public static void test(){
        File file = new File("E:\\八斗\\尚硅谷22-4月开班\\02、第1~28天-javase(29天)");
        System.out.println("file = " + file);
    }


    /**
     * File对象创建2
     * constructor2
     */
    public static void test2(){
        File file = new File("E:\\八斗\\尚硅谷22-4月开班\\","02、第1~28天-javase(29天)");
        System.out.println("file = "+file);
    }


    /**
     * File对象创建3
     * constructor3
     */
    public static void test3(){
        File file1 = new File("E:\\八斗\\尚硅谷22-4月开班\\");

        File file = new File(file1,"02、第1~28天-javase(29天)");
        System.out.println("file = " + file);
    }
}

/**
 * // 通过file对象创建文件
 */
class CreateNewFileTest{

    public static void createFile(){
        File file = new File("E:/a.txt");
        try {
            boolean b = file.createNewFile();
            System.out.println("b = " + b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createDirectory(){
        File file = new File("D:\\a\\b\\c\\d");
        boolean b = file.mkdirs();
        System.out.println("b = " + b);
    }
}

/**
 *  通过file对象删除文件
 */
class DeleteTest{

    public static void deleteFile(){
        File file = new File("D:\\a");
        boolean b = file.delete();
        System.out.println("b = " + b);
    }
}



class PanDuan{

public  static void isExists(){
    File file = new File("E:\\八斗\\尚硅谷22-4月开班\\02、第1~28天-javase(29天)");
    boolean b = file.exists();
    System.out.println("Exists = " + b);
}

public static void isFile(){
    File file = new File("E:\\八斗\\尚硅谷22-4月开班\\02、第1~28天-javase(29天)");
    boolean b = file.isFile();
    System.out.println("File = " + b);
}

public static void isDirectory(){
    File file = new File("E:\\八斗\\尚硅谷22-4月开班\\02、第1~28天-javase(29天)");
    boolean b = file.isDirectory();
    System.out.println("Directory = " + b);
}
}

