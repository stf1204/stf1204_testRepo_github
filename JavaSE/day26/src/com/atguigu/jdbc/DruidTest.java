package com.atguigu.jdbc;

import com.atguigu.pojo.Product;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;
import utils.DataSourceUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author stf
 * 德鲁伊连接池
 */

public class DruidTest {

    @Test
    /**
     * ScalarHandler
     * 返回值类型为 单行单列
     * query()用来执行select语句
     * 参数1：sql语句
     *
     * 参数2：ScalarHandler实现类
     *
     * 参数3：sql语句需要的参数
     */
    public void testDbutisQuery3() throws SQLException {
        // 创建queryRunner类对象
        // 构造方法传递DataSource接口实现类对象
        // 执行Sql 无需传递连接对象
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String  sql = "select count(1) from product";
        // qr对象调用query查询，第二个参数中，传递ScalarHandler实现类
        // 返回值类型不确定，所以用object来接
        Object obs = qr.query(sql, new ScalarHandler<>());
        System.out.println("obs = " + obs);

    }

    @Test
    /**
     * BeanListHandler
     * 返回值类型为 多行
     * query()用来执行select语句
     * 参数1：sql语句
     *
     * 参数2：BeanListHandler实现类
     *
     * 参数3：sql语句需要的参数
     */
    public void testDbutisQuery2() throws SQLException {
        // 创建queryRunner类对象
        // 构造方法传递DataSource接口实现类对象
        // 执行Sql 无需传递连接对象
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product";
        // qr对象调用query查询，第二个参数中，传递BeanListHandler实现类
        // 返回值为 List<查询对象>
        List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class));
        // 如果查询不到，list仍然存在，只是长度size()为0
        for (Product product : list) {
            System.out.println("product = " + product);
        }

    }


    @Test
    /**
     * BeanHandler
     * 返回值类型为 单行
     * query()用来执行select语句
     * 参数1：sql语句
     * 参数2：BeanHandler实现类
     * 参数3：sql语句需要的参数
     */
    public void testDbutisQuery1() throws SQLException {
        // 创建queryRunner类对象
        // 构造方法传递DataSource接口实现类对象
        // 执行Sql 无需传递连接对象
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product";
        // qr对象调用query查询，第二个参数中，传递BeanHandler实现类
        //BeanHandler<查询类>(查询类.class)
        // 返回值为 查询对象类型
        Product pro = qr.query(sql, new BeanHandler<Product>(Product.class));
        System.out.println(pro);

    }

    @Test
    /**
     * QueryRunner无参update
     */
    public void tetDbutisUpdate() throws SQLException {

        // QueryRunner获取Dbutis对象(无参)
        QueryRunner qr = new QueryRunner();

        String sql = "update product set pname=?,price=?,num=? where pid = ?";

        Object[] params = {"茶Π",6.5,13,20016};

        qr.update(DataSourceUtils.getConnection(),sql,params);
    }

    @Test
    /**
     *
     * 简化sql执行
     * QueryRunner无参insert
     * Apache.commons.dbutils.QueryRunner类
     * update()用来执行insert，update，delete
    */
    public void testDbutisInsert() throws SQLException {

        // QueryRunner--获取QueryRunner对象(无参)
        QueryRunner qr = new QueryRunner();

        String sql = "insert into product values(?,?,?,?,?)";

        Object[] params = {null,"元气森林",5,41234,3};

        qr.update(DataSourceUtils.getConnection(),sql,params);


    }


    @Test
    /**
     * 更新
     */
    public void testUpdate() throws SQLException{

        //1、获取数据库连接对象
        Connection con = DataSourceUtils.getConnection();

        //2、获取sql语句执行对象
        String sql = "update  product set price=? where pid=?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setObject(1,8889);
        ps.setObject(2,20014);

        //3、执行sql语句
        int lines = ps.executeUpdate();
        System.out.println("lines = " + lines);

        //4、释放资源
        ps.close();
        con.close();
    }


    @Test
    /**
     * 插入
     */
    public void testInsert() throws SQLException {
        //1、获取数据库连接对象
        Connection con = DataSourceUtils.getConnection();

        //2、获取sql语句执行对象
        String sql = "insert into product values(?,?,?,?,?);";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setObject(1,null);
        pst.setObject(2,"电视机");
        pst.setObject(3,9999);
        pst.setObject(4,8);
        pst.setObject(5,1);

        //3、执行sql语句
        int lines = pst.executeUpdate();
        System.out.println("lines = " + lines);

        //4、释放资源
        pst.close();
        con.close();
    }
}
