import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;

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

        Table t1 = hbaseUtil.getTable("t1");

        // 封装一个Get对象
        Get get = new Get(Bytes.toBytes("1001"));
        Result line = t1.get(get);

        hbaseUtil.parseResult(line);



    }

}