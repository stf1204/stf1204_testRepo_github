package com.atguigu.flume;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.List;
import java.util.Map;

public class MyInterceptor implements Interceptor {
    public void initialize() {
    }


    /**
     * 拦截器
     *  1、    真正处理的业务逻辑————处理单个数据的逻辑
     * @param event 就是我们要处理的数据
     * @return
     */
    public Event intercept(Event event) {

        Map<String, String> headers = event.getHeaders();
        byte[] body = event.getBody();
        byte value = body[0];
        // 数字
        if (value >= '0' && value <= '9') {
            headers.put("state", "number");
        }
        if ((value >= 'a' && value <= 'z') || (value >= 'A' && value <= 'Z')) {
            headers.put("state", "letter");
        }
        return event;
    }


    /**
     * 2、     真正处理的业务逻辑————处理一组数据
     * @param list
     * @return
     */
    public List<Event> intercept(List<Event> list) {
        for (Event event : list) {
            intercept(event);
        }
        return list;
    }


    /**
     * 释放资源
     */
    public void close() {

    }




    /**
     * 3、 内部类，用来提供类对象
     */
    public static class MyBuilder implements Builder {

        public Interceptor build() {
            return new MyInterceptor();
        }


        public void configure(Context context) {
        }
    }
}
