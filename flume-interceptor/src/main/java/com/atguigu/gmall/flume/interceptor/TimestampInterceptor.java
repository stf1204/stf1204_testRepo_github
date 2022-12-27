package com.atguigu.gmall.flume.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @author stf
 */
public class TimestampInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    /**
     * 用光顾时间ts中的时间戳，替换掉采集数据的时间戳
     * @param event
     * @return
     */
    @Override
    public Event intercept(Event event) {
        byte[] body = event.getBody();
        Map<String, String> headers = event.getHeaders();
        String value = new String(body, StandardCharsets.UTF_8);
        JSONObject jsonObject = JSONObject.parseObject(value);
        String ts = jsonObject.getString("ts");
        headers.put("timestamp",ts);

        return event;
    }

    /**
     * 多json处理
     * @param list
     * @return
     */
    @Override
    public List<Event> intercept(List<Event> list) {
        for (Event event : list) {
            intercept(event);
        }
        return list;
    }

    @Override
    public void close() {

    }

    public static class MyBuilder implements Interceptor.Builder {

        @Override
        public Interceptor build() {
            return new TimestampInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
