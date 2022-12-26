package com.atguigu.dao;

import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author stf
 * 方法：减钱操作
 * 参数是金钱，用户名
 * 被业务层调用，传递参数
 */
public class AccountDao {

    public  void subMoney(Connection con,Double money, String name) throws SQLException {
        //创建QueryRunner对象
        QueryRunner qr = new QueryRunner();
        String sql = "update account set anum = anum-? where aname=?;";
        //执行sql
        qr.update(con,sql,money,name);
    }


    /**
     * 方法：加钱操作
     * 参数是金钱，用户名
     * 被业务层调用，传递参数
     */
    public  void addMoney(Connection con,Double money,String name) throws SQLException {
        //创建QueryRunner对象
        QueryRunner qr = new QueryRunner();
        String sql = "update account set anum=anum+? where aname=?;";
        //执行sqla
        qr.update(con,sql,money,name);
    }
}
