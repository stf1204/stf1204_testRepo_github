package com.atguigu.mapreduce.Flow;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author stf
 */
public class flowMapper extends Mapper <LongWritable,Text,Text,FlowBean>{

    Text k = new Text();
    FlowBean v = new FlowBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String lines = value.toString();
        String[] split = lines.split("\t");

        String phone = split[1];
        long up = Long.parseLong(split[split.length - 3]);
        long down = Long.parseLong(split[split.length - 2]);

        k.set(phone);
        v.set(up,down);

        context.write(k,v);
    }
}
