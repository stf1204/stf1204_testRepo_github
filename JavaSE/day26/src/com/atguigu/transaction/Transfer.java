package com.atguigu.transaction;

import org.junit.Test;
import utils.DataSourceUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author stf
 */
public class Transfer {

    @Test
    public void testTransfer() {
        // 声明数据库连接对象
        Connection con = null;

        //SQL语句执行对象
        PreparedStatement pst1 = null;
        PreparedStatement pst2 = null;

        try {
            //连接池工具类 获取连接对象
            con = DataSourceUtils.getConnection();

            //获取对象，立即开启事务，防止自动提交
            con.setAutoCommit(false);

            // sql1转账加钱
            String sql1 = "UPDATE account SET anum = anum+? WHERE aname = ?;";
            // sql2转账减钱
            String sql2 = "UPDATE account SET anum = anum-? WHERE aname = ?;";
            //获取sql执行对象
            pst1 = con.prepareStatement(sql1);
            pst2 = con.prepareStatement(sql2);

            //设置？占位符
            pst1.setObject(1, 1000);
            pst1.setObject(2, "张三");
            pst2.setObject(1, 1000);
            pst2.setObject(2, "李四");

            //执行sql
            int i = pst1.executeUpdate();
            // int a = 1/0;
            int j = pst2.executeUpdate();
            System.out.println("i = " + i);
            System.out.println("j = " + j);

            //执行完毕，sql语句没有异常，可以提交事务了
            con.commit();

        } catch (Exception e) {
            e.getMessage();

            try {
                // 执行失败，事务回滚
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } finally {

            //释放资源
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


            if (pst1 != null) {
                try {
                    pst1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


            if (pst2 != null) {
                try {
                    pst2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
