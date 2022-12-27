package com.atguigu.mapreduce.join.mapjoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author stf
 */
public class MapJoinDriver {
    public static void main1(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(MapJoinMapper.class);

        job.setMapperClass(MapJoinMapper.class);
        job.setNumReduceTasks(0);

        // MapJoin中要缓存的数据表格，以分布式缓存的形式设置到Job中
        job.addCacheFile(URI.create("file:///d:/Atguigu/Mydata/input/pd.txt"));


        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);


        FileInputFormat.setInputPaths(job, new Path("D:\\Atguigu\\Mydata\\input/order.txt"));
        FileOutputFormat.setOutputPath(job, new Path("D:\\Atguigu\\Mydata\\output_MapJoin/"));


        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);

    }
}
