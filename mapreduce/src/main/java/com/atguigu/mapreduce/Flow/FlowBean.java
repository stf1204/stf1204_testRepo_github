package com.atguigu.mapreduce.Flow;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


/**
 * @author stf
 */
public class FlowBean implements Writable {
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
}
