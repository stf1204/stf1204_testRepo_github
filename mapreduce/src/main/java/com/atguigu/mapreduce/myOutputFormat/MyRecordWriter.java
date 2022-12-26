package com.atguigu.mapreduce.myOutputFormat;

import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * RecordWriter具体写操作
 *
 * @author stf
 */
public class MyRecordWriter extends RecordWriter<LongWritable, Text> {

    private FileOutputStream guigu;
    private FileOutputStream others;

    /**
     * 开两个文件输出流，一个写atguigu，一个写其他
     */
    public MyRecordWriter() throws FileNotFoundException {
        guigu = new FileOutputStream("D:/Atguigu/Mydata/output_outputFormat/guigu.log");
        others = new FileOutputStream("D:/Atguigu/Mydata/output_outputFormat/others.log");
    }

    /**
     * 具体流写数据的代码
     * @param key 行号
     * @param value 域名地址
     * @throws IOException
     */
    @Override
    public void write(LongWritable key, Text value) throws IOException{
        //要写入的数据
        String line = value.toString() + "\n";
        //根据一行的数据是否包含atguigu,判断两条输出流输出的内容
        if (line.contains("atguigu")){
            guigu.write(line.getBytes(StandardCharsets.UTF_8));
        } else{
            others.write(line.getBytes(StandardCharsets.UTF_8));
        }
    }

    /**
     * 关流
     * 使用hadoop提供的IOUtils工具包
     */
    @Override
    public void close(TaskAttemptContext context){

        IOUtils.closeStream(guigu);
        IOUtils.closeStream(others);
    }
}
