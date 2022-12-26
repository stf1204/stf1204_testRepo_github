package com.atguigu.mapreduce.partitioner;

import com.atguigu.mapreduce.Flow.FlowBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 自定义分区器
 * 136，137，138，139为各自分区，其他开头为一个分区
 * @author stf
 */
public class MyPartition extends Partitioner<Text, FlowBean> {
    /**
     *
     * @param text 手机号
     * @param flowBean 自定义流量
     * @param numPartitions reduce分区总个数
     * @return
     */
    @Override
    public int getPartition(Text text, FlowBean flowBean, int numPartitions) {
        int result = 0;

        // 重写分区规则，按照我们的要求进行分区
        switch (text.toString().substring(0, 3)) {
            case "136":
                break;
            case "137":
                result = 1;
                break;
            case "138":
                result = 2;
                break;
            case "139":
                result = 3;
                break;
            default:
                result = 4;
                break;
        }
        return result % numPartitions;
    }
}
