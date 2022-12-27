import java.util.Properties;
import java.util.Set;

public class propertiesTest {

    public static void main(String[] args) {
        Properties prpo = new Properties();

        // setProperty() 添加键值对
        prpo.setProperty("a","beijing");
        prpo.setProperty("b","shanghai");
        prpo.setProperty("c","tianjin");
        System.out.println("prpo = " + prpo);
        System.out.println("________________________________________");

        // getProperty() 依据键查值
        String ss = prpo.getProperty("a");
        System.out.println("ss = " + ss);
        System.out.println("__________________________________________");

        // stringPropertyNames() 返回键集合
        Set<String> set =  prpo.stringPropertyNames();
        for (String s:set){
            System.out.println(s+ " === " + prpo.getProperty(s));
        }
        System.out.println("__________________________________________");
    }
}
