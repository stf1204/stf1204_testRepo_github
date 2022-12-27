package com.atguigu.mapreduce.join.reducejoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @author stf
 */
public class OrderMapper extends Mapper<LongWritable, Text,Text,OrderBean> {

    private Text pid = new Text();
    private OrderBean ob = new OrderBean();
    private String filename = "";

    /**
     * 区分数据来源的方式，最稳妥的方式就是通过文件的文件名判断
     *mapper在处理之前就应该知道这个文件来源于谁
     *
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        // 获取文件名

        // 获取输入切片
        InputSplit is = context.getInputSplit();

        // 将切片转成FileSplit，获取文件名
        //  使用的 是这个org.apache.hadoop.mapreduce.lib.input.FileSplit 啊啊 啊啊啊啊
        if (is instanceof org.apache.hadoop.mapreduce.lib.input.FileSplit){
            FileSplit fs = (FileSplit) is;
            filename = fs.getPath().getName();
        }
    }

    /**
     * 假设已经拿到文件名，如何进行操作
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] files = value.toString().split("\t");

        if ("order.txt".equals(filename)) {
            // 说明数据来源于order 三列分别是 id,pid,amount
            pid.set(files[1]);

            ob.setId(files[0]);
            ob.setPid(files[1]);
            ob.setAmount(Integer.parseInt(files[2]));
            ob.setPname("");
        } else {
            // 说明数据来源pd,两列分别是pid,pname
            pid.set(files[0]);

            ob.setPid(files[0]);
            ob.setPname(files[1]);
            ob.setId("");
            ob.setAmount(0);
        }

        context.write(pid,ob);
    }


}
