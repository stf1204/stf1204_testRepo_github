package com.atguigu.mapreduce.join.reducejoin;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author stf
 */
public class OrderReducerOptimised extends Reducer<Text, OrderBean, OrderBean, NullWritable> {

    private List<OrderBean> orders = new ArrayList<>();


    /**
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<OrderBean> values, Context context) throws IOException, InterruptedException {

        // 先找到pname
        String pname = null;
        // 上一组对象可以不同清零，供下一组继续使用
        // orders.clear()
        //声明一个计数器，统计order.txt有几行
        int n = 0;

        for (OrderBean value : values) {

            // pname有值，说明数据来源pd
            if (!"".equals(value.getPname())) {
                pname = value.getPname();
            } else {
                OrderBean ob;

                //说明数据来源order
                // value只有一个对象，我们要新建对象保存信息
                // 频繁new对象会导致堆内存满，进而导致full gc
                if (n < orders.size()) {
                    // 上一组的对象还没有用完
                    ob = orders.get(n);
                } else {
                    // 上一组申请的对象用完了
                    ob = new OrderBean();
                    orders.add(ob);
                }

                // 将value值拷贝进ob
                ob.setId(value.getId());
                ob.setPid(value.getPid());
                ob.setPname(value.getPname());
                ob.setAmount(value.getAmount());

                // n用来统计当前组有几个数据
                n++;
            }
        }

        // 输出orders
        for (int i = 0; i < n; i++) {
            // 只读取统计的n个行，其余为脏数据
            OrderBean order = orders.get(i);
            order.setPname(pname);
            context.write(order, NullWritable.get());
        }

    }


}
