package com.atguigu.mapreduce.partitioner;

import com.atguigu.mapreduce.Flow.FlowBean;
import com.atguigu.mapreduce.Flow.flowMapper;
import com.atguigu.mapreduce.Flow.flowReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author stf
 */
public class flowDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(new Configuration());

        job.setJarByClass(flowDriver.class);

        job.setMapperClass(flowMapper.class);
        job.setReducerClass(flowReducer.class);


        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        // 将输入格式改成CombineTextInputFormat   默认是FileTextInputFormat
        job.setInputFormatClass(CombineTextInputFormat.class);
        // 设置多大可以切一片，可以跨文件
        CombineTextInputFormat.setMaxInputSplitSize(job,1024*1024);

        // 设置Map的partitioner为自定义MyPartitioner
        job.setPartitionerClass(MyPartition.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        // 如果希望分区，将reduceTask任务设置为多个
        job.setNumReduceTasks(5);

        FileInputFormat.setInputPaths(job, new Path("D:\\Atguigu\\Mydata\\input/phone_data.txt"));
        FileOutputFormat.setOutputPath(job, new Path("D:\\Atguigu\\Mydata\\output"));

        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}
