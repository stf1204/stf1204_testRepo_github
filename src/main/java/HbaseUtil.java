/**
 * @ClassName: HbaseUtil
 * @PACKAGE: PACKAGE_NAME
 * @Author: stf
 * @Date: 2022/12/24 - 18:03
 * @Description:
 * @Version: v1.0
 */

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;


import java.io.IOException;

/**
 * 读写数据库，把读写时候常用的方法写入到一个工具类中，让其他代码调用。
 * <p>
 * 敏感性：
 * 客户端------> 链接 ------> 发命令 -------> 服务端
 * <p>
 * 如何实例化链接： ConnectionFactory
 * 谁调用，谁管理，不用的时候，及时释放，
 * 从Connection获取Table和Admin对象
 * Table:操作Hbase表，DML
 * Admin:管理表，增删改 DDL
 * Connection是重量级的，并且是线程安全的，可以在不同的线程中共享，官方不建议频繁的进行定义，严重消耗资源，一般是一个APP创建一次。
 * Table 和 Admin 是轻量级的，线程不安全的，谁调用，谁创建，不建议池化和缓存。
 * <p>
 * 线程安全：
 * 对象可以声明为静态变量(static)和成员变量(类体中定义)
 * 线程不安全：
 * 对象声明为方法内部的局部变量。
 * --------------------------------------------------------------------------------
 * 客户端是如何来连接服务端？
 * 通过ZK 查询meta表
 * 客户端无论是读还是写都要找ZK
 * ---------------------------------------------------------------------------------
 * <p>
 * 增：改： Table.put(Put put)
 * 删:      Table.delete(Delete d)
 * 查单行:    Table.get(Get g)
 * 查多行:    Table.scan(Scanner s)
 * <p>
 * ---------------------------------------------------------------------------
 * <p>
 * 工具类：
 * Bytes：
 * 将常见的数据类型转为Byte[]  Bytes.toBytes(XXX)
 * 将byte[]转为常见的数据类型: Bytes.toXXX(byte[] b)
 * <p>
 * CellUtil:
 * CellUtil.cloneXXX(Cell c) 获取cell的某个XXX(rowKey,cf,cq,value)属性
 */
public class HbaseUtil {

    /**
     * 遍历result结果
     */
    public void parseResult(Result result) {
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {
            System.out.println("rowKey = " + Bytes.toString(CellUtil.cloneRow(cell)));
            System.out.println("cf:cq =" + Bytes.toString(CellUtil.cloneFamily(cell)) + ":" + Bytes.toString(CellUtil.cloneQualifier(cell)));
            System.out.println("value = " + Bytes.toString(CellUtil.cloneValue(cell)));
        }
    }

    /**
     * 封装Put对象的方法
     * Put 表名, rowKey，cf：cq，value
     */
    public Put getPut(String rowKey, String cf, String cq, String value) {
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cq), Bytes.toBytes(value));
        return put;
    }


    /**
     * 获取表名对象
     */
    public Table getTable(String tableName) throws IOException {

        //StringUtils: commons.lang
        if (StringUtils.isBlank(tableName)) {
            System.err.println("表名非法!");
            throw new RuntimeException("表明非法！");
        }
        Table table = connection.getTable(TableName.valueOf(tableName));
        return table;
    }

    /**
     * 成员变量
     */
    Connection connection = null;

    {
        try {
            //
            //return createConnection(HBaseConfiguration.create(), null, null);
            // 只要连接到hbase，在创建configuration时，只能通过HBaseConfiguration.create去创建。
            // 不能直接new Configuration() 自己new的config，不会调用
            //    conf.addResource("hbase-default.xml")
            //    conf.addResource("hbase-site.xml") 加载这两个配置文件
            connection = ConnectionFactory.createConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
  /*
    /**
     * 获取Hbase,Connection
     * @return
    public Connection getConn() {
    }*/


    /**
     * 释放链接Connection
     *
     * @param con
     * @throws IOException
     */
    public void close(Connection con) throws IOException {
        if (con != null) {
            con.close();
        }
    }

}
