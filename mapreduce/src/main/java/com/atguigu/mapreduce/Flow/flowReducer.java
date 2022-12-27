package com.atguigu.mapreduce.Flow;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class flowReducer extends Reducer<Text, FlowBean, Text, FlowBean> {

    private long sum_up = 0;
    private long sum_down = 0;
    FlowBean fb = new FlowBean();

    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        for (FlowBean value : values) {
            sum_up += value.getUperFlow();
            sum_down = value.getDownerFlow();
        }

        fb.set(sum_up, sum_down);
        context.write(key, fb);
    }
}
