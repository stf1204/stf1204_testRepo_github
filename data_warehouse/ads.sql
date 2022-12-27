-- 流量主题
-- 各渠道流量统计
-- 1）建表语句
DROP TABLE IF EXISTS ads_traffic_stats_by_channel;
CREATE EXTERNAL TABLE ads_traffic_stats_by_channel
(
    `dt`               STRING COMMENT '统计日期',
    `recent_days`      BIGINT COMMENT '最近天数,1:最近1天,7:最近7天,30:最近30天',
    `channel`          STRING COMMENT '渠道',
    `uv_count`         BIGINT COMMENT '访客人数',
    `avg_duration_sec` BIGINT COMMENT '会话平均停留时长，单位为秒',
    `avg_page_count`   BIGINT COMMENT '会话平均浏览页面数',
    `sv_count`         BIGINT COMMENT '会话数',
    `bounce_rate`      DECIMAL(16, 2) COMMENT '跳出率'
) COMMENT '各渠道流量统计'
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
    LOCATION '/warehouse/gmall/ads/ads_traffic_stats_by_channel/';


-- 加载数据
-- 方案一、
select max(dt),
       1,
       channel,
       count(distinct mid_id),
       avg(during_time_1d),
       avg(page_count_1d),
       count(*),
       sum(`if`(page_count_1d = 1, 1, 0)) / count(*)
from dws_traffic_session_page_view_1d
where dt = '2020-06-14'
group by channel
union all
select max(dt),
       7,
       channel,
       count(distinct mid_id),
       avg(during_time_1d),
       avg(page_count_1d),
       count(*),
       sum(`if`(page_count_1d = 1, 1, 0)) / count(*)
from dws_traffic_session_page_view_1d
where dt >= date_sub('2020-06-14', 6)
group by channel
union all
select max(dt),
       30,
       channel,
       count(distinct mid_id),
       avg(during_time_1d),
       avg(page_count_1d),
       count(*),
       sum(`if`(page_count_1d = 1, 1, 0)) / count(*)
from dws_traffic_session_page_view_1d
where dt >= date_sub('2020-06-14', 29)
group by channel;

-- 方案二、
select '2020-06-14',
       recent_days,
       channel,
       count(distinct mid_id),
       avg(during_time_1d),
       avg(page_count_1d),
       count(*),
       sum(`if`(page_count_1d = 1, 1, 0)) / count(*)
from (
         select channel,
                1 recent_days,
                mid_id,
                during_time_1d,
                page_count_1d
         from dws_traffic_session_page_view_1d
         where dt = '2020-06-14'
         union all
         select channel,
                7 recent_days,
                mid_id,
                during_time_1d,
                page_count_1d
         from dws_traffic_session_page_view_1d
         where dt >= date_sub('2020-06-14', 6)
         union all
         select channel,
                30 recent_days,
                mid_id,
                during_time_1d,
                page_count_1d
         from dws_traffic_session_page_view_1d
         where dt >= date_sub('2020-06-14', 29)
     ) t1
group by channel, recent_days;


-- 方案三、
select '2020-06-14',
       recent_days,
       channel,
       count(distinct mid_id),
       avg(during_time_1d),
       avg(page_count_1d),
       count(*),
       sum(`if`(page_count_1d = 1, 1, 0)) / count(*)
from dws_traffic_session_page_view_1d lateral view explode(`array`(1, 7, 30)) tmp as recent_days
where dt >= date_sub('2020-06-14', recent_days - 1)
group by recent_days, channel;


-- 装载
insert overwrite table ads_traffic_stats_by_channel
select *
from ads_traffic_stats_by_channel
union
select '2020-06-14'                                                        dt,
       recent_days,
       channel,
       cast(count(distinct (mid_id)) as bigint)                            uv_count,
       cast(avg(during_time_1d) / 1000 as bigint)                          avg_duration_sec,
       cast(avg(page_count_1d) as bigint)                                  avg_page_count,
       cast(count(*) as bigint)                                            sv_count,
       cast(sum(if(page_count_1d = 1, 1, 0)) / count(*) as decimal(16, 2)) bounce_rate
from dws_traffic_session_page_view_1d lateral view explode(array(1, 7, 30)) tmp as recent_days
where dt >= date_add('2020-06-14', -recent_days + 1)
group by recent_days, channel;


-- 简化版
DROP TABLE IF EXISTS ads_traffic_stats_by_channel;
CREATE EXTERNAL TABLE ads_traffic_stats_by_channel
(
    `dt`               STRING COMMENT '统计日期',
    `uv_count`         BIGINT COMMENT '访客人数',
    `avg_duration_sec` BIGINT COMMENT '会话平均停留时长，单位为秒',
    `avg_page_count`   BIGINT COMMENT '会话平均浏览页面数',
    `sv_count`         BIGINT COMMENT '会话数',
    `bounce_rate`      DECIMAL(16, 2) COMMENT '跳出率'
) COMMENT '各渠道流量统计'
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
    LOCATION '/warehouse/gmall/ads/ads_traffic_stats_by_channel/';

-- 1d
select max(dt),
       min(dt),
       '2020-06-14' dt,
       count(distinct mid_id),
       avg(during_time_1d),
       avg(page_count_1d),
       count(*),
       sum(`if`(page_count_1d = 1, 1, 0)) / count(*),
       count(`if`(page_count_1d = 1, 1, null)) / count(*)
from dws_traffic_session_page_view_1d
where dt = '2020-06-14';



-- 2、 路径分析
-- 1）建表语句
DROP TABLE IF EXISTS ads_page_path;
CREATE EXTERNAL TABLE ads_page_path
(
    `dt`         STRING COMMENT '统计日期',
    `source`     STRING COMMENT '跳转起始页面ID',
    `target`     STRING COMMENT '跳转终到页面ID',
    `path_count` BIGINT COMMENT '跳转次数'
) COMMENT '页面浏览路径分析'
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
    LOCATION '/warehouse/gmall/ads/ads_page_path/';

-- 数据装载
--1、source不能为空
--2、路径不能存在环
select last_page_id source,
       page_id      target,
       count(1)
from dwd_traffic_page_view_inc
where dt = '2020-06-14'
group by last_page_id, page_id;


-- 方法一、
select source,
       target,
       count(*)
from (
         select concat('step-', rk, ':', page_id)                                                  source,
                lead(concat('step-', rk, ':', page_id)) over (partition by session_id order by rk) target

         from (select session_id,
                      page_id,
                      row_number() over (partition by session_id order by view_time) rk
               from dwd_traffic_page_view_inc
               where dt = '2020-06-14'
              ) t1
     ) t2
group by source, target
;

---  方法二、
select source,
       target,
       count(*)
from (
         select concat('step-', rk, ':', page_id) source,
                concat('step-', rk + 1, ':', tg)  target

         from (select session_id,
                      page_id,
                      lead(page_id, 1, null) over (partition by session_id order by view_time) tg,
                      row_number() over (partition by session_id order by view_time)           rk
               from dwd_traffic_page_view_inc
               where dt = '2020-06-14'
              ) t1
     ) t2
group by source, target
;


-- 用户变动统计
-- 1）建表语句
DROP TABLE IF EXISTS ads_user_change;
CREATE EXTERNAL TABLE ads_user_change
(
    `dt`               STRING COMMENT '统计日期',
    `user_churn_count` BIGINT COMMENT '流失用户数',
    `user_back_count`  BIGINT COMMENT '回流用户数'
) COMMENT '用户变动统计'
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
    LOCATION '/warehouse/gmall/ads/ads_user_change/';



--user_back_count--先找出前一日时还是流失的用户，再找这些用户中找当日活跃过的用户。
select user_churn_count,
       user_back_count
from (
         -- 1.1、流失用户数（7日前活跃）
         select count(*) user_churn_count
         from dws_user_user_login_td
         where login_date_last = date_sub('2020-06-14', 7)
           and dt = '2020-06-14'
     ) churn
         join

     (
         -- 1.2、回流用户数（7日未活跃，14号活跃）
         select count(*) user_back_count
         from (
                  select user_id
                  from dws_user_user_login_td
                  where login_date_last <= date_sub('2020-06-14', 8)
                    and dt = date_sub('2020-06-14', 1)
              ) a
                  join
              (
                  select user_id,
                         login_date_last
                  from dws_user_user_login_td
                  where dt = '2020-06-14'
                    and login_date_last = '2020-06-14'
              ) b
              on a.user_id = b.user_id
     ) back;


-- 2、
-- user_back_count-- 先找出当日的活跃用户，再从这些用户中找出前一天还是流失的用户。

select user_churn_count,
       user_back_count
from (
         -- 1.1、流失用户数（7日前活跃）
         select count(*) user_churn_count
         from dws_user_user_login_td
         where dt = '2020-06-14'
           and login_date_last = date_sub('2020-06-14', 7)
     ) churn
         join
     (
         -- 1.2、回流用户数（7日未活跃，14号活跃）
         select count(*) user_back_count
         from (
                  select user_id,
                         login_date_last
                  from dws_user_user_login_td
                  where dt = date_sub('2020-06-14', 1)
              ) a
                  join
              (
                  select user_id,
                         login_date_last
                  from dws_user_user_login_td
                  where dt = '2020-06-14'
                    and login_date_last = '2020-06-14'
              ) b
         where datediff(b.login_date_last, a.login_date_last) >= 8
     ) back;



-- 用户留存率

-- 1）建表语句
DROP TABLE IF EXISTS ads_user_retention;
CREATE EXTERNAL TABLE ads_user_retention
(
    `dt`              STRING COMMENT '统计日期',
    `create_date`     STRING COMMENT '用户新增日期',
    `retention_day`   INT COMMENT '截至当前日期留存天数',
    `retention_count` BIGINT COMMENT '留存用户数量',
    `new_user_count`  BIGINT COMMENT '新增用户数量',
    `retention_rate`  DECIMAL(16, 2) COMMENT '留存率'
) COMMENT '用户留存率'
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
    LOCATION '/warehouse/gmall/ads/ads_user_retention/';


-- 2）数据装载
--方案一、
select '2020-06-14',
       '2020-06-13',
       1,
       count(*),
       sum(`if`(login_date_last = '2020-06-14', 1, 0)),
       sum(`if`(login_date_last = '2020-06-14', 1, 0)) / count(*)
from (
         select user_id
         from dwd_user_register_inc
         where dt = '2020-06-13'
     ) t1
         join
     (
         select user_id,
                login_date_last
         from dws_user_user_login_td
         where dt = '2020-06-14'
     ) t2
     on t1.user_id = t2.user_id
union all
select '2020-06-14',
       '2020-06-10',
       4,
       count(*),
       sum(`if`(login_date_last = '2020-06-14', 1, 0)),
       sum(`if`(login_date_last = '2020-06-14', 1, 0)) / count(*)
from (
         select user_id
         from dwd_user_register_inc
         where dt = '2020-06-10'
     ) t1
         join
     (
         select user_id,
                login_date_last
         from dws_user_user_login_td
         where dt = '2020-06-14'
     ) t2
     on t1.user_id = t2.user_id
union all
select '2020-06-14',
       '2020-06-11',
       3,
       count(*),
       sum(`if`(login_date_last = '2020-06-14', 1, 0)),
       sum(`if`(login_date_last = '2020-06-14', 1, 0)) / count(*)
from (
         select user_id
         from dwd_user_register_inc
         where dt = '2020-06-11'
     ) t1
         join
     (
         select user_id,
                login_date_last
         from dws_user_user_login_td
         where dt = '2020-06-14'
     ) t2
     on t1.user_id = t2.user_id;


-- 方案二、
select '2020-06-14'                                               `dt`,
       date_id                                                    `create_date`,
       datediff('2020-06-14', date_id)                            `retention_day`,
       count(*)                                                   `new_user_count`,
       sum(`if`(login_date_last = '2020-06-14', 1, 0))            `retention_count`,
       sum(`if`(login_date_last = '2020-06-14', 1, 0)) / count(*) `retention_rate`
from (
         select user_id,
                date_id
         from dwd_user_register_inc
         where dt >= date_sub('2020-06-14', 7)
           and dt < '2020-06-14'
     ) t1
         join
     (
         select user_id,
                login_date_last
         from dws_user_user_login_td
         where dt = '2020-06-14'
     ) t2
     on t1.user_id = t2.user_id
group by date_id;



-- 用户新增活跃统计
-- 1）建表语句
DROP TABLE IF EXISTS ads_user_stats;
CREATE EXTERNAL TABLE ads_user_stats
(
    `dt`                STRING COMMENT '统计日期',
    `recent_days`       BIGINT COMMENT '最近n日,1:最近1日,7:最近7日,30:最近30日',
    `new_user_count`    BIGINT COMMENT '新增用户数',
    `active_user_count` BIGINT COMMENT '活跃用户数'
) COMMENT '用户新增活跃统计'
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
    LOCATION '/warehouse/gmall/ads/ads_user_stats/';


-- 数据装载
-- 方法一
select '2020-06-14' dt,
       1,
       new_user_count,
       active_user_count
from (
         select count(*) new_user_count
         from dwd_user_register_inc
         where dt = '2020-06-14'
           and date_id = '2020-06-14') a
         join
     (
         select count(distinct user_id) active_user_count
         from dws_user_user_login_td
         where dt = '2020-06-14'
           and login_date_last = '2020-06-14') b
union
select '2020-06-14' dt,
       7,
       new_user_count,
       active_user_count
from (
         select count(*) new_user_count
         from dwd_user_register_inc
         where dt >= date_sub('2020-06-14', 6)
           and date_id >= date_sub('2020-06-14', 6)) a
         join
     (
         select count(distinct user_id) active_user_count
         from dws_user_user_login_td
         where dt >= date_sub('2020-06-14', 6)
           and login_date_last >= date_sub('2020-06-14', 6)) b;

-- 方法二
select '2020-06-14' dt,
       log.recent_days,
       log.cnt,
       reg.cnt
from (
         select recent_days,
                count(*) cnt
         from dwd_user_register_inc lateral view explode(`array`(1, 7, 30)) tmp as recent_days
         where dt >= date_sub('2020-06-14', 29)
           and dt >= date_sub('2020-06-14', recent_days - 1)
         group by recent_days
     ) reg
         join
     (
         select recent_days,
                count(*) cnt
         from dws_user_user_login_td lateral view explode(`array`(1, 7, 30)) tmp as recent_days
         where dt = '2020-06-14'
           and login_date_last >= date_sub('2020-06-14', 29)
           and login_date_last >= date_sub('2020-06-14', recent_days - 1)
         group by recent_days
     ) log on reg.recent_days = log.recent_days;

-- 用户行为漏斗分析
-- 1）建表语句
DROP TABLE IF EXISTS ads_user_action;
CREATE EXTERNAL TABLE ads_user_action
(
    `dt`                STRING COMMENT '统计日期',
    `home_count`        BIGINT COMMENT '浏览首页人数',
    `good_detail_count` BIGINT COMMENT '浏览商品详情页人数',
    `cart_count`        BIGINT COMMENT '加入购物车人数',
    `order_count`       BIGINT COMMENT '下单人数',
    `payment_count`     BIGINT COMMENT '支付人数'
) COMMENT '漏斗分析'
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
    LOCATION '/warehouse/gmall/ads/ads_user_action/';
--v1.0
-- home_count
select count(*)
from dws_traffic_page_visitor_page_view_1d
where dt = '2020-06-14'
  and page_id = 'home';
--good_detail_count
select count(*)
from dws_traffic_page_visitor_page_view_1d
where dt = '2020-06-14'
  and page_id = 'good_detail';

--v2.0
select count(*)
from dws_traffic_page_visitor_page_view_1d
where page_id in ('home', 'good_detail')
group by page_id;

--v3.0
select sum(`if`(page_id = 'home', 1, 0)),
       sum(`if`(page_id = 'good_detail', 1, 0))
from dws_traffic_page_visitor_page_view_1d
where page_id in ('home', 'good_detail');

--cart_count
select count(*)
from dws_trade_user_cart_add_1d
where dt = '2020-06-14';
--order_count
select count(*)
from dws_trade_user_order_1d
where dt = '2020-06-14';
--payment_count
select count(*)
from dws_trade_user_payment_1d
where dt = '2020-06-14';



-- 新增下单用户统计
-- 1）建表语句
DROP TABLE IF EXISTS ads_new_order_user_stats;
CREATE EXTERNAL TABLE ads_new_order_user_stats
(
    `dt`                   STRING COMMENT '统计日期',
    `recent_days`          BIGINT COMMENT '最近天数,1:最近1天,7:最近7天,30:最近30天',
    `new_order_user_count` BIGINT COMMENT '新增下单人数'
) COMMENT '新增交易用户统计'
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
    LOCATION '/warehouse/gmall/ads/ads_new_order_user_stats/';


--1
select count(*)
from dws_trade_user_order_td
where dt = '2020-06-14'
  and order_date_first = '2020-06-14';
--7
select count(*)
from dws_trade_user_order_td
where dt = '2020-06-14'
  and order_date_first >= date_sub('2020-06-14', 6);
--30
select count(*)
from dws_trade_user_order_td
where dt = '2020-06-14'
  and order_date_first >= date_sub('2020-06-14', 29);


select recent_days,
       count(*)
from dws_trade_user_order_td lateral view explode(`array`(1, 7, 30)) tmp as recent_days
where dt = '2020-06-14'
  and order_date_first >= date_sub('2020-06-14', 29)
  and order_date_first >= date_sub('2020-06-14', recent_days - 1)
group by recent_days;



-- 最近7日内连续3日下单用户数
-- 1）建表语句
DROP TABLE IF EXISTS ads_order_continuously_user_count;
CREATE EXTERNAL TABLE ads_order_continuously_user_count
(
    `dt`                            STRING COMMENT '统计日期',
    `recent_days`                   BIGINT COMMENT '最近天数,7:最近7天',
    `order_continuously_user_count` BIGINT COMMENT '连续3日下单用户数'
) COMMENT '新增交易用户统计'
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
    LOCATION '/warehouse/gmall/ads/ads_order_continuously_user_count/';


select count(distinct user_id)
from (
         select user_id,
                date_id,
                lead(date_id, 1, '9999-12-31') over (partition by user_id order by date_id) lead_1,
                lead(date_id, 2, '9999-12-31') over (partition by user_id order by date_id) lead_2
         from (
                  select distinct user_id,
                                  date_id
                  from dwd_trade_order_detail_inc
                  where dt >= date_sub('2020-06-14', 6)
              ) t1
     ) t2
where datediff(lead_1, date_id) = datediff(lead_2, lead_1);


-- 优化
select count(distinct user_id)
from (
         select user_id,
                dt,
                lead(dt, 2, '9999-12-31') over (partition by user_id order by dt) lead_2
         from (
                  select user_id, dt
                  from dws_trade_user_order_1d
                  where dt >= date_sub('2020-06-14', 6)
              ) t1
     ) t2
where datediff(lead_2, dt) = 2;


-- 方案二、
select count(distinct user_id)
from (
         select user_id, count(sub_date) cnt
         from (
                  select user_id,
                         date_sub(dt, rk) sub_date
                  from (
                           select user_id,
                                  dt,
                                  rank() over (partition by user_id order by dt) rk
                           from (
                                    select user_id, dt
                                    from dws_trade_user_order_1d
                                    where dt >= date_sub('2020-06-14', 6)
                                ) t1
                       ) t2
              ) t3
         group by user_id, sub_date
     ) t4
where cnt >= 3;


-- 方案三、
select count(user_id)
from (
         select user_id,
                sum(num) nu
         from (
                  select user_id,
                         dt,
                         case dt
                             when '2020-06-14' then 1
                             when '2020-06-13' then 10
                             when '2020-06-12' then 100
                             when '2020-06-11' then 1000
                             when '2020-06-10' then 10000
                             when '2020-06-9' then 100000
                             when '2020-06-8' then 1000000
                             end num
                  from (
                           select user_id, dt
                           from dws_trade_user_order_1d
                           where dt >= date_sub('2020-06-14', 6)
                       ) t1
              ) t2
         group by user_id
     ) t3
where nu like '%111%';


-- 断一天也算连续
select count(distinct user_id)
from (
         select user_id,
                dt,
                lead(dt, 2, '9999-12-31') over (partition by user_id order by dt) lead_2
         from (
                  select user_id, dt
                  from dws_trade_user_order_1d
                  where dt >= date_sub('2020-06-14', 6)
              ) t1
     ) t2
where datediff(lead_2, dt) <= 3;



-- 最近30日各品牌复购率
-- 统计周期	统计粒度	指标	说明
-- 最近30日	品牌	复购率	重复购买人数占购买人数比例
-- 1）建表语句
DROP TABLE IF EXISTS ads_repeat_purchase_by_tm;
CREATE EXTERNAL TABLE ads_repeat_purchase_by_tm
(
    `dt`                STRING COMMENT '统计日期',
    `recent_days`       BIGINT COMMENT '最近天数,30:最近30天',
    `tm_id`             STRING COMMENT '品牌ID',
    `tm_name`           STRING COMMENT '品牌名称',
    `order_repeat_rate` DECIMAL(16, 2) COMMENT '复购率'
) COMMENT '各品牌复购率统计'
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
    LOCATION '/warehouse/gmall/ads/ads_repeat_purchase_by_tm/';

-- 数据装载
select '2020-06-14' dt,
       30,
       tm_id,
       tm_name,
       cast(sum(`if`(order_count >= 2, 1, 0)) / sum(`if`(order_count = 1, 1, 0)) as decimal(16, 2))
from (
         select tm_id,
                tm_name,
                sum(order_count_30d) order_count
         from dws_trade_user_tm_order_nd
         where dt = '2020-06-14'
         group by user_id, tm_id, tm_name
     ) t1
group by tm_id, tm_name;



-- 各品牌商品下单统计
-- 统计周期	  统计粒度 指标
-- 最近1、7、30日	品牌	订单数
-- 最近1、7、30日	品牌	订单人数
-- 1）建表语句
DROP TABLE IF EXISTS ads_order_stats_by_tm;
CREATE EXTERNAL TABLE ads_order_stats_by_tm
(
    `dt`               STRING COMMENT '统计日期',
    `recent_days`      BIGINT COMMENT '最近天数,1:最近1天,7:最近7天,30:最近30天',
    `tm_id`            STRING COMMENT '品牌ID',
    `tm_name`          STRING COMMENT '品牌名称',
    `order_count`      BIGINT COMMENT '订单数',
    `order_user_count` BIGINT COMMENT '订单人数'
) COMMENT '各品牌商品交易统计'
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
    LOCATION '/warehouse/gmall/ads/ads_order_stats_by_tm/';

--装载语句

select '2020-06-14'            dt,
       1                       recent_days,
       tm_id,
       tm_name,
       sum(order_count_1d)     order_count,
       count(distinct user_id) order_user_count
from dws_trade_user_sku_order_1d
where dt = '2020-06-14'
group by tm_id, tm_name
union all
select '2020-06-14'                                            dt,
       7                                                       recent_days,
       tm_id,
       tm_name,
       sum(order_count_7d)                                     order_count,
       count(distinct `if`(order_count_7d > 0, user_id, null)) order_user_count
from dws_trade_user_tm_order_nd
where dt = '2020-06-14'
group by tm_name, tm_id
union all
select '2020-06-14'            dt,
       30                      recent_days,
       tm_id,
       tm_name,
       sum(order_count_30d)    order_count,
       count(distinct user_id) order_user_count
from dws_trade_user_tm_order_nd
where dt = '2020-06-14'
group by tm_name, tm_id
;

-- 方案二、
select '2020-06-14'            dt,
       1                       recent_days,
       tm_id,
       tm_name,
       sum(order_count_1d)     order_count,
       count(distinct user_id) order_user_count
from dws_trade_user_sku_order_1d
where dt = '2020-06-14'
group by tm_id, tm_name
union all
select '2020-06-14'                                         dt,
       recent_days,
       tm_id,
       tm_name,
       sum(order_count)                                     order_count,
       count(distinct `if`(order_count > 0, user_id, null)) order_user_count
from (
         select 7              recent_days,
                tm_id,
                tm_name,
                order_count_7d order_count,
                user_id
         from dws_trade_user_tm_order_nd
         where dt = '2020-06-14'
         union all
         select 30 recent_days,
                tm_id,
                tm_name,
                order_count_30d,
                user_id
         from dws_trade_user_tm_order_nd
         where dt = '2020-06-14'
     ) t1
group by recent_days, tm_name, tm_id;


--方案三、
select '2020-06-14'            dt,
       1                       recent_days,
       tm_id,
       tm_name,
       sum(order_count_1d)     order_count,
       count(distinct user_id) order_user_count
from dws_trade_user_sku_order_1d
where dt = '2020-06-14'
group by tm_id, tm_name
union all
select '2020-06-14'                                         dt,
       recent_days,
       tm_id,
       tm_name,
       sum(order_count)                                     order_count,
       count(distinct `if`(order_count > 0, user_id, null)) order_user_count
from (
         select recent_days,
                user_id,
                tm_id,
                tm_name,
                case recent_days
                    when '7' then sum(order_count_7d)
                    when '30' then sum(order_count_30d)
                    end order_count
         from dws_trade_user_tm_order_nd lateral view explode(`array`(7, 30)) tmp as recent_days
         where dt = '2020-06-14'
         group by recent_days, user_id, tm_id, tm_name
     ) t1
group by recent_days, '2020-06-14', tm_id, tm_name;


-- 各品类商品下单统计
-- 需求说明如下
-- 统计周期	统计粒度	指标	说明
-- 最近1、7、30日	品类	订单数	略
-- 最近1、7、30日	品类	订单人数	略
-- 1）建表语句
DROP TABLE IF EXISTS ads_order_stats_by_cate;
CREATE EXTERNAL TABLE ads_order_stats_by_cate
(
    `dt`                      STRING COMMENT '统计日期',
    `recent_days`             BIGINT COMMENT '最近天数,1:最近1天,7:最近7天,30:最近30天',
    `category1_id`            STRING COMMENT '一级分类id',
    `category1_name`          STRING COMMENT '一级分类名称',
    `category2_id`            STRING COMMENT '二级分类id',
    `category2_name`          STRING COMMENT '二级分类名称',
    `category3_id`            STRING COMMENT '三级分类id',
    `category3_name`          STRING COMMENT '三级分类名称',
    `order_count`             BIGINT COMMENT '订单数',
    `order_user_count`        BIGINT COMMENT '订单人数'
) COMMENT '各分类商品交易统计'
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
    LOCATION '/warehouse/gmall/ads/ads_order_stats_by_cate/';


-- 各分类商品购物车存量Top3
-- 1）建表语句
DROP TABLE IF EXISTS ads_sku_cart_num_top3_by_cate;
CREATE EXTERNAL TABLE ads_sku_cart_num_top3_by_cate
(
    `dt`             STRING COMMENT '统计日期',
    `category1_id`   STRING COMMENT '一级分类ID',
    `category1_name` STRING COMMENT '一级分类名称',
    `category2_id`   STRING COMMENT '二级分类ID',
    `category2_name` STRING COMMENT '二级分类名称',
    `category3_id`   STRING COMMENT '三级分类ID',
    `category3_name` STRING COMMENT '三级分类名称',
    `sku_id`         STRING COMMENT '商品id',
    `sku_name`       STRING COMMENT '商品名称',
    `cart_num`       BIGINT COMMENT '购物车中商品数量',
    `rk`             BIGINT COMMENT '排名'
) COMMENT '各分类商品购物车存量Top10'
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
    LOCATION '/warehouse/gmall/ads/ads_sku_cart_num_top3_by_cate/';

select '2020-06-14' dt,
       sku_id,
       sku_name,
       category1_id,
       category2_id,
       category3_id,
       category1_name,
       category2_name,
       category3_name,
       cart_num,
       rk
from (
         select sku_id,
                sku_name,
                category1_id,
                category2_id,
                category3_id,
                category1_name,
                category2_name,
                category3_name,
                cart_num,
                row_number() over (partition by category1_id,category2_id,category3_id order by cart_num desc ) rk
         from (
                  select sku_id,
                         sum(sku_num) cart_num
                  from dwd_trade_cart_full
                  group by sku_id
              ) t1
                  left join (
             select id,
                    sku_name,
                    category1_id,
                    category2_id,
                    category3_id,
                    category1_name,
                    category2_name,
                    category3_name
             from dim_sku_full
         ) t2 on t1.sku_id = t2.id
     ) t3
where rk <= 3;


-- 各品牌商品收藏次数Top3
-- 1）建表语句
DROP TABLE IF EXISTS ads_sku_favor_count_top3_by_tm;
CREATE EXTERNAL TABLE ads_sku_favor_count_top3_by_tm
(
    `dt`          STRING COMMENT '统计日期',
    `tm_id`       STRING COMMENT '品牌id',
    `tm_name`     STRING COMMENT '品牌名称',
    `sku_id`      STRING COMMENT '商品id',
    `sku_name`    STRING COMMENT '商品名称',
    `favor_count` BIGINT COMMENT '被收藏次数',
    `rk`          BIGINT COMMENT '排名'
) COMMENT '各品牌商品收藏次数Top3'
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
    LOCATION '/warehouse/gmall/ads/ads_sku_favor_count_top3_by_tm/';


-- 数据装载
select '2020-06-14' dt,
       tm_id,
       tm_name,
       sku_id,
       sku_name,
       favor_add_count_1d,
       rk
from (
         select tm_id,
                tm_name,
                sku_id,
                sku_name,
                favor_add_count_1d,
                row_number() over (partition by tm_id order by favor_add_count_1d desc) rk
         from dws_interaction_sku_favor_add_1d
         where dt = '2020-06-14'
     ) t1
where rk <= 3;


-- 下单到支付时间间隔平均值
-- 建表语句
DROP TABLE IF EXISTS ads_order_to_pay_interval_avg;
CREATE EXTERNAL TABLE ads_order_to_pay_interval_avg
(
    `dt`                        STRING COMMENT '统计日期',
    `order_to_pay_interval_avg` BIGINT COMMENT '下单到支付时间间隔平均值,单位为秒'
) COMMENT '下单到支付时间间隔平均值'
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
    LOCATION '/warehouse/gmall/ads/ads_order_to_pay_interval_avg/';



select '2020-06-14' dt,
       avg(unix_timestamp(payment_time) - unix_timestamp(order_time))
from dwd_trade_trade_flow_acc
where dt in ('9999-12-31', '2020-06-14')
  and payment_date_id = '2020-06-14';



--  各省份交易统计
-- 需求说明如下
-- 统计周期	统计粒度	指标
-- 最近1、7、30日	省份	订单数
-- 最近1、7、30日	省份	订单金额
-- 1）建表语句
DROP TABLE IF EXISTS ads_order_by_province;
CREATE EXTERNAL TABLE ads_order_by_province
(
    `dt`                 STRING COMMENT '统计日期',
    `recent_days`        BIGINT COMMENT '最近天数,1:最近1天,7:最近7天,30:最近30天',
    `province_id`        STRING COMMENT '省份ID',
    `province_name`      STRING COMMENT '省份名称',
    `area_code`          STRING COMMENT '地区编码',
    `iso_code`           STRING COMMENT '国际标准地区编码',
    `iso_code_3166_2`    STRING COMMENT '国际标准地区编码',
    `order_count`        BIGINT COMMENT '订单数',
    `order_total_amount` DECIMAL(16, 2) COMMENT '订单金额'
) COMMENT '各地区订单统计'
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
    LOCATION '/warehouse/gmall/ads/ads_order_by_province/';

select 1 recent_days,
       province_id,
       province_name,
       area_code,
       iso_code,
       iso_3166_2,
       order_count_1d,
       order_total_amount_1d
from dws_trade_province_order_1d
where dt = '2020-06-14'
union all
select 7 recent_days,
       province_id,
       province_name,
       area_code,
       iso_code,
       iso_3166_2,
       order_count_7d,
       order_total_amount_7d
from dws_trade_province_order_nd
where dt >= date_sub('2020-06-14', 6)
union all
select 30 recent_days,
       province_id,
       province_name,
       area_code,
       iso_code,
       iso_3166_2,
       order_count_30d,
       order_total_amount_30d
from dws_trade_province_order_nd
where dt >= date_sub('2020-06-14', 29);


--优化
select 1                     recent_days,
       province_id,
       province_name,
       area_code,
       iso_code,
       iso_3166_2,
       order_count_1d        order_count,
       order_total_amount_1d order_total_amount
from dws_trade_province_order_1d
where dt = '2020-06-14'
union all
select recent_days,
       province_id,
       province_name,
       area_code,
       iso_code,
       iso_3166_2,
       case recent_days
           when 7 then order_count_7d
           when 30 then order_count_30d
           end order_count_nd,
       case recent_days
           when 7 then order_total_amount_7d
           when 30 then order_total_amount_30d
           end order_total_amount_nd
from dws_trade_province_order_nd lateral view explode(`array`(7, 30)) tmp as recent_days
where dt = '2020-06-14';



-- 优惠券使用统计
-- 统计周期	统计粒度	指标	说明
-- 最近1日	优惠券	使用次数	支付才算使用
-- 最近1日	优惠券	使用人数	支付才算使用

-- 1）建表语句
DROP TABLE IF EXISTS ads_coupon_stats;
CREATE EXTERNAL TABLE ads_coupon_stats
(
    `dt`              STRING COMMENT '统计日期',
    `coupon_id`       STRING COMMENT '优惠券ID',
    `coupon_name`     STRING COMMENT '优惠券名称',
    `used_count`      BIGINT COMMENT '使用次数',
    `used_user_count` BIGINT COMMENT '使用人数'
) COMMENT '优惠券统计'
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
    LOCATION '/warehouse/gmall/ads/ads_coupon_stats/';


-- 数据装载
select dt,
       coupon_id,
       coupon_name,
       sum(used_count_1d),
       count(user_id)
from dws_tool_user_coupon_coupon_used_1d
where dt='2020-06-14'
group by dt, coupon_id, coupon_name;



