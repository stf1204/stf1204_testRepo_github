package com.atguigu.phoenix;

import org.apache.phoenix.queryserver.client.ThinClientUtil;
import java.sql.*;
import java.util.Properties;

/**
 * @ClassName: PhoenixThin
 * @PACKAGE: com.atguigu.phoenix
 * @Author: stf
 * @Date: 2022/12/25 - 14:36
 * @Description:
 * @Version: v1.0
 */
public class PhoenixThin {
    public static void main(String[] args) throws SQLException {


        // 1. 直接从瘦客户端获取链接
        String hadoop102 = ThinClientUtil.getConnectionUrl("hadoop102", 8765);
        // 2. 获取连接
        Connection connection = DriverManager.getConnection(hadoop102);

        // 3.编译SQL语句
        PreparedStatement preparedStatement = connection.prepareStatement("select * from student");

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



