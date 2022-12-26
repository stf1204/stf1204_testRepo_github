package com.atguigu.mapreduce.myOutputFormat;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * FileOutputFormat
 * @author stf
 */
public class MyOutputFormat extends FileOutputFormat<LongWritable, Text> {

    /**
     *
     * @param job
     * @return 调用自己写的RecordWriter，往文件输出
     * @throws IOException
     */
    @Override
    public RecordWriter<LongWritable, Text> getRecordWriter(TaskAttemptContext job) throws IOException{
        return new MyRecordWriter();
    }
}
