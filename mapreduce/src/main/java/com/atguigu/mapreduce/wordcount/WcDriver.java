package com.atguigu.mapreduce.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Drivers 就是新建Job，并对Job进行一系列优化
 *
 * @author stf
 */
public class WcDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        //新建Job
        Job job = Job.getInstance(new Configuration());

        //如果把它提交到集群,需要设置jar包
        // job.setJarByClass(WcMapper.class);

        // 设置Mapper和Reducer
        job.setMapperClass(WcMapper.class);
        job.setReducerClass(WcReducer.class);

        // 设置输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //设置输入输出路径

        FileInputFormat.setInputPaths(job,new Path("D:/Atguigu/Mydata/input/words.txt"));
        FileOutputFormat.setOutputPath(job,new Path("D:/Atguigu/Mydata/output"));
        // FileInputFormat.setInputPaths(job,new Path(args[0]));
        // FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //提交任务
        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}
