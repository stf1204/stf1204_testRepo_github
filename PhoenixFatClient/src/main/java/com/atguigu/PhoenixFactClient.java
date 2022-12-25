package com.atguigu;

import java.sql.*;
import java.util.Properties;
/**
 * @ClassName: PhoenixFactClient
 * @PACKAGE: com.atguigu
 * @Author: stf
 * @Date: 2022/12/25 - 13:48
 * @Description:
 * JDBC:
 *      ①：新建一个Connection，url，driver，user，password
 *      ②：从Connection中获取一个prepareStatement
 *      ③：使用prepare Statement预编译sql，如果是查询，可以选填充占位符
 *      ④：执行查询或者写
 *      ⑤：如果是查询就遍历ResultSet
 *      ⑥：关闭连接
 * @Version: v1.0
 */
public class PhoenixFactClient {
    public static void main(String[] args) throws SQLException {

        // 1.添加链接
        String url = "jdbc:phoenix:hadoop102:2181";
        Properties props = new Properties();

        // 2.获取连接
        Connection connection = DriverManager.getConnection(url,props);

        // 3.编译SQL语句
        PreparedStatement preparedStatement = connection.prepareStatement("select * from STUDENT");

        // 4.执行语句
        ResultSet resultSet = preparedStatement.executeQuery();

        // 5.输出结果
        while (resultSet.next()){
            System.out.println(resultSet.getString(1) + ":" + resultSet.getString(2) + ":" + resultSet.getString(3));
        }

        // 6.关闭资源
        connection.close();
    }
}

