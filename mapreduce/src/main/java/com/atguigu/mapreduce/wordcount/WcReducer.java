package com.atguigu.mapreduce.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 将Mapper输出的(word，1)按照word分组累加
 *
 * @author stf
 */
public class WcReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    private IntWritable result = new IntWritable();
    /**
     * 将所有的单词进行累加
     *
     * @param key     单词
     * @param values  这个单词对应所有的1
     * @param context job本身
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        // 针对同一个单词所有的1 累加
        int sum = 0;
        for (IntWritable value : values) {
            sum += value.get();
        }

        //sum就是输出结果
        result.set(sum);
        context.write(key,result);
    }
}
