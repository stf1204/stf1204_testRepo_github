package com.atguigu.javase;

import com.atguigu.utils.DataSourceUtils;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;

/**
 * @author stf
 * 转账方法，需要参数：转账金额，付款人，收款人
 * 参数是用户输入的
 * 被web层调用，传递相关参数
 * 调用Dao层，控制事务
 *
 * 业务层获取连接池对象以后，要传递给Dao层，保证事务唯一性
 */
public class AccountService {

    public void transfer(String subName,String addName,Double money){
        //声明连接对象
        Connection con = null;
        try {
            //创建连接对象
            con = DataSourceUtils.getConnection();
            //关闭事务
            con.setAutoCommit(false);
            //创建dao层类的对象
            AccountDao accd = new AccountDao();
            //减钱
            accd.subMoney(con,money,subName);

            // int a = 1/0;

            //加钱
            accd.addMoney(con,money,addName);

            //提交事务，并悄悄地关闭资源
            DbUtils.commitAndCloseQuietly(con);

        }catch (Exception e){
            e.printStackTrace();
            //回滚事务，并悄悄地关闭资源
            DbUtils.rollbackAndCloseQuietly(con);
        }
    }
}
