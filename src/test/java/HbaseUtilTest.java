import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @ClassName: HbaseUtilTest
 * @PACKAGE: PACKAGE_NAME
 * @Author: stf
 * @Date: 2022/12/24 - 19:59
 * @Description:
 * @Version: v1.0
 */
public class HbaseUtilTest {
    private HbaseUtil hbaseUtil = new HbaseUtil();

    @Test
    public void testGet() throws IOException {

        // 获取表对象
        Table t1 = hbaseUtil.getTable("t1");

        // 用的时候才声明一个Get对象
        Get get = new Get(Bytes.toBytes("1005"));
        Result line = t1.get(get);

        hbaseUtil.parseResult(line);
        t1.close();
    }


    @Test
    public void tesPut() throws IOException {

        Table t1 = hbaseUtil.getTable("t1");

        // 封装一个Put对象
        Put put1 = hbaseUtil.getPut("1005", "info", "name", "wangba");
        Put put2 = hbaseUtil.getPut("1005", "info", "age", "22");
        Put put3 = hbaseUtil.getPut("1005", "info", "gender", "male");

        ArrayList<Put> puts = new ArrayList<>();
        puts.add(put1);
        puts.add(put2);
        puts.add(put3);

        t1.put(puts);

        t1.close();
    }


    @Test
    public void testScan() throws IOException {

        // 获取表对象
        Table t1 = hbaseUtil.getTable("t1");

        // 用的时候才声明一个Get对象
        Scan scan = new Scan();
        scan.withStartRow(Bytes.toBytes("1001"));
        scan.withStopRow(Bytes.toBytes("1007"));

        // Result(一行)的集合
        ResultScanner scanner = t1.getScanner(scan);

        for (Result result : scanner) {
            hbaseUtil.parseResult(result);
        }

        t1.close();
    }

    @Test
    public void testDelete() throws IOException {
        // 获取表对象
        Table t1 = hbaseUtil.getTable("t1");
        // 构造删除一行的Delete对象
        Delete delete = new Delete(Bytes.toBytes("1004"));
        //进一步-》删除那一列？

//        增加一个cell：timestamp=当前最新cell的ts, type=Delete。 删除这行的最新版本
//        delete.addColumn(Bytes.toBytes("info"),Bytes.toBytes("age"));

//        增加一个cell：timestamp=当前的最新ts, type=DeleteColumn。  删除这一整列
//        delete.addColumns(Bytes.toBytes("info"),Bytes.toBytes("age"));

//        增加一个cell：column=列族, timestamp=最新ts, type=DeleteFamily。 删除这一个列族
//        delete.addFamily(Bytes.toBytes("info"));

//        给当前的每一列的列族都执行DeleteFamily
        t1.delete(delete);
        t1.close();
    }
}