package com.atguigu.mapreduce.Comparable;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


/**
 * WritableComparable继承 Writable和Comparable接口
 * 自定义类比较需要继承comparable接口，实现compareTo方法
 * @author stf
 */
public class FlowBean implements WritableComparable<FlowBean> {
    private Long UperFlow;
    private Long DownerFlow;
    private Long SumFlow;

    public FlowBean() {
    }

    public void set(Long uperFlow, Long downerFlow){
        this.UperFlow = uperFlow;
        this.DownerFlow = downerFlow;
        this.SumFlow = uperFlow + downerFlow;
    }

    public Long getUperFlow() {
        return UperFlow;
    }

    public void setUperFlow(Long uperFlow) {
        UperFlow = uperFlow;
    }

    public Long getDownerFlow() {
        return DownerFlow;
    }

    public void setDownerFlow(Long downerFlow) {
        DownerFlow = downerFlow;
    }

    public Long getSumFlow() {
        return SumFlow;
    }

    public void setSumFlow(Long sumFlow) {
        SumFlow = sumFlow;
    }
    @Override
    public String toString() {
        return UperFlow + "\t" + DownerFlow + "\t" + SumFlow;
    }

    /**
     * 序列化
     * @param out
     * @throws IOException
     */
    @Override
    public void write(DataOutput out) throws IOException {

        out.writeLong(UperFlow);
        out.writeLong(DownerFlow);
        out.writeLong(SumFlow);
    }

    /**
     * 反序列化
     * @param in
     * @throws IOException
     */
    @Override
    public void readFields(DataInput in) throws IOException {

        this.UperFlow = in.readLong();
        this.DownerFlow = in.readLong();
        this.SumFlow = in.readLong();
    }

    @Override
    public int compareTo(FlowBean o) {
        // this 比 o 大的时候返回 1，小的时候返回 -1 ，相等返回 0
        return Long.compare(this.SumFlow,o.SumFlow);
    }
}
