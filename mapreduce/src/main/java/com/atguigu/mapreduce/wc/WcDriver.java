package com.atguigu.mapreduce.wc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Combiner map端进行合并
 * @author stf
 */
public class WcDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        long start = System.currentTimeMillis();

        // job 记得声明配置信息
        Job job = Job.getInstance(new Configuration());

        // job.setCombinerClass("wcDriver.class"
        job.setCombinerClass(WcReducer.class);


        job.setMapperClass(WcMapper.class);
        job.setReducerClass(WcReducer.class);



        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job, new Path("D:/Atguigu/Mydata/input/words.txt"));
        FileOutputFormat.setOutputPath(job, new Path("D:/Atguigu/Mydata/output_wc_Combiner"));

        long end = System.currentTimeMillis();
        boolean b = job.waitForCompletion(true);
        System.out.println("time = " + (end-start));
        System.exit(b ? 0 : 1);
    }
}
