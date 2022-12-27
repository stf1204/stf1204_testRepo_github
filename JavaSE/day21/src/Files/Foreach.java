package Files;

import java.io.File;

/**
 * 递归打印文件名称
 * @author stf
 */
public class Foreach  {
    public static void main(String[] args) {

        getAll(new File("E:\\八斗\\尚硅谷22-4月开班\\02、第1~28天-javase(29天)"));
    }
    public static void getAll (File srcDir){
        //先打印父目录
        System.out.println(srcDir);
        // 获取文件数组
        File[] files = srcDir.listFiles();

        for (File f:files) {
            // 如果f是文件夹 递归该子文件夹
            if (f.isDirectory()) {
                getAll(f);
            }
            else{
                //真正逻辑块
                System.out.println(f);
            }
        }
    }
}
