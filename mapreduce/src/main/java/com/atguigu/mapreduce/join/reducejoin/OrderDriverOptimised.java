package com.atguigu.mapreduce.join.reducejoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author stf
 */
public class OrderDriverOptimised {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(new Configuration());

        job.setMapperClass(OrderMapper.class);
        job.setReducerClass(OrderReducerOptimised.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(OrderBean.class);
        job.setOutputKeyClass(OrderBean.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.setInputPaths(job, new Path("D:\\Atguigu\\Mydata\\input/order.txt"), new Path("D:\\Atguigu\\Mydata\\input/pd.txt"));

        FileOutputFormat.setOutputPath(job, new Path("D:\\Atguigu\\Mydata\\output_ReduceJoin_Optimised"));

        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}
