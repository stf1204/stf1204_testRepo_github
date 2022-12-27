package com.atguigu.mapreduce.Comparable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author stf
 */
public class ComparableMapper extends Mapper<LongWritable, Text, FlowBean, Text> {
    Text phone = new Text();
    FlowBean fb = new FlowBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] split = value.toString().split("\t");

        phone.set(split[0]);

        fb.set(Long.parseLong(split[1]), Long.parseLong(split[2]));

        context.write(fb, phone);

    }
}
