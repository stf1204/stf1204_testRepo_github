/*
-- 建表语句
DROP TABLE IF EXISTS dws_trade_user_tm_order_1d;
CREATE EXTERNAL TABLE dws_trade_user_tm_order_1d
(
    `user_id`            STRING COMMENT '用户id',
    `tm_id`              STRING COMMENT '品牌id',
    `tm_name`            STRING COMMENT '品牌名称',
    `order_count`        bigint comment '订单个数',
    `order_tm_num`       bigint comment '订单商品件数',
    `order_total_amount` bigint comment '订单金额'

) COMMENT '交易域品牌订单粒度最近1日汇总事实表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dws/dws_trade_user_tm_order_1d'
    TBLPROPERTIES ('orc.compress' = 'snappy');

-- 数据装载语句
-- 最近1日	品类	订单数
-- 最近1日	品类	订单人数
insert overwrite table dws_trade_user_tm_order_1d partition (dt = '2020-06-14')
select user_id,
       tm_id,
       tm_name,
       sum(sku_num),
       count(*),
       sum(split_total_amount)
from (select sku_id,
             sku_num,
             user_id,
             split_total_amount
      from dwd_trade_order_detail_inc
      where dt = '2020-06-14') oi
         left join
     (
         select id,
                tm_id,
                tm_name
         from dim_sku_full
         where dt = '2020-06-14'
     ) sku on oi.sku_id = sku.id
group by user_id, tm_id, tm_name;


-- 建表语句
DROP TABLE IF EXISTS dws_trade_user_tm_order_nd;
CREATE EXTERNAL TABLE dws_trade_user_tm_order_nd
(
    `user_id`                STRING COMMENT '用户id',
    `tm_id`                  STRING COMMENT '品牌id',
    `tm_name`                STRING COMMENT '品牌名称',
    `order_count_7d`         bigint comment '订单个数',
    `order_tm_num_7d`        bigint comment '订单商品件数',
    `order_total_amount_7d`  bigint comment '订单金额',
    `order_count_30d`        bigint comment '订单个数',
    `order_tm_num_30d`       bigint comment '订单商品件数',
    `order_total_amount_30d` bigint comment '订单金额'

) COMMENT '交易域品牌订单粒度最近n日汇总事实表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dws/dws_trade_user_tm_order_nd'
    TBLPROPERTIES ('orc.compress' = 'snappy');
-- 最近n日	品类	订单数
-- 最近n日	品类	订单人数
insert overwrite table dws_trade_user_tm_order_nd partition (dt = '2020-06-14')
select user_id,
       tm_id,
       tm_name,
       sum(`if`(dt >= date_sub('2020-06-14', 6), order_count, null))        order_count_7d,
       sum(`if`(dt >= date_sub('2020-06-14', 6), order_tm_num, null))       order_tm_num_7d,
       sum(`if`(dt >= date_sub('2020-06-14', 6), order_total_amount, null)) order_total_amount_7d,
       sum(order_count)                                                     order_count_30d,
       sum(order_tm_num)                                                    order_tm_num_30d,
       sum(order_total_amount)                                              order_total_amount_30d
from dws_trade_user_tm_order_1d
where dt >= date_sub('2020-06-14', 29)
group by user_id, tm_id, tm_name;


-- 最近1日	品类	订单数
select tm_id, tm_name, sum(order_count)
from dws_trade_user_tm_order_1d
where dt = '2020-06-14'
group by tm_id, tm_name;
-- 最近1日	品类	订单人数
select tm_name, tm_id, count(distinct user_id)
from dws_trade_user_tm_order_1d
where dt = '2020-06-14'
group by tm_name, tm_id;

-- 最近7日	品类	订单数
select tm_name, tm_id, sum(order_count_7d)
from dws_trade_user_tm_order_nd
where dt = '2020-06-14'
group by tm_id, tm_name;
-- 最近7日	品类	订单人数
select tm_id, tm_name, count(distinct if(order_count_7d > 0, user_id, null))
from dws_trade_user_tm_order_nd
where dt = '2020-06-14'
group by tm_id, tm_name;

-- 最近30日	品类	订单数
select tm_name, tm_id, sum(order_count_30d)
from dws_trade_user_tm_order_nd
where dt = '2020-06-14'
group by tm_id, tm_name;
-- 最近30日	品类	订单人数
select tm_name, tm_id, count(distinct user_id)
from dws_trade_user_tm_order_nd
where dt = '2020-06-14'
group by tm_name, tm_id;


-- V3.0
DROP TABLE IF EXISTS dws_trade_user_sku_order_1d;
CREATE EXTERNAL TABLE dws_trade_user_sku_order_1d
(
    `user_id`                   STRING COMMENT '用户id',
    `sku_id`                    STRING COMMENT 'sku_id',
    `sku_name`                  STRING COMMENT 'sku名称',
    `category1_id`              STRING COMMENT '一级分类id',
    `category1_name`            STRING COMMENT '一级分类名称',
    `category2_id`              STRING COMMENT '一级分类id',
    `category2_name`            STRING COMMENT '一级分类名称',
    `category3_id`              STRING COMMENT '一级分类id',
    `category3_name`            STRING COMMENT '一级分类名称',
    `tm_id`                     STRING COMMENT '品牌id',
    `tm_name`                   STRING COMMENT '品牌名称',
    `order_count_1d`            BIGINT COMMENT '最近1日下单次数',
    `order_num_1d`              BIGINT COMMENT '最近1日下单件数',
    `order_original_amount_1d`  DECIMAL(16, 2) COMMENT '最近1日下单原始金额',
    `activity_reduce_amount_1d` DECIMAL(16, 2) COMMENT '最近1日活动优惠金额',
    `coupon_reduce_amount_1d`   DECIMAL(16, 2) COMMENT '最近1日优惠券优惠金额',
    `order_total_amount_1d`     DECIMAL(16, 2) COMMENT '最近1日下单最终金额'
) COMMENT '交易域用户商品粒度订单最近1日汇总事实表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dws/dws_trade_user_sku_order_1d'
    TBLPROPERTIES ('orc.compress' = 'snappy');



DROP TABLE IF EXISTS dws_trade_user_sku_order_nd;
CREATE EXTERNAL TABLE dws_trade_user_sku_order_nd
(
    `user_id`                    STRING COMMENT '用户id',
    `sku_id`                     STRING COMMENT 'sku_id',
    `sku_name`                   STRING COMMENT 'sku名称',
    `category1_id`               STRING COMMENT '一级分类id',
    `category1_name`             STRING COMMENT '一级分类名称',
    `category2_id`               STRING COMMENT '一级分类id',
    `category2_name`             STRING COMMENT '一级分类名称',
    `category3_id`               STRING COMMENT '一级分类id',
    `category3_name`             STRING COMMENT '一级分类名称',
    `tm_id`                      STRING COMMENT '品牌id',
    `tm_name`                    STRING COMMENT '品牌名称',
    `order_count_7d`             STRING COMMENT '最近7日下单次数',
    `order_num_7d`               BIGINT COMMENT '最近7日下单件数',
    `order_original_amount_7d`   DECIMAL(16, 2) COMMENT '最近7日下单原始金额',
    `activity_reduce_amount_7d`  DECIMAL(16, 2) COMMENT '最近7日活动优惠金额',
    `coupon_reduce_amount_7d`    DECIMAL(16, 2) COMMENT '最近7日优惠券优惠金额',
    `order_total_amount_7d`      DECIMAL(16, 2) COMMENT '最近7日下单最终金额',
    `order_count_30d`            BIGINT COMMENT '最近30日下单次数',
    `order_num_30d`              BIGINT COMMENT '最近30日下单件数',
    `order_original_amount_30d`  DECIMAL(16, 2) COMMENT '最近30日下单原始金额',
    `activity_reduce_amount_30d` DECIMAL(16, 2) COMMENT '最近30日活动优惠金额',
    `coupon_reduce_amount_30d`   DECIMAL(16, 2) COMMENT '最近30日优惠券优惠金额',
    `order_total_amount_30d`     DECIMAL(16, 2) COMMENT '最近30日下单最终金额'
) COMMENT '交易域用户商品粒度订单最近n日汇总事实表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dws/dws_trade_user_sku_order_nd'
    TBLPROPERTIES ('orc.compress' = 'snappy');



DROP TABLE IF EXISTS dws_trade_user_order_td;
CREATE EXTERNAL TABLE dws_trade_user_order_td
(
    `user_id`            STRING COMMENT '用户id',
    `order_date_first`   STRING COMMENT '首次下单时间',
    `order_date_last`    STRING COMMENT '末次下单时间',
    `order_count`        bigint comment '历史至今订单个数',
    `order_tm_num`       bigint comment '历史至今订单商品件数',
    `order_total_amount` bigint comment '历史至今订单金额'
) COMMENT '交易域用户粒度订单历史至今汇总事实表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dws/dws_trade_user_order_td'
    TBLPROPERTIES ('orc.compress' = 'snappy');


-- 首日数据装载
-- 2020-06-14
insert overwrite table dws_trade_user_order_td partition (dt = '2020-06-14')
select user_id,
       min(date_id) order_date_first,
       max(date_id) order_date_last,
       count(distinct order_id),
       sum(sku_num),
       sum(split_total_amount)
from dwd_trade_order_detail_inc
group by user_id;

-- 每日数据装载
-- 2020-06-15--->join
insert overwrite table dws_trade_user_order_td partition (dt = '2020-06-15')
select nvl(old.user_id, new.user_id)                                   user_id,
       `if`(old.user_id is null, '2020-06-15', old.order_date_first)   order_date_first,
       `if`(new.user_id is null, old.order_date_last, '2020-06-15')    order_date_last,
       nvl(old.order_count, 0) + nvl(new.order_count, 0)               order_count,
       nvl(old.order_tm_num, 0) + nvl(new.order_tm_num, 0)             order_tm_num,
       nvl(old.order_total_amount, 0) + nvl(new.order_total_amount, 0) order_total_amount
from (
         select `user_id`,
                `order_date_first`,
                `order_date_last`,
                `order_count`,
                `order_tm_num`,
                `order_total_amount`
         from dws_trade_user_order_td
     ) old
         full outer join
     (
         select user_id,
                count(distinct order_id) order_count,
                sum(sku_num)             order_tm_num,
                sum(split_total_amount)  order_total_amount
         from dwd_trade_order_detail_inc
         where dt = '2020-06-15'
         group by user_id
     ) new on old.user_id = new.user_id;


-- 每日数据装载
-- 2020-06-15--->union
insert overwrite table dws_trade_user_order_td partition (dt = '2020-06-15')
select user_id,
       min(order_date_first),
       max(order_date_last),
       sum(order_count),
       sum(order_tm_num),
       sum(order_total_amount)
from (
         select `user_id`,
                `order_date_first`,
                `order_date_last`,
                `order_count`,
                `order_tm_num`,
                `order_total_amount`
         from dws_trade_user_order_td
         union all
         select user_id,
                '2020-06-15'             `order_date_first`,
                '2020-06-15'             `order_date_last`,
                count(distinct order_id) order_count,
                sum(sku_num)             order_tm_num,
                sum(split_total_amount)  order_total_amount
         from dwd_trade_order_detail_inc
         where dt = '2020-06-15'
         group by user_id
     ) t1
group by user_id;
*/


--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*

--1、交易域用户商品粒度订单最近1日汇总表
DROP TABLE IF EXISTS dws_trade_user_sku_order_1d;
CREATE EXTERNAL TABLE dws_trade_user_sku_order_1d
(
    `user_id`                   STRING COMMENT '用户id',
    `sku_id`                    STRING COMMENT 'sku_id',
    `sku_name`                  STRING COMMENT 'sku名称',
    `category1_id`              STRING COMMENT '一级分类id',
    `category1_name`            STRING COMMENT '一级分类名称',
    `category2_id`              STRING COMMENT '一级分类id',
    `category2_name`            STRING COMMENT '一级分类名称',
    `category3_id`              STRING COMMENT '一级分类id',
    `category3_name`            STRING COMMENT '一级分类名称',
    `tm_id`                     STRING COMMENT '品牌id',
    `tm_name`                   STRING COMMENT '品牌名称',
    `order_count_1d`            BIGINT COMMENT '最近1日下单次数',
    `order_num_1d`              BIGINT COMMENT '最近1日下单件数',
    `order_original_amount_1d`  DECIMAL(16, 2) COMMENT '最近1日下单原始金额',
    `activity_reduce_amount_1d` DECIMAL(16, 2) COMMENT '最近1日活动优惠金额',
    `coupon_reduce_amount_1d`   DECIMAL(16, 2) COMMENT '最近1日优惠券优惠金额',
    `order_total_amount_1d`     DECIMAL(16, 2) COMMENT '最近1日下单最终金额'
) COMMENT '交易域用户商品粒度订单最近1日汇总事实表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dws/dws_trade_user_sku_order_1d'
    TBLPROPERTIES ('orc.compress' = 'snappy');


-- 1.1 首日装载
insert overwrite table dws_trade_user_sku_order_1d partition (dt)
select `user_id`,
       `sku_id`,
       `sku_name`,
       `category1_id`,
       `category1_name`,
       `category2_id`,
       `category2_name`,
       `category3_id`,
       `category3_name`,
       `tm_id`,
       `tm_name`,
       `order_count_1d`,
       `order_num_1d`,
       `order_original_amount_1d`,
       `activity_reduce_amount_1d`,
       `coupon_reduce_amount_1d`,
       `order_total_amount_1d`,
       dt
from (
         select dt,
                user_id,
                sku_id,
                count(*)                   order_count_1d,
                sum(sku_num)               order_num_1d,
                sum(split_original_amount) order_original_amount_1d,
                sum(split_activity_amount) activity_reduce_amount_1d,
                sum(split_coupon_amount)   coupon_reduce_amount_1d,
                sum(split_total_amount)    order_total_amount_1d
         from dwd_trade_order_detail_inc
         group by user_id, sku_id, dt
     ) oi
         left join
     (
         select id,
                `sku_name`,
                `category1_id`,
                `category1_name`,
                `category2_id`,
                `category2_name`,
                `category3_id`,
                `category3_name`,
                `tm_id`,
                `tm_name`
         from dim_sku_full
         where dt = '2020-06-14'
     ) sku on oi.sku_id = sku.id;

show partitions dws_trade_user_sku_order_1d;

-- 1.2 每日装载
insert overwrite table dws_trade_user_sku_order_1d partition (dt = '2020-06-15')
select `user_id`,
       `sku_id`,
       `sku_name`,
       `category1_id`,
       `category1_name`,
       `category2_id`,
       `category2_name`,
       `category3_id`,
       `category3_name`,
       `tm_id`,
       `tm_name`,
       `order_count_1d`,
       `order_num_1d`,
       `order_original_amount_1d`,
       `activity_reduce_amount_1d`,
       `coupon_reduce_amount_1d`,
       `order_total_amount_1d`
from (
         select user_id,
                sku_id,
                count(*)                   order_count_1d,
                sum(sku_num)               order_num_1d,
                sum(split_original_amount) order_original_amount_1d,
                sum(split_activity_amount) activity_reduce_amount_1d,
                sum(split_coupon_amount)   coupon_reduce_amount_1d,
                sum(split_total_amount)    order_total_amount_1d
         from dwd_trade_order_detail_inc
         where dt = '2020-06-15'
         group by user_id, sku_id
     ) oi
         left join
     (
         select id,
                `sku_name`,
                `category1_id`,
                `category1_name`,
                `category2_id`,
                `category2_name`,
                `category3_id`,
                `category3_name`,
                `tm_id`,
                `tm_name`
         from dim_sku_full
         where dt = '2020-06-15'
     ) sku on oi.sku_id = sku.id;



-- 2、交易域用户粒度订单最近1日汇总表
-- 1）建表语句
DROP TABLE IF EXISTS dws_trade_user_order_1d;
CREATE EXTERNAL TABLE dws_trade_user_order_1d
(
    `user_id`                   STRING COMMENT '用户id',
    `order_count_1d`            BIGINT COMMENT '最近1日下单次数',
    `order_num_1d`              BIGINT COMMENT '最近1日下单商品件数',
    `order_original_amount_1d`  DECIMAL(16, 2) COMMENT '最近1日最近1日下单原始金额',
    `activity_reduce_amount_1d` DECIMAL(16, 2) COMMENT '最近1日下单活动优惠金额',
    `coupon_reduce_amount_1d`   DECIMAL(16, 2) COMMENT '下单优惠券优惠金额',
    `order_total_amount_1d`     DECIMAL(16, 2) COMMENT '最近1日下单最终金额'
) COMMENT '交易域用户粒度订单最近1日汇总事实表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dws/dws_trade_user_order_1d'
    TBLPROPERTIES ('orc.compress' = 'snappy');



--  3、交易域用户粒度加购最近1日汇总表
-- 1）建表语句
DROP TABLE IF EXISTS dws_trade_user_cart_add_1d;
CREATE EXTERNAL TABLE dws_trade_user_cart_add_1d
(
    `user_id`           STRING COMMENT '用户id',
    `cart_add_count_1d` BIGINT COMMENT '最近1日加购次数',
    `cart_add_num_1d`   BIGINT COMMENT '最近1日加购商品件数'
) COMMENT '交易域用户粒度加购最近1日汇总事实表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dws/dws_trade_user_cart_add_1d'
    TBLPROPERTIES ('orc.compress' = 'snappy');



-- 4、交易域用户粒度支付最近1日汇总表
-- 1）建表语句
DROP TABLE IF EXISTS dws_trade_user_payment_1d;
CREATE EXTERNAL TABLE dws_trade_user_payment_1d
(
    `user_id`           STRING COMMENT '用户id',
    `payment_count_1d`  BIGINT COMMENT '最近1日支付次数',
    `payment_num_1d`    BIGINT COMMENT '最近1日支付商品件数',
    `payment_amount_1d` DECIMAL(16, 2) COMMENT '最近1日支付金额'
) COMMENT '交易域用户粒度支付最近1日汇总事实表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dws/dws_trade_user_payment_1d'
    TBLPROPERTIES ('orc.compress' = 'snappy');



-- 5、交易域省份粒度订单最近1日汇总表
-- 1）建表语句
DROP TABLE IF EXISTS dws_trade_province_order_1d;
CREATE EXTERNAL TABLE dws_trade_province_order_1d
(
    `province_id`               STRING COMMENT '省份id',
    `province_name`             STRING COMMENT '省份名称',
    `area_code`                 STRING COMMENT '地区编码',
    `iso_code`                  STRING COMMENT '旧版ISO-3166-2编码',
    `iso_3166_2`                STRING COMMENT '新版版ISO-3166-2编码',
    `order_count_1d`            BIGINT COMMENT '最近1日下单次数',
    `order_original_amount_1d`  DECIMAL(16, 2) COMMENT '最近1日下单原始金额',
    `activity_reduce_amount_1d` DECIMAL(16, 2) COMMENT '最近1日下单活动优惠金额',
    `coupon_reduce_amount_1d`   DECIMAL(16, 2) COMMENT '最近1日下单优惠券优惠金额',
    `order_total_amount_1d`     DECIMAL(16, 2) COMMENT '最近1日下单最终金额'
) COMMENT '交易域省份粒度订单最近1日汇总事实表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dws/dws_trade_province_order_1d'
    TBLPROPERTIES ('orc.compress' = 'snappy');



-- 6、工具域用户优惠券粒度优惠券使用(支付)最近1日汇总表
-- 1）建表语句
DROP TABLE IF EXISTS dws_tool_user_coupon_coupon_used_1d;
CREATE EXTERNAL TABLE dws_tool_user_coupon_coupon_used_1d
(
    `user_id`          STRING COMMENT '用户id',
    `coupon_id`        STRING COMMENT '优惠券id',
    `coupon_name`      STRING COMMENT '优惠券名称',
    `coupon_type_code` STRING COMMENT '优惠券类型编码',
    `coupon_type_name` STRING COMMENT '优惠券类型名称',
    `benefit_rule`     STRING COMMENT '优惠规则',
    `used_count_1d`    STRING COMMENT '使用(支付)次数'
) COMMENT '工具域用户优惠券粒度优惠券使用(支付)最近1日汇总表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dws/dws_tool_user_coupon_coupon_used_1d'
    TBLPROPERTIES ('orc.compress' = 'snappy');



-- 7、互动域商品粒度收藏商品最近1日汇总表
-- 1）建表语句
DROP TABLE IF EXISTS dws_interaction_sku_favor_add_1d;
CREATE EXTERNAL TABLE dws_interaction_sku_favor_add_1d
(
    `sku_id`             STRING COMMENT '商品id',
    `sku_name`           STRING COMMENT 'sku名称',
    `category1_id`       STRING COMMENT '一级分类id',
    `category1_name`     STRING COMMENT '一级分类名称',
    `category2_id`       STRING COMMENT '一级分类id',
    `category2_name`     STRING COMMENT '一级分类名称',
    `category3_id`       STRING COMMENT '一级分类id',
    `category3_name`     STRING COMMENT '一级分类名称',
    `tm_id`              STRING COMMENT '品牌id',
    `tm_name`            STRING COMMENT '品牌名称',
    `favor_add_count_1d` BIGINT COMMENT '商品被收藏次数'
) COMMENT '互动域商品粒度收藏商品最近1日汇总表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dws/dws_interaction_sku_favor_add_1d'
    TBLPROPERTIES ('orc.compress' = 'snappy');



-- 8、流量域会话粒度页面浏览最近1日汇总表
-- 1）建表语句
DROP TABLE IF EXISTS dws_traffic_session_page_view_1d;
CREATE EXTERNAL TABLE dws_traffic_session_page_view_1d
(
    `session_id`     STRING COMMENT '会话id',
    `mid_id`         string comment '设备id',
    `brand`          string comment '手机品牌',
    `model`          string comment '手机型号',
    `operate_system` string comment '操作系统',
    `version_code`   string comment 'app版本号',
    `channel`        string comment '渠道',
    `during_time_1d` BIGINT COMMENT '最近1日访问时长',
    `page_count_1d`  BIGINT COMMENT '最近1日访问页面数'
) COMMENT '流量域会话粒度页面浏览最近1日汇总表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dws/dws_traffic_session_page_view_1d'
    TBLPROPERTIES ('orc.compress' = 'snappy');

-- 2）数据装载
insert overwrite table dws_traffic_session_page_view_1d partition (dt = '2020-06-14')
select session_id,
       mid_id,
       brand,
       model,
       operate_system,
       version_code,
       channel,
       sum(during_time),
       count(*)
from dwd_traffic_page_view_inc
where dt = '2020-06-14'
group by session_id, mid_id, brand, model, operate_system, version_code, channel;



-- 9、流量域访客页面粒度页面浏览最近1日汇总表
-- 1）建表语句
DROP TABLE IF EXISTS dws_traffic_page_visitor_page_view_1d;
CREATE EXTERNAL TABLE dws_traffic_page_visitor_page_view_1d
(
    `mid_id`         STRING COMMENT '访客id',
    `brand`          string comment '手机品牌',
    `model`          string comment '手机型号',
    `operate_system` string comment '操作系统',
    `page_id`        STRING COMMENT '页面id',
    `during_time_1d` BIGINT COMMENT '最近1日浏览时长',
    `view_count_1d`  BIGINT COMMENT '最近1日访问次数'
) COMMENT '流量域访客页面粒度页面浏览最近1日汇总事实表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dws/dws_traffic_page_visitor_page_view_1d'
    TBLPROPERTIES ('orc.compress' = 'snappy');

show tables like 'dws*1d';


--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*

-- 1、交易域用户商品粒度订单最近n日汇总表
-- 1）建表语句
DROP TABLE IF EXISTS dws_trade_user_sku_order_nd;
CREATE EXTERNAL TABLE dws_trade_user_sku_order_nd
(
    `user_id`                    STRING COMMENT '用户id',
    `sku_id`                     STRING COMMENT 'sku_id',
    `sku_name`                   STRING COMMENT 'sku名称',
    `category1_id`               STRING COMMENT '一级分类id',
    `category1_name`             STRING COMMENT '一级分类名称',
    `category2_id`               STRING COMMENT '一级分类id',
    `category2_name`             STRING COMMENT '一级分类名称',
    `category3_id`               STRING COMMENT '一级分类id',
    `category3_name`             STRING COMMENT '一级分类名称',
    `tm_id`                      STRING COMMENT '品牌id',
    `tm_name`                    STRING COMMENT '品牌名称',
    `order_count_7d`             STRING COMMENT '最近7日下单次数',
    `order_num_7d`               BIGINT COMMENT '最近7日下单件数',
    `order_original_amount_7d`   DECIMAL(16, 2) COMMENT '最近7日下单原始金额',
    `activity_reduce_amount_7d`  DECIMAL(16, 2) COMMENT '最近7日活动优惠金额',
    `coupon_reduce_amount_7d`    DECIMAL(16, 2) COMMENT '最近7日优惠券优惠金额',
    `order_total_amount_7d`      DECIMAL(16, 2) COMMENT '最近7日下单最终金额',
    `order_count_30d`            BIGINT COMMENT '最近30日下单次数',
    `order_num_30d`              BIGINT COMMENT '最近30日下单件数',
    `order_original_amount_30d`  DECIMAL(16, 2) COMMENT '最近30日下单原始金额',
    `activity_reduce_amount_30d` DECIMAL(16, 2) COMMENT '最近30日活动优惠金额',
    `coupon_reduce_amount_30d`   DECIMAL(16, 2) COMMENT '最近30日优惠券优惠金额',
    `order_total_amount_30d`     DECIMAL(16, 2) COMMENT '最近30日下单最终金额'
) COMMENT '交易域用户商品粒度订单最近n日汇总事实表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dws/dws_trade_user_sku_order_nd'
    TBLPROPERTIES ('orc.compress' = 'snappy');

-- 数据装载
insert overwrite table dws_trade_user_sku_order_nd partition (dt = '2020-06-14')
select user_id,
       sku_id,
       sku_name,
       category1_id,
       category1_name,
       category2_id,
       category2_name,
       category3_id,
       category3_name,
       tm_id,
       tm_name,
       sum(if(dt >= date_add('2020-06-14', -6), order_count_1d, 0)),
       sum(if(dt >= date_add('2020-06-14', -6), order_num_1d, 0)),
       sum(if(dt >= date_add('2020-06-14', -6), order_original_amount_1d, 0)),
       sum(if(dt >= date_add('2020-06-14', -6), activity_reduce_amount_1d, 0)),
       sum(if(dt >= date_add('2020-06-14', -6), coupon_reduce_amount_1d, 0)),
       sum(if(dt >= date_add('2020-06-14', -6), order_total_amount_1d, 0)),
       sum(order_count_1d),
       sum(order_num_1d),
       sum(order_original_amount_1d),
       sum(activity_reduce_amount_1d),
       sum(coupon_reduce_amount_1d),
       sum(order_total_amount_1d)
from dws_trade_user_sku_order_1d
where dt >= date_add('2020-06-14', -29)
group by user_id, sku_id, sku_name, category1_id, category1_name, category2_id, category2_name, category3_id,
         category3_name, tm_id, tm_name;



-- 2、交易域省份粒度订单最近n日汇总表
-- 1）建表语句
DROP TABLE IF EXISTS dws_trade_province_order_nd;
CREATE EXTERNAL TABLE dws_trade_province_order_nd
(
    `province_id`                STRING COMMENT '省份id',
    `province_name`              STRING COMMENT '省份名称',
    `area_code`                  STRING COMMENT '地区编码',
    `iso_code`                   STRING COMMENT '旧版ISO-3166-2编码',
    `iso_3166_2`                 STRING COMMENT '新版版ISO-3166-2编码',
    `order_count_7d`             BIGINT COMMENT '最近7日下单次数',
    `order_original_amount_7d`   DECIMAL(16, 2) COMMENT '最近7日下单原始金额',
    `activity_reduce_amount_7d`  DECIMAL(16, 2) COMMENT '最近7日下单活动优惠金额',
    `coupon_reduce_amount_7d`    DECIMAL(16, 2) COMMENT '最近7日下单优惠券优惠金额',
    `order_total_amount_7d`      DECIMAL(16, 2) COMMENT '最近7日下单最终金额',
    `order_count_30d`            BIGINT COMMENT '最近30日下单次数',
    `order_original_amount_30d`  DECIMAL(16, 2) COMMENT '最近30日下单原始金额',
    `activity_reduce_amount_30d` DECIMAL(16, 2) COMMENT '最近30日下单活动优惠金额',
    `coupon_reduce_amount_30d`   DECIMAL(16, 2) COMMENT '最近30日下单优惠券优惠金额',
    `order_total_amount_30d`     DECIMAL(16, 2) COMMENT '最近30日下单最终金额'
) COMMENT '交易域省份粒度订单最近n日汇总事实表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dws/dws_trade_province_order_nd'
    TBLPROPERTIES ('orc.compress' = 'snappy');



--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*
-- 1、交易域用户粒度订单历史至今汇总表
-- 1）建表语句
DROP TABLE IF EXISTS dws_trade_user_order_td;
CREATE EXTERNAL TABLE dws_trade_user_order_td
(
    `user_id`                   STRING COMMENT '用户id',
    `order_date_first`          STRING COMMENT '首次下单日期',
    `order_date_last`           STRING COMMENT '末次下单日期',
    `order_count_td`            BIGINT COMMENT '下单次数',
    `order_num_td`              BIGINT COMMENT '购买商品件数',
    `original_amount_td`        DECIMAL(16, 2) COMMENT '原始金额',
    `activity_reduce_amount_td` DECIMAL(16, 2) COMMENT '活动优惠金额',
    `coupon_reduce_amount_td`   DECIMAL(16, 2) COMMENT '优惠券优惠金额',
    `total_amount_td`           DECIMAL(16, 2) COMMENT '最终金额'
) COMMENT '交易域用户粒度订单历史至今汇总事实表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dws/dws_trade_user_order_td'
    TBLPROPERTIES ('orc.compress' = 'snappy');



--  2、用户域用户粒度登录历史至今汇总表
-- 1）建表语句
DROP TABLE IF EXISTS dws_user_user_login_td;
CREATE EXTERNAL TABLE dws_user_user_login_td
(
    `user_id`         STRING COMMENT '用户id',
    `login_date_last` STRING COMMENT '末次登录日期',
    `login_count_td`  BIGINT COMMENT '累计登录次数'
) COMMENT '用户域用户粒度登录历史至今汇总事实表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dws/dws_user_user_login_td'
    TBLPROPERTIES ('orc.compress' = 'snappy');


-- 2）数据装载
-- （1）首日装载
insert overwrite table dws_user_user_login_td partition (dt = '2020-06-14')
select zip.id,
       `if`(log.user_id is not null, log.login_date_last, zip.login_date_last),
       `if`(log.user_id is not null, login_count_td, 1)
from (
         select id,
                date_format(create_time, 'yyyy-MM-dd') login_date_last
         from dim_user_zip
         where dt = '9999-12-31'
     ) zip
         left join
     (
         select user_id,
                max(date_id) login_date_last,
                count(*)     login_count_td
         from dwd_user_login_inc
         group by user_id
     ) log
     on zip.id = log.user_id;

--(2）每日装载
insert overwrite table dws_user_user_login_td partition (dt = '2020-06-15')
select nvl(old.user_id, new.user_id)                                    user_id,
       `if`(old.user_id is not null, old.login_date_last, '2020-06-15') login_date_last,
       nvl(old.login_count_td, 1)                                       login_count_td
from (
         select user_id,
                login_count_td,
                login_date_last
         from dws_user_user_login_td
         where dt = date_add('2020-06-15', -1)
     ) old
         full outer join
     (
         select user_id,
                count(*) login_count_td
         from dwd_user_login_inc
         where dt = '2020-06-15'
         group by user_id
     ) new
     on old.user_id = new.user_id



