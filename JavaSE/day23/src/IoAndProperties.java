import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * IO流 和 properties配置信息load()方法的联合使用
 * @author stf
 */
public class IoAndProperties {

    public static void main(String[] args) throws IOException {

        //创建输入流对象
        FileInputStream fis = new FileInputStream("day23//config.properties");

        //创建配置对象
        Properties prop = new Properties();

        //配置对象加载配置信息
        prop.load(fis);

        //关闭输入流
        fis.close();

        System.out.println("prop = " + prop);


    }
}
