package com.atguigu.mapreduce.Comparable;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author stf
 */
public class ComparableDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(new Configuration());

        job.setMapperClass(ComparableMapper.class);
        job.setReducerClass(ComparableReducer.class);

        job.setMapOutputKeyClass(FlowBean.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);


        FileInputFormat.setInputPaths(job,new Path("D:/Atguigu/Mydata/output/part-r-00000"));
        FileOutputFormat.setOutputPath(job,new Path("D:/Atguigu/Mydata/output2"));

        boolean b = job.waitForCompletion(true);
        System.out.println(b ? 0 : 1);
    }
}
