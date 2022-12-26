package com.atguigu.mapreduce.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 将数据按行输入，按照空格分隔开，以(word,1)的形式输出
 * @author stf
 */
public class WcMapper extends Mapper<LongWritable, Text,Text, IntWritable> {

    private static final  IntWritable one = new IntWritable(1);
    private Text word = new Text();

    /**
     * 按空格切分输入的一行数据，并把切分的单词以（word，1）的形式输出给框架
     * @param key  行号
     * @param value 行内容
     * @param context job本身
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //获取正在处理的本行数据
        String line = value.toString();

        //将这一行数据按照空格切分
        String[] words = line.split(" ");

        // 遍历所有的单词，输出（word，1）
        for (String word : words) {

            //将遍历完的单词重新打包，交给Job
            // context.write(new Text(word),new IntWritable(1))
            this.word.set(word);
            context.write(this.word,one);
        }
    }
}
