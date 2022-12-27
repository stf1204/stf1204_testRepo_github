package com.atguigu.jdbc;

import com.atguigu.pojo.Product;
import org.junit.Test;
import utils.DataSourceUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * JDBC技术，连接数据库
 * 实现数据表新增数据
 * -注册驱动程序
 * -获取数据库连接(网络连接数据库，TCP)
 * -获取sql语句的执行对象
 * -执行sql语句
 * -处理查询的结果集
 * -释放资源


 * JDBC开发数据库
 *
 * @author stf
 */
@SuppressWarnings("all")
public class JdbcDemo01 {

    public static void main(String[] args) throws Exception {
        testLogin();
    }






    @Test
    public void test() throws Exception {
        long start = System.currentTimeMillis();
        testJdbcDemo2();
        long end = System.currentTimeMillis();
        System.out.println("time = " + (end-start));
    }
    /**
     * 循环10000次
     * time = 668
     * 往数据库循环插入数据，开启批处理rewriteBatchedStatements=true
     * pres.addBatch()和pres.executeBatch();提升速度
     */
    public void testJdbcDemo2()throws Exception{
        //创建连接数据库对象
        Connection con = DataSourceUtils.getConnection();

        //获取执行sql语句对象
        String sql = "insert into product values(?,?,?,?,?)";
        PreparedStatement pres = con.prepareStatement(sql);

        //循环10000次
        for (int i=1; i<=10000;i++){
            pres.setObject(1,null);
            pres.setObject(2,"电视机"+i);
            pres.setObject(3,3999);
            pres.setObject(4,100);
            pres.setObject(5,1);

            pres.addBatch();
        }

        pres.executeBatch();

        //释放资源
        pres.close();
        con.close();

    }


    /**
     * 循环10000次
     * time = 405，994
     * 往数据库循环插入数据， 时间太慢
     */
    public void testJdbcDemo() throws Exception {
        //创建连接数据库对象
        Connection con = DataSourceUtils.getConnection();

        //获取执行sql语句对象
        String sql = "insert into product values(?,?,?,?,?)";
        PreparedStatement pres = con.prepareStatement(sql);

        //循环10000次
        for (int i=1; i<=10000;i++){
            pres.setObject(1,null);
            pres.setObject(2,"电视机"+i);
            pres.setObject(3,3999);
            pres.setObject(4,100);
            pres.setObject(5,1);

            //执行sql语句
            pres.executeUpdate();
        }

        //释放资源
        pres.close();
        con.close();

    }


    @Test
    /**
     * statement的子接口 prepareStatement
     * 连接对象直接创建，返回PreparedStatement对象
     * setObject进行给sql语句传值
     * 使用statement接口的子接口 prepareStatement来执行SQL语句，更安全，查询速度更快
     */
    public void testPreparedStatement() throws Exception{
        Connection con = DataSourceUtils.getConnection();
        String sql = "SELECT * FROM USER WHERE username= ? AND `password` =? ;";

        //使用statement的子接口 prepareStatement 更安全，查询速度更快
        PreparedStatement ps = con.prepareStatement(sql);

        //setObject() 给占位符传递值
        ps.setObject(1,"badou");
        ps.setObject(2,"11");

        //执行ps的executeQuary()方法，不需要再传递值
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            //取出列值
            int uid = rs.getInt("uid");
            String username = rs.getString("username");
            String password = rs.getString("password");
            System.out.println("uid:"+uid+", username:"+username+", password:"+password);
        }

        con.close();
        rs.close();


    }



    /**
     * 数据库注入攻击
     * 前面使用的数据库查询技术不安全
     */
    public static void testLogin() throws Exception{
        // 键盘录入
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String user = sc.nextLine();
        System.out.println("请输入密码：");
        String pass = sc.nextLine();

        //连接对象
        Connection con = DataSourceUtils.getConnection();

        //执行对象
        Statement sta = con.createStatement();
        String sql = "SELECT * FROM USER WHERE username='"+user+"' AND `password` = '"+pass+"';";

        //注入攻击
        //sql = SELECT * FROM USER WHERE username='badou' AND `password` = '1sda' or'1=1';
        System.out.println("sql = " + sql);
        ResultSet rs = sta.executeQuery(sql);
        while (rs.next()) {
            int uid = rs.getInt("uid");
            String username = rs.getString("username");
            String password = rs.getString("password");
            System.out.println(username + "\t" + password);
        }

        //释放资源
        sta.close();
        con.close();
        rs.close();
    }




    @Test
    /**
     * 查询数据存入到Javabean对象中，再存入list中
     * 最后从List读取数据
     */
    public void testJavaBean() throws Exception{
        //连接对象
        Connection con = DataSourceUtils.getConnection();

        //执行对象
        Statement state = con.createStatement();
        String sql = "SELECT * FROM product;";

        //执行
        ResultSet re = state.executeQuery(sql);

        //将结果存储在product实例对象（JavaBean）中
        //实例对象存储在list中
        List<Product> se = new ArrayList<>();
        while (re.next()){
            Product pro = new Product();
            int pid = re.getInt("pid");
            pro.setPid(pid);
            String pname = re.getString("pname");
            pro.setPname(pname);
            double price = re.getDouble("price");
            pro.setPrice(price);
            int num = re.getInt("num");
            pro.setNum(num);
            int category = re.getInt("category");
            pro.setCategory(category);

            se.add(pro);
        }

        //遍历结果集se
        for (Product p:se){
            System.out.println("p = " + p);
        }

        //释放资源
        re.close();
        con.close();
        state.close();
    }


    @Test
    /**
     *读取数据库数据
     *
     * 数据库连接对象获取方式封装，通过DataSourceUtils.getConnection()获取
     * 执行对象state调用方法executeQuery()返回ResultSet类对象
     * ResultSet对象rs的getXXX(列名)，返回列的值
     */
    public void testSelect() throws Exception{
        // 工具类获取数据库连接对象
        Connection con = DataSourceUtils.getConnection();

        // 获取执行对象
        Statement state = con.createStatement();

        // 执行sql语句
        String sql = "SELECT * FROM product;";
        // 执行查询语句使用executeQuery(String sql) 执行增删改语句使用executeUpdate(String sql)
        // executeQuerty返回对象是ResultSet对象
        ResultSet rs = state.executeQuery(sql);

        // Boolean next()类似于collections接口中的迭代器iterator的hasNext()
        while (rs.next()){
            //取出数据表中列的数据：结果集对象rs的getXXX(列名)，返回列的值
            int pid = rs.getInt("pid");
            String pname = rs.getString("pname");
            double price = rs.getDouble("price");
            int num = rs.getInt("num");
            int category = rs.getInt("category");

            System.out.println(pid + "\t" + pname + "\t" + price  + "\t"+ num + "\t" + category);
        }

        // 释放资源
        con.close();
        state.close();
        rs.close();
    }

    @Test
    /**
     * 删除数据
     */
    public void testDelete() throws Exception{
        // 获取数据库连接驱动
        Class.forName("com.mysql.jdbc.Driver");

        // 获取连接对象
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my2", "root", "root");

        // 获取执行对象
        Statement state = con.createStatement();

        // 执行sql语句
        state.executeUpdate("delete from product where pid=14");

        // 关闭资源
        con.close();
        state.close();
    }

    @Test
    /**
     * 更新数据
     */
    public void testUpdate()throws Exception{
        // 获取驱动
        Class.forName("com.mysql.jdbc.Driver");

        // 连接数据库
        String url = "jdbc:mysql://localhost:3306/my2";
        String user = "root";
        String password = "root";
        Connection con = DriverManager.getConnection(url, user, password);

        // 获取执行对象
        Statement state = con.createStatement();

        String sql = "update product set pname='饼干',price=33,num=3306 where pid = 14";
        // 执行sql
        int lines = state.executeUpdate(sql);
        System.out.println("lines = " + lines);

        // 关闭资源
        con.close();
        state.close();
    }




    @Test

    /**
     * 2、0 通过 类对象注册驱动，替代DriverManager

     * 通过Java连接数据库，插入数据
     * DriverManager注册驱动
     * DriverManager获得连接对象
     * 连接对象 createStatement()创建sql执行对象
     * 执行对象执行sql语句executeUpdate()
     * 最后释放资源
     */
    public void testRegister() throws  Exception{
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/my2";
        String user = "root";
        String pass = "root";
        Connection con = DriverManager.getConnection(url,user,pass);

        System.out.println("con = " + con);

        con.close();
    }


    @Test
    /**
     * 1、0
     * 通过Java连接数据库，插入数据
     * DriverManager注册驱动
     * DriverManager获得连接对象
     * 连接对象 createStatement()创建sql执行对象
     * 执行对象执行sql语句executeUpdate()
     * 最后释放资源
     */
    public void testInsert() throws Exception {

        // -1、注册驱动程序:告知JVM，需要使用哪个数据库驱动
        // DriverManager类静态方法 state void registerDriver(Driver d)
        // driver参数：driver是接口传递实现类的对象
        // 接口实现类：数据库驱动实现
        // DriverManager.registerDriver(new Driver())
        Class.forName("com.mysql.jdbc.Driver");


        // -2、获取数据库连接(网络连接数据库，TCP)
        // DriverManager类的静态方法，返回数据库连接对象Connection接口实现类
        // static Connection getConnection(String url，String user，String password)
        // 数据库连接地址：连接方式：数据库厂商名：//数据库服务器Ip地址：端口号/数据库名字
        String url = "jdbc:mysql://localhost:3306/my2";
        String user = "root";
        String pass = "root";
        Connection con = DriverManager.getConnection(url,user,pass);
        System.out.println("con = " + con);


        // -3、获取sql语句的执行对象
        // sql语句执行对象是接口Statement的实现类对象
        // 数据库连接对象方法：
        // Statement createStatement() 返回sql语句执行对象
        Statement state = con.createStatement();


        // -4、执行sql语句
        // sql语句执行对象的方法executeUpdate(String sql)
        String sql = "insert into product values(null,'面包',10.5,1555,2)";
        int line = state.executeUpdate(sql);
        System.out.println("line = " + line);


        // -5、释放资源
        state.close();
        con.close();



    }
}
