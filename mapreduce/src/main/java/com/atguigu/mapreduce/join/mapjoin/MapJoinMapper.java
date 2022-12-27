package com.atguigu.mapreduce.join.mapjoin;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author stf
 */
public class MapJoinMapper extends Mapper<LongWritable, Text,Text, NullWritable> {

    private Map<String,String> pmap = new HashMap<>();
    private StringBuilder sb = new StringBuilder();
    private Text result = new Text();

    /**
     * 需要在处理数据之前就把pd.txt加载到分布式缓冲区中
     * 使用HDFS分布式文件缓冲流，转为IO流读取
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        // 获取分布式缓冲的URI
        URI[] cf = context.getCacheFiles();
        // 获取分布式文件对象
        FileSystem fs = FileSystem.get(context.getConfiguration());
        // 用HDFS对象开流
        FSDataInputStream stream = fs.open(new Path(cf[0]));


        // 包装流为BufferedReader流对象，读取一行数据，存进pMap
        // 如果要按行读，需要将字节流包装为字符流
        InputStreamReader isr = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(isr);

        // 现在开始按行处理
        String lines = br.readLine();
        while (lines!=null && lines.length()!=0) {
            // 切割数据，存数据
            String[] files = lines.split("\t");
            pmap.put(files[0], files[1]);
            lines = br.readLine();
        }

        //  处理结束，关流
        IOUtils.closeStream(br);
    }

    /**
     * 内存已经加载pd.txt，map要处理的只有order.txt
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] lines = value.toString().split("\t");
        sb.setLength(0);
        sb.append(lines[0]).append("\t")
                .append(pmap.get(lines[1])).append("\t")
                .append(lines[2]);

        result.set(sb.toString());
        context.write(result,NullWritable.get());
    }
}
