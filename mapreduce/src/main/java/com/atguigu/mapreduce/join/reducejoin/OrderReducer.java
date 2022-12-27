package com.atguigu.mapreduce.join.reducejoin;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author stf
 */
public class OrderReducer extends Reducer<Text,OrderBean,OrderBean, NullWritable> {

    private List <OrderBean> orders = new ArrayList<>();


    /**
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<OrderBean> values, Context context) throws IOException, InterruptedException {

        // 先找到pname
        String pname = null;
        orders.clear();

        Iterator<OrderBean> iterator = values.iterator();

        while (iterator.hasNext()){

            OrderBean value = iterator.next();

            // pname有值，说明数据来源pd
            if (!"".equals(value.getPname())) {
                pname = value.getPname();
            }

            else {
                //说明数据来源order
                // value只有一个对象，我们要新建对象保存信息
                OrderBean ob = new OrderBean();

                // 将value值拷贝进ob
                ob.setId(value.getId());
                ob.setPid(value.getPid());
                ob.setPname(value.getPname());
                ob.setAmount(value.getAmount());

                orders.add(ob);
            }
        }

        // 输出orders
        for (OrderBean order : orders) {
            order.setPname(pname);
            context.write(order,NullWritable.get());
        }

    }



}
