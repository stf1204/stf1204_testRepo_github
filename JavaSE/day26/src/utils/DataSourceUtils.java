package utils;

/**
 * 德鲁伊连接池
 *
 * 数据库连接池工具类
 * 读取配置文件
 * 创建DateSource连接池接口的实现类对象
 * 实现类对象调用方法，返回连接connection
 * @author stf
 */

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceUtils {

    /**datasource写在成员变量中，提升作用域*/
    private static DataSource ds;

    static {
        try {
            //1、获取输入流对象
            InputStream is =
                    DataSourceUtils.class.getClassLoader().getResourceAsStream("druid.properties");

            //2、读取配置信息
            Properties pro = new Properties();
            pro.load(is);
            is.close();

            // System.out.println(pro)
            /**
             * DataSource接口的实现类，com.alibaba.druid.pool.DruidDataSource
             * 实现类的对象不要自己new对象
             *
             * DruidDataSourceFactory类的静态方法 createDataSource()帮我们new对象了
             * createDataSource()方法里面传递参数：传递Properties集合
             * 会从集合Properties中自己取出键值对，连接数据库
             */
            //3、 创建德鲁伊连接池对象
             ds = DruidDataSourceFactory.createDataSource(pro);
            // Connection con = ds.getConnection();
            // System.out.println("con = " + con);
        }catch (Exception e){}
    }


    /**
     * 外类调用
     * 1、获取数据连接对象con
     */
    public static Connection getConnection() throws SQLException {
        //4、从连接池取出连接对象，返回
        return ds.getConnection();
    }


    /**
     * 2、获取DataSource对象
     * 德鲁伊连接池实现DataSource接口，返回该对象
     */
    public static DataSource getDataSource(){
        return ds;
    }

}








// import com.alibaba.druid.pool.DruidDataSourceFactory;
//
// import javax.sql.DataSource;
// import java.io.InputStream;
// import java.sql.Connection;
// import java.util.Properties;
//
//
// public class DataSourceUtil{

//     private static DataSource dataSource;
//     static{
//         try {
//             // 获取输入流对象
//             InputStream in =
//                     DataSourceUtils.class.getClassLoader().getResourceAsStream("druid.properties");
//
//             // 拿到配置文件信息
//             Properties pro = new Properties();
//             pro.load(in);
//             in.close();
//             System.out.println("pro = " + pro);
//
//             // DruidDataSourceFactory 获取连接池对象
//             dataSource = DruidDataSourceFactory.createDataSource(pro);
//
//         }catch (Exception e){}
//     }
//
//     /**
//      通过连接池对象拿连接对象
//      */
//     public static Connection getConnection() throws Exception {
//         return dataSource.getConnection();
//     }
// }
