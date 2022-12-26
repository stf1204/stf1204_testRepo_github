package com.atguigu.mapreduce.myOutputFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * reduce输出控制
 * @author stf
 */
public class MyOutputDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(new Configuration());

        job.setOutputFormatClass(MyOutputFormat.class);

        FileInputFormat.setInputPaths(job, new Path("D:/Atguigu/Mydata/input/log.txt"));
        FileOutputFormat.setOutputPath(job, new Path("D:/Atguigu/Mydata/output_outputFormat/"));

        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}
