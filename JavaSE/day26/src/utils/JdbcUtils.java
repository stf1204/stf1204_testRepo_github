package utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @author stf
 */

public class JdbcUtils {

    /** 静态代码块访问静态变量  */
    private static String className;
    private static String url;
    private static String userName;
    private static String passWord;

    static {
        try {

            InputStream is
                    = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties pro = new Properties();
            pro.load(is);
            is.close();

            // 需要提升作用域
            className = pro.getProperty("ClassName");
            url = pro.getProperty("url");
            userName = pro.getProperty("UserName");
            passWord = pro.getProperty("PassWord");

            // 驱动也只用获取一次
            Class.forName(className);
        }
        catch (Exception e){}
    }

    public static Connection getConnection() throws Exception{
        /*
        // 获取驱动
        Class.forName("com.mysql.jdbc.Driver")

        // 连接数据库
        String url = "jdbc:mysql://localhost:3306/my2";
        String user = "root";
        String password = "root";

         */

        Connection con = DriverManager.getConnection(url, userName, passWord);
        return con;
    }
}
