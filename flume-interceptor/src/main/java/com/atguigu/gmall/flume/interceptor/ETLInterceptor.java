package com.atguigu.gmall.flume.interceptor;

import com.atguigu.gmall.flume.utils.JSONUtil;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

public class ETLInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        // 1、获取body当中的数据，并转换为字符串
        byte[] body = event.getBody();
        String log = new String(body, StandardCharsets.UTF_8);

        // 2、进行字符串判断,是否是一个合法的json，是：返回当前event；不是：返回null
        if (JSONUtil.isJSONValidate(log)){
            return event;
        } else {
            return null;
        }
    }


    @Override
    public List<Event> intercept(List<Event> list) {

        Iterator<Event> iterator = list.iterator();

        while (iterator.hasNext()){
            Event event = iterator.next();
            if (intercept(event) == null){
                iterator.remove();
            }
        }
        return list;
    }

    @Override
    public void close() {

    }



    /**
     * 内部类，用来实例化对象
     */
    public static class Mybuilder implements Interceptor.Builder{
        @Override
        public Interceptor build() {
            return new ETLInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
