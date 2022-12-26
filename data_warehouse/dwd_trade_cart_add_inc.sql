-- DWD 事实表
-- 1. 交易域-加购-事务事实表
-- 加购 ： 用户在某一个时间点向购物车中增加了新的商品（全新，数量的新增）


-- 1、建表语句
-- 表名
-- 分区规划 ： 每日分区保存 （首日动态分区，每日静态分区）

drop table if exists dwd_trade_cart_add_inc;
create external table dwd_trade_cart_add_inc
(
    `id`               STRING COMMENT '编号',
    `user_id`          STRING COMMENT '用户id',
    `sku_id`           STRING COMMENT '商品id',
    `date_id`          STRING COMMENT '时间id',
    `create_time`      STRING COMMENT '加购时间',
    `source_id`        STRING COMMENT '来源类型ID',
    `source_type_code` STRING COMMENT '来源类型编码',
    `source_type_name` STRING COMMENT '来源类型名称',
    `sku_num`          BIGINT COMMENT '加购物车件数'
) comment '交易域加购物车事务事实表'
    partitioned by (dt string)
    stored as orc
    location '/warehouse/gmall/dwd/dwd_trade_cart_add_inc/'
    tblproperties ('orc.compress' = 'snappy');


-- 2、加载数据
-- 2.1首日加载
-- maxwell - 首日 - type -> bootstrap-start, insert, finish

set hive.exec.dynamic.partition.mode=nonstrict;
insert overwrite table dwd_trade_cart_add_inc partition (dt)
select id
     , user_id
     , sku_id
     , date_format(create_time, 'yyyy-MM-dd') date_id
     , create_time
     , source_id
     , source_type
     , dic.dic_name                           source_type_name
     , sku_num
     , date_format(create_time, 'yyyy-MM-dd')
from (select data.id
           , data.user_id
           , data.sku_id
           , data.create_time
           , data.source_id
           , data.source_type
           , data.sku_num
      from ods_cart_info_inc
      where dt = '2020-06-14'
        and type = 'bootstrap-insert') cart
         left join (
    select dic_code,
           dic_name
    from ods_base_dic_full
    where dt = '2020-06-14'
      and parent_code = '24'
) dic on cart.source_id = dic.dic_code;

show partitions dwd_trade_cart_add_inc;

-- 2.2每日数据加载
-- 2020-06-15
-- 判断哪些数据是加购操作
-- 06-15 新增商品加入到购物车 => insert into => create time => 加购时间
-- 06-15 修改购物车中商品的数量 => update (sku_num)
-- 加购数据需要保存 new.sku_num > old.sku_num  (增量表 - update - old)
-- 减购数据不考虑   new.sku_num < old.sku_num  (增量表 - update - old)

-- 数据字段类型：结构体和map的区别
-- map : 字段数量不确定，KV类型相同
-- 结构体 : 字段数量固定，不同的字段属性类型不一致

-- map_keys : 获取map类型字段的所有key，返回的就是key的数组
-- array_contains: 判断集合中是否包含指定数据
-- date_format 时间格式化
-- from_utc_timestamp 转换时区

insert overwrite table dwd_trade_cart_add_inc partition (dt = '2020-06-15')
select id
     , user_id
     , sku_id
     , date_format(from_utc_timestamp(ts * 1000, 'GMT+8'), 'yyyy-MM-dd')          date_id
     , date_format(from_utc_timestamp(ts * 1000, 'GMT+8'), 'yyyy-MM-dd HH:mm:ss') create_time
     , source_id
     , source_type
     , dic.dic_name                                                               source_type_name
     , sku_num
from (select data.id
           , data.user_id
           , data.sku_id
           , ts
           , data.source_id
           , data.source_type
           , if(type = 'insert', data.sku_num, data.sku_num - old['sku_num']) sku_num
      from ods_cart_info_inc
      where dt = '2020-06-15'
        and (type = 'insert' or
             (type = 'update' and array_contains(map_keys(old), 'sku_num') and
              data.sku_num > cast(old['sku_num'] as bigint)))) cart
         left join (
    select dic_code,
           dic_name
    from ods_base_dic_full
    where dt = '2020-06-15'
      and parent_code = '24'
) dic on cart.source_id = dic.dic_code;



--  时间函数练习
--  from_unixtime : 获取0时区的时间数据，传递参数为long
--  from_utc_timestamp : 获取指定时区的时间数据，传递参数为long,GMT+8表示东8区
--  from_utc_timestamp : 传入整数按ms算，传入小数按s算

select ts,
       from_unixtime(ts, 'yyyy-MM-dd HH:mm:ss'),
       from_utc_timestamp(ts * 1000, 'GMT+8'),
       date_format(from_utc_timestamp(ts * 1000, 'GMT+8'), 'yyyy-MM-dd HH:mm:ss')
from ods_cart_info_inc;



-- 2、交易域-下单-事务事实表
-- 建表
-- 表名
-- 粒度
-- 维度
-- 事实（度量值）

-- 2.1 建表语句
drop table if exists dwd_trade_order_detail_inc;
create external table dwd_trade_order_detail_inc
(
    `id`                    STRING COMMENT '编号',
    `order_id`              STRING COMMENT '订单id',
    `user_id`               STRING COMMENT '用户id',
    `sku_id`                STRING COMMENT '商品id',
    `province_id`           STRING COMMENT '省份id',
    `activity_id`           STRING COMMENT '参与活动规则id',
    `activity_rule_id`      STRING COMMENT '参与活动规则id',
    `coupon_id`             STRING COMMENT '使用优惠券id',
    `date_id`               STRING COMMENT '下单日期id',
    `create_time`           STRING COMMENT '下单时间',
    `source_id`             STRING COMMENT '来源编号',
    `source_type_code`      STRING COMMENT '来源类型编码',
    `source_type_name`      STRING COMMENT '来源类型名称',
    `sku_num`               BIGINT COMMENT '商品数量',
    `split_original_amount` DECIMAL(16, 2) COMMENT '原始价格',
    `split_activity_amount` DECIMAL(16, 2) COMMENT '活动优惠分摊',
    `split_coupon_amount`   DECIMAL(16, 2) COMMENT '优惠券优惠分摊',
    `split_total_amount`    DECIMAL(16, 2) COMMENT '最终价格分摊'
) comment '交易域下单明细事务事实表'
    partitioned by (`dt` string)
    stored as orc
    location '/warehouse/gmall/dwd/dwd_trade_order_detail_inc/'
    tblproperties ('orc.compress' = 'snappy');



-- 2.2 装载数据
-- 2.2.1首日
set hive.exec.dynamic.partition.mode=nonstrict;
insert overwrite table dwd_trade_order_detail_inc partition (dt)
select od.id,
       order_id,
       user_id,
       sku_id,
       province_id,
       activity_id,
       activity_rule_id,
       coupon_id,
       date_format(create_time, 'yyyy-MM-dd') date_id,
       create_time,
       source_id,
       source_type,
       dic_name,
       sku_num,
       split_original_amount,
       nvl(split_activity_amount, 0.0),
       nvl(split_coupon_amount, 0.0),
       split_total_amount,
       date_format(create_time, 'yyyy-MM-dd')
from (
         select data.id,
                data.order_id,
                data.sku_id,
                data.create_time,
                data.source_id,
                data.source_type,
                data.sku_num,
                data.sku_num * data.order_price split_original_amount,
                data.split_total_amount,
                data.split_activity_amount,
                data.split_coupon_amount
         from ods_order_detail_inc
         where dt = '2020-06-14'
           and type = 'bootstrap-insert'
     ) od
         left join (
    select data.id,
           data.user_id,
           data.province_id
    from ods_order_info_inc
    where dt = '2020-06-14'
      and type = 'bootstrap-insert'
) oi
                   on od.order_id = oi.id
         left join (
    select data.order_detail_id,
           data.activity_id,
           data.activity_rule_id
    from ods_order_detail_activity_inc
    where dt = '2020-06-14'
      and type = 'bootstrap-insert'
) act
                   on od.id = act.order_detail_id
         left join (
    select data.order_detail_id,
           data.coupon_id
    from ods_order_detail_coupon_inc
    where dt = '2020-06-14'
      and type = 'bootstrap-insert'
) cou
                   on od.id = cou.order_detail_id
         left join (
    select dic_code,
           dic_name
    from ods_base_dic_full
    where dt = '2020-06-14'
      and parent_code = '24'
) dic
                   on od.source_id = dic.dic_code;

-- 2.2.2每日
insert overwrite table dwd_trade_order_detail_inc partition (dt = '2020-06-15')
select od.id,
       order_id,
       user_id,
       sku_id,
       province_id,
       activity_id,
       activity_rule_id,
       coupon_id,
       date_id,
       create_time,
       source_id,
       source_type,
       dic_name,
       sku_num,
       split_original_amount,
       nvl(split_activity_amount, 0.0),
       nvl(split_coupon_amount, 0.0),
       split_total_amount
from (
         select data.id,
                data.order_id,
                data.sku_id,
                data.create_time,
                date_format(data.create_time, 'yyyy-MM-dd') date_id,
                data.source_id,
                data.source_type,
                data.sku_num,
                data.sku_num * data.order_price             split_original_amount,
                data.split_total_amount,
                data.split_activity_amount,
                data.split_coupon_amount
         from ods_order_detail_inc
         where dt = '2020-06-15'
           and type = 'insert'
     ) od
         left join (
    select data.id,
           data.user_id,
           data.province_id
    from ods_order_info_inc
    where dt = '2020-06-15'
      and type = 'insert'
) oi
                   on od.order_id = oi.id
         left join (
    select data.order_detail_id,
           data.activity_id,
           data.activity_rule_id
    from ods_order_detail_activity_inc
    where dt = '2020-06-15'
      and type = 'insert'
) act
                   on od.id = act.order_detail_id
         left join (
    select data.order_detail_id,
           data.coupon_id
    from ods_order_detail_coupon_inc
    where dt = '2020-06-15'
      and type = 'insert'
) cou
                   on od.id = cou.order_detail_id
         left join (
    select dic_code,
           dic_name
    from ods_base_dic_full
    where dt = '2020-06-15'
      and parent_code = '24'
) dic
                   on od.source_id = dic.dic_code;



-- 3、交易域-支付成功-事务事实表
-- 3.1 建表语句

DROP TABLE IF EXISTS dwd_trade_pay_detail_suc_inc;
CREATE EXTERNAL TABLE dwd_trade_pay_detail_suc_inc
(
    `id`                    STRING COMMENT '编号',
    `order_id`              STRING COMMENT '订单id',
    `user_id`               STRING COMMENT '用户id',
    `sku_id`                STRING COMMENT '商品id',
    `province_id`           STRING COMMENT '省份id',
    `activity_id`           STRING COMMENT '参与活动规则id',
    `activity_rule_id`      STRING COMMENT '参与活动规则id',
    `coupon_id`             STRING COMMENT '使用优惠券id',
    `payment_type_code`     STRING COMMENT '支付类型编码',
    `payment_type_name`     STRING COMMENT '支付类型名称',
    `date_id`               STRING COMMENT '支付日期id',
    `callback_time`         STRING COMMENT '支付成功时间',
    `source_id`             STRING COMMENT '来源编号',
    `source_type_code`      STRING COMMENT '来源类型编码',
    `source_type_name`      STRING COMMENT '来源类型名称',
    `sku_num`               BIGINT COMMENT '商品数量',
    `split_original_amount` DECIMAL(16, 2) COMMENT '应支付原始金额',
    `split_activity_amount` DECIMAL(16, 2) COMMENT '支付活动优惠分摊',
    `split_coupon_amount`   DECIMAL(16, 2) COMMENT '支付优惠券优惠分摊',
    `split_payment_amount`  DECIMAL(16, 2) COMMENT '支付金额'
) COMMENT '交易域成功支付事务事实表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dwd/dwd_trade_pay_detail_suc_inc/'
    TBLPROPERTIES ('orc.compress' = 'snappy');


-- 3.2 装载数据
-- 3.2.1 首日(2020-06-14)装载
set hive.exec.dynamic.partition.mode=nonstrict;
insert overwrite table dwd_trade_pay_detail_suc_inc partition (dt)
select od.`id`,--STRING COMMENT '编号',
       od.`order_id`,--STRING COMMENT '订单id',
       `user_id`,--STRING COMMENT '用户id',
       `sku_id`,--STRING COMMENT '商品id',
       `province_id`,--STRING COMMENT '省份id',
       `activity_id`,--STRING COMMENT '参与活动规则id',
       `activity_rule_id`,--STRING COMMENT '参与活动规则id',
       `coupon_id`,--STRING COMMENT '使用优惠券id',
       payment_type,--STRING COMMENT '支付类型编码',
       pay_base.dic_name,--STRING COMMENT '支付类型名称',
       date_format(callback_time, 'yyyy-MM-dd') date_id,--STRING COMMENT '支付日期id',
       `callback_time`,--STRING COMMENT '支付成功时间',
       `source_id`,--STRING COMMENT '来源编号',
       source_type                              `source_type_code`,--STRING COMMENT '来源类型编码',
       sou_base.`dic_name`                      source_type_name,--STRING COMMENT '来源类型名称',
       `sku_num`,--BIGINT COMMENT '商品数量',
       `split_original_amount`,--DECIMAL(16, 2) COMMENT '应支付原始金额',
       nvl(`split_activity_amount`, 0.0),--DECIMAL(16, 2) COMMENT '支付活动优惠分摊',
       nvl(`split_coupon_amount`, 0.0),--DECIMAL(16, 2) COMMENT '支付优惠券优惠分摊',
       split_total_amount                       `split_payment_amount`,--DECIMAL(16, 2) COMMENT '支付金额'
       date_format(callback_time, 'yyyy-MM-dd')
from (
         select data.id,
                data.order_id,
                data.sku_id,
                data.source_id,
                data.source_type,
                data.sku_num,
                data.sku_num * data.order_price split_original_amount,
                data.split_total_amount,
                data.split_activity_amount,
                data.split_coupon_amount
         from ods_order_detail_inc
         where dt = '2020-06-14'
           and type = 'bootstrap-insert'
     ) od
         join
     (
         select data.order_id,
                data.payment_type,
                data.user_id,
                data.callback_time
         from ods_payment_info_inc
         where dt = '2020-06-14'
           and type = 'bootstrap-insert'
           and data.payment_status = '1602' --已完成支付
     ) pay_info on od.order_id = pay_info.order_id
         left join
     (
         select data.id,
                data.province_id
         from ods_order_info_inc
         where dt = '2020-06-14'
           and type = 'bootstrap-insert'
     ) ord on od.order_id = ord.id
         left join
     (
         select data.order_detail_id,
                data.activity_id,
                data.activity_rule_id
         from ods_order_detail_activity_inc
         where dt = '2020-06-14'
           and type = 'bootstrap-insert'
     ) od_ac on od.id = od_ac.order_detail_id
         left join
     (
         select data.order_detail_id,
                data.coupon_id
         from ods_order_detail_coupon_inc
         where dt = '2020-06-14'
           and type = 'bootstrap-insert'
     ) od_cou on od.id = od_cou.order_detail_id
         left join
     (
         select dic_code,
                dic_name
         from ods_base_dic_full
         where dt = '2020-06-14'
           and parent_code = '24'
     ) sou_base on od.source_id = sou_base.dic_code
         left join
     (
         select dic_code,
                dic_name
         from ods_base_dic_full
         where dt = '2020-06-14'
           and parent_code = '11'
     ) pay_base on pay_info.payment_type = pay_base.dic_code

show partitions dwd_trade_pay_detail_suc_inc;
select *
from dwd_trade_pay_detail_suc_inc
where dt = 2020 - 06 - 15;

-- 3.2.2 每日(2020-06-14)装载
-- 关联的两张表中有空表时，会出现ClassCastException
set hive.mapjoin.optimized.hashtable=false;
insert overwrite table dwd_trade_pay_detail_suc_inc partition (dt = '2020-06-15')
select od.`id`,--STRING COMMENT '编号',
       od.`order_id`,--STRING COMMENT '订单id',
       `user_id`,--STRING COMMENT '用户id',
       `sku_id`,--STRING COMMENT '商品id',
       `province_id`,--STRING COMMENT '省份id',
       `activity_id`,--STRING COMMENT '参与活动规则id',
       `activity_rule_id`,--STRING COMMENT '参与活动规则id',
       `coupon_id`,--STRING COMMENT '使用优惠券id',
       payment_type,--STRING COMMENT '支付类型编码',
       pay_base.dic_name,--STRING COMMENT '支付类型名称',
       date_format(callback_time, 'yyyy-MM-dd') date_id,--STRING COMMENT '支付日期id',
       `callback_time`,--STRING COMMENT '支付成功时间',
       `source_id`,--STRING COMMENT '来源编号',
       source_type                              `source_type_code`,--STRING COMMENT '来源类型编码',
       sou_base.`dic_name`                      source_type_name,--STRING COMMENT '来源类型名称',
       `sku_num`,--BIGINT COMMENT '商品数量',
       `split_original_amount`,--DECIMAL(16, 2) COMMENT '应支付原始金额',
       nvl(`split_activity_amount`, 0.0),--DECIMAL(16, 2) COMMENT '支付活动优惠分摊',
       nvl(`split_coupon_amount`, 0.0),--DECIMAL(16, 2) COMMENT '支付优惠券优惠分摊',
       split_total_amount                       `split_payment_amount`--DECIMAL(16, 2) COMMENT '支付金额'
from (
         select data.id,
                data.order_id,
                data.sku_id,
                data.source_id,
                data.source_type,
                data.sku_num,
                data.sku_num * data.order_price split_original_amount,
                data.split_total_amount,
                data.split_activity_amount,
                data.split_coupon_amount
         from ods_order_detail_inc
         where (dt = date_sub('2020-06-15', 1) or dt = '2020-06-15')
           and (type = 'bootstrap-insert' or type = 'insert')
     ) od
         join
     (
         select data.order_id,
                data.payment_type,
                data.user_id,
                data.callback_time
         from ods_payment_info_inc
         where (dt = date_sub('2020-06-15', 1) or dt = '2020-06-15')
           and type = 'update'                                 -- 支付表中第一次为创建订单，之后的操作肯定是update操作
           and array_contains(map_keys(old), 'payment_status') -- 更新后，old中应该有该字段。
           and data.payment_status = '1602' --已完成支付
     ) pay_info on od.order_id = pay_info.order_id
         left join
     (
         select data.id,
                data.province_id
         from ods_order_info_inc
         where (dt = date_sub('2020-06-15', 1) or dt = '2020-06-15')
           and (type = 'bootstrap-insert' or type = 'insert')
     ) ord on od.order_id = ord.id
         left join
     (
         select data.order_detail_id,
                data.activity_id,
                data.activity_rule_id
         from ods_order_detail_activity_inc
         where (dt = date_sub('2020-06-15', 1) or dt = '2020-06-15')
           and (type = 'bootstrap-insert' or type = 'insert')
     ) od_ac on od.id = od_ac.order_detail_id
         left join
     (
         select data.order_detail_id,
                data.coupon_id
         from ods_order_detail_coupon_inc
         where (dt = date_sub('2020-06-15', 1) or dt = '2020-06-15')
           and (type = 'bootstrap-insert' or type = 'insert')
     ) od_cou on od.id = od_cou.order_detail_id
         left join
     (
         select dic_code,
                dic_name
         from ods_base_dic_full
         where dt = '2020-06-15'
           and parent_code = '24'
     ) sou_base on od.source_id = sou_base.dic_code
         left join
     (
         select dic_code,
                dic_name
         from ods_base_dic_full
         where dt = '2020-06-15'
           and parent_code = '11'
     ) pay_base on pay_info.payment_type = pay_base.dic_code;


-- 4、交易域-购物车-周期快照事实表
-- 4.1 建表语句
DROP TABLE IF EXISTS dwd_trade_cart_full;
CREATE EXTERNAL TABLE dwd_trade_cart_full
(
    `id`       STRING COMMENT '编号',
    `user_id`  STRING COMMENT '用户id',
    `sku_id`   STRING COMMENT '商品id',
    `sku_name` STRING COMMENT '商品名称',
    `sku_num`  BIGINT COMMENT '加购物车件数'
) COMMENT '交易域购物车周期快照事实表'
    PARTITIONED BY (`dt` STRING)
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
    STORED AS ORC
    LOCATION '/warehouse/gmall/dwd/dwd_trade_cart_full/'
    TBLPROPERTIES ('orc.compress' = 'snappy');


-- 4.2 装载数据
insert overwrite table dwd_trade_cart_full partition (dt = '2020-06-14')
select id,
       user_id,
       sku_id,
       sku_name,
       sku_num
from ods_cart_info_full
where dt = '2020-06-14'
  and is_ordered = '0';
select *
from dwd_trade_cart_full;



-- 5、 交易域-交易流程-累积快照事实表
-- 数据如何进行分区？
--累计型快照事实表-侧重点在关联不同的业务数据，所以每一个业务行为的粒度应该最粗（最小粒度）

-- 5.1 建表语句
DROP TABLE IF EXISTS dwd_trade_trade_flow_acc;
CREATE EXTERNAL TABLE dwd_trade_trade_flow_acc
(
    `order_id`              STRING COMMENT '订单id',
    `user_id`               STRING COMMENT '用户id',
    `province_id`           STRING COMMENT '省份id',
    `order_date_id`         STRING COMMENT '下单日期id',
    `order_time`            STRING COMMENT '下单时间',
    `payment_date_id`       STRING COMMENT '支付日期id',
    `payment_time`          STRING COMMENT '支付时间',
    `finish_date_id`        STRING COMMENT '确认收货日期id',
    `finish_time`           STRING COMMENT '确认收货时间',
    `order_original_amount` DECIMAL(16, 2) COMMENT '下单原始价格',
    `order_activity_amount` DECIMAL(16, 2) COMMENT '下单活动优惠分摊',
    `order_coupon_amount`   DECIMAL(16, 2) COMMENT '下单优惠券优惠分摊',
    `order_total_amount`    DECIMAL(16, 2) COMMENT '下单最终价格分摊',
    `payment_amount`        DECIMAL(16, 2) COMMENT '支付金额'
) COMMENT '交易域交易流程累积快照事实表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dwd/dwd_trade_trade_flow_acc/'
    TBLPROPERTIES ('orc.compress' = 'snappy');


-- 5.2 装载数据
--5.2.1 首日装载
set hive.exec.dynamic.partition.mode=nonstrict;
insert overwrite table dwd_trade_trade_flow_acc partition (dt)
select oi.id                                    order_id,
       user_id,
       province_id,
       date_format(create_time, 'yyyy-MM-dd')   order_date_id,
       create_time                              order_time,
       date_format(callback_time, 'yyyy-MM-dd') payment_date_id,
       callback_time                            payment_time,
       date_format(operate_time, 'yyyy-MM-dd')  finish_date_id,
       operate_time                             finish_time,
       original_total_amount,
       activity_reduce_amount,
       coupon_reduce_amount,
       total_amount,
       nvl(payment_amount, 0.0)                 payment_amount,
       nvl(date_format(operate_time, 'yyyy-MM-dd'), '9999-12-31')
from (
         select data.id,
                data.user_id,
                data.province_id,
                data.create_time,
                data.original_total_amount,
                data.activity_reduce_amount,
                data.coupon_reduce_amount,
                data.total_amount
         from ods_order_info_inc
         where dt = '2020-06-14'
           and type = 'bootstrap-insert'
     ) oi
         left join
     (
         select data.order_id,
                data.callback_time,
                data.total_amount payment_amount
         from ods_payment_info_inc
         where dt = '2020-06-14'
           and type = 'bootstrap-insert'
           and data.payment_status = '1602' -- 1602 支付成功
     ) pay_info on oi.id = pay_info.order_id
         left join
     (
         select data.order_id,
                data.operate_time
         from ods_order_status_log_inc
         where dt = '2020-06-14'
           and type = 'bootstrap-insert'
           and data.order_status = '1004' -- 1004 已完成
     ) log on oi.id = log.order_id;

--5.2.2 每日装载
set hive.exec.dynamic.partition.mode=nonstrict;
insert overwrite table dwd_trade_trade_flow_acc partition (dt)
select oi.order_id,
       user_id,
       province_id,
       order_date_id,
       order_time,
       nvl(oi.payment_date_id, pi.payment_date_id),
       nvl(oi.payment_time, pi.payment_time),
       nvl(oi.finish_date_id, log.finish_date_id),
       nvl(oi.finish_time, log.finish_time),
       order_original_amount,
       order_activity_amount,
       order_coupon_amount,
       order_total_amount,
       nvl(oi.payment_amount, pi.payment_amount),
       nvl(nvl(oi.finish_time, log.finish_time), '9999-12-31')
from (
         select order_id,
                user_id,
                province_id,
                order_date_id,
                order_time,
                payment_date_id,
                payment_time,
                finish_date_id,
                finish_time,
                order_original_amount,
                order_activity_amount,
                order_coupon_amount,
                order_total_amount,
                payment_amount
         from dwd_trade_trade_flow_acc
         where dt = '9999-12-31'
         union all
         select data.id,
                data.user_id,
                data.province_id,
                date_format(data.create_time, 'yyyy-MM-dd') order_date_id,
                data.create_time                            order_time,
                null                                        payment_date_id,
                null                                        payment_time,
                null                                        finish_date_id,
                null                                        finish_time,
                data.original_total_amount,
                data.activity_reduce_amount,
                data.coupon_reduce_amount,
                data.total_amount,
                null                                        payment_amount
         from ods_order_info_inc
         where dt = '2020-06-15'
           and type = 'insert'
     ) oi
         left join (
    select data.order_id,
           date_format(data.callback_time, 'yyyy-MM-dd') payment_date_id,
           data.callback_time                            payment_time,
           data.total_amount                             payment_amount
    from ods_payment_info_inc
    where dt = '2020-06-15'
      and type = 'update'
      and array_contains(map_keys(old), 'payment_status')
      and data.payment_status = '1602') pi on oi.order_id = pi.order_id
         left join (
    select data.order_id,
           data.operate_time                            finish_time,
           date_format(data.operate_time, 'yyyy-MM-dd') finish_date_id
    from ods_order_status_log_inc
    where dt = '2020-06-15'
      and type = 'insert'
      and data.order_status = '1004'
) log on oi.order_id = log.order_id;



-- 6、工具域-优惠券使用(支付)-事务事实表

-- 6.1 建表语句
drop table if exists dwd_tool_coupon_used_inc;
CREATE EXTERNAL TABLE dwd_tool_coupon_used_inc
(
    `id`           STRING COMMENT '编号',
    `coupon_id`    STRING COMMENT '优惠券ID',
    `user_id`      STRING COMMENT 'user_id',
    `order_id`     STRING COMMENT 'order_id',
    `date_id`      STRING COMMENT '日期ID',
    `payment_time` STRING COMMENT '使用下单时间'
) COMMENT '优惠券使用支付事务事实表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dwd/dwd_tool_coupon_used_inc/'
    TBLPROPERTIES ("orc.compress" = "snappy");

-- 6.2 装载数据
--6.2.1 首日装载
insert overwrite table dwd_tool_coupon_used_inc partition (dt)
select data.id,-- STRING COMMENT '编号',
       data.coupon_id,-- STRING COMMENT '优惠券ID',
       data.user_id,-- STRING COMMENT 'user_id',
       data.order_id,-- STRING COMMENT 'order_id',
       date_format(data.used_time, 'yyyy-MM-dd') date_id,-- STRING COMMENT '日期ID',
       data.used_time                            payment_time,-- STRING COMMENT '使用下单时间'
       date_format(data.used_time, 'yyyy-MM-dd')
from ods_coupon_use_inc
where dt = '2020-06-14'
  and type = 'bootstrap-insert'
  and data.coupon_status = '1403';

--6.2.2 每日装载
insert overwrite table dwd_tool_coupon_used_inc partition (dt = '2020-06-15')
select data.id,-- STRING COMMENT '编号',
       data.coupon_id,-- STRING COMMENT '优惠券ID',
       data.user_id,-- STRING COMMENT 'user_id',
       data.order_id,-- STRING COMMENT 'order_id',
       date_format(data.used_time, 'yyyy-MM-dd') date_id,-- STRING COMMENT '日期ID',
       data.used_time                            payment_time -- STRING COMMENT '使用下单时间'
from ods_coupon_use_inc
where dt = '2020-06-15'
  and type = 'update'
  and data.used_time is not null;



-- 7、互动域-收藏商品-事务事实表
-- 7.1 建表语句
DROP TABLE IF EXISTS dwd_interaction_favor_add_inc;
CREATE EXTERNAL TABLE dwd_interaction_favor_add_inc
(
    `id`          STRING COMMENT '编号',
    `user_id`     STRING COMMENT '用户id',
    `sku_id`      STRING COMMENT 'sku_id',
    `date_id`     STRING COMMENT '日期id',
    `create_time` STRING COMMENT '收藏时间'
) COMMENT '收藏事实表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dwd/dwd_interaction_favor_add_inc/'
    TBLPROPERTIES ("orc.compress" = "snappy");
-- 7.2 装载数据
-- 7.2.1 首日装载
set hive.exec.dynamic.partition.mode=nonstrict;
insert overwrite table dwd_interaction_favor_add_inc partition (dt)
select data.`id`,--STRING COMMENT '编号',
       data.`user_id`,--STRING COMMENT '用户id',
       data.`sku_id`,--STRING COMMENT 'sku_id',
       date_format(data.create_time, 'yyyy-MM-dd') `date_id`,--STRING COMMENT '日期id',
       data.create_time                            `create_time`,--STRING COMMENT '收藏时间'
       date_format(data.create_time, 'yyyy-MM-dd')
from ods_favor_info_inc
where dt = '2020-06-14'
  and type = 'bootstrap-insert';

-- 7.2.2 每日装载
insert overwrite table dwd_interaction_favor_add_inc partition (dt = '2020-06-15')
select data.`id`,--STRING COMMENT '编号',
       data.`user_id`,--STRING COMMENT '用户id',
       data.`sku_id`,--STRING COMMENT 'sku_id',
       date_format(data.create_time, 'yyyy-MM-dd') `date_id`,--STRING COMMENT '日期id',
       data.create_time                            `create_time` --STRING COMMENT '收藏时间'
from ods_favor_info_inc
where dt = '2020-06-15'
  and type = 'insert';



-- 8、流量域-页面浏览-事务事实表
-- 8.1 建表语句
DROP TABLE IF EXISTS dwd_traffic_page_view_inc;
CREATE EXTERNAL TABLE dwd_traffic_page_view_inc
(
    `province_id`    STRING COMMENT '省份id',
    `brand`          STRING COMMENT '手机品牌',
    `channel`        STRING COMMENT '渠道',
    `is_new`         STRING COMMENT '是否首次启动',
    `model`          STRING COMMENT '手机型号',
    `mid_id`         STRING COMMENT '设备id',
    `operate_system` STRING COMMENT '操作系统',
    `user_id`        STRING COMMENT '会员id',
    `version_code`   STRING COMMENT 'app版本号',
    `page_item`      STRING COMMENT '目标id ',
    `page_item_type` STRING COMMENT '目标类型',
    `last_page_id`   STRING COMMENT '上页类型',
    `page_id`        STRING COMMENT '页面ID ',
    `source_type`    STRING COMMENT '来源类型',
    `date_id`        STRING COMMENT '日期id',
    `view_time`      STRING COMMENT '跳入时间',
    `session_id`     STRING COMMENT '所属会话id',
    `during_time`    BIGINT COMMENT '持续时间毫秒'
) COMMENT '页面日志表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dwd/dwd_traffic_page_view_inc'
    TBLPROPERTIES ('orc.compress' = 'snappy');


-- 8.2 装载语句
-- {"ar":"110000","ba":"Xiaomi","ch":"360","is_new":"0","md":"Xiaomi 10 Pro ","mid":"mid_16345","os":"Android 11.0","uid":"97","vc":"v2.1.111"}
-- {"during_time":"11546","item":null,"item_type":null,"last_page_id":null,"page_id":"home","source_type":null}
set hive.cbo.enable=false;
insert overwrite table dwd_traffic_page_view_inc partition (dt = '2020-06-14')
select `province_id`,--STRING COMMENT '省份id',
       `brand`,--STRING COMMENT '手机品牌',
       `channel`,--STRING COMMENT '渠道',
       `is_new`,--STRING COMMENT '是否首次启动',
       `model`,--STRING COMMENT '手机型号',
       `mid_id`,--STRING COMMENT '设备id',
       `operate_system`,--STRING COMMENT '操作系统',
       `user_id`,--STRING COMMENT '会员id',
       `version_code`,--STRING COMMENT 'app版本号',
       `page_item`,--STRING COMMENT '目标id ',
       `page_item_type`,--STRING COMMENT '目标类型',
       `last_page_id`,--STRING COMMENT '上页类型',
       `page_id`,--STRING COMMENT '页面ID ',
       `source_type`,--STRING COMMENT '来源类型',
       date_format(from_utc_timestamp(ts, 'GMT+8'), 'yyyy-MM-dd')                                   `date_id`,--STRING COMMENT '日期id',
       date_format(from_utc_timestamp(ts, 'GMT+8'), 'yyyy-MM-dd HH:mm:ss')                          `view_time`,--STRING COMMENT '跳入时间',
       concat(mid_id, '-', last_value(session_target, true) over (partition by mid_id order by ts)) `session_id`,--STRING COMMENT '所属会话id',
       `during_time` --BIGINT COMMENT '持续时间毫秒'

from (select common.ar                                 area_code,
             common.ba                                 brand,
             common.ch                                 channel,
             common.is_new                             is_new,
             common.md                                 model,
             common.mid                                mid_id,
             common.os                                 operate_system,
             common.uid                                user_id,
             common.vc                                 version_code,
             page.during_time,
             page.item                                 page_item,
             page.item_type                            page_item_type,
             page.last_page_id,
             page.page_id,
             page.source_type,
             ts,
             `if`(page.last_page_id is null, ts, null) session_target
      from ods_log_inc
      where dt = '2020-06-14'
        and page is not null) log
         left join
     (select area_code,
             id province_id
      from ods_base_province_full
      where dt = '2020-06-14'
     ) pro
     on pro.area_code = log.area_code



-- 9、用户域-用户注册-事务事实表
-- 9.1 建表语句
DROP TABLE IF EXISTS dwd_user_register_inc;
CREATE EXTERNAL TABLE dwd_user_register_inc
(
    `user_id`        STRING COMMENT '用户ID',
    `date_id`        STRING COMMENT '日期ID',
    `create_time`    STRING COMMENT '注册时间',
    `channel`        STRING COMMENT '应用下载渠道',
    `province_id`    STRING COMMENT '省份id',
    `version_code`   STRING COMMENT '应用版本',
    `mid_id`         STRING COMMENT '设备id',
    `brand`          STRING COMMENT '设备品牌',
    `model`          STRING COMMENT '设备型号',
    `operate_system` STRING COMMENT '设备操作系统'
) COMMENT '用户域用户注册事务事实表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dwd/dwd_user_register_inc/'
    TBLPROPERTIES ("orc.compress" = "snappy");


-- 9.2.1 首日数据加载
set hive.exec.dynamic.partition.mode=nonstrict;
with ui as (
-- {"id":null,"login_name":null,"nick_name":null,"passwd":null,"name":null,"phone_num":null,"email":null,"head_img":null,"user_level":null,"birthday":null,"gender":null,"create_time":null,"operate_time":null,"status":null}
    select data.id                                              user_id,
           date_format(data.create_time, 'yyyy-MM-dd')          date_id,
           date_format(data.create_time, 'yyyy-MM-dd HH:mm:ss') create_time
    from ods_user_info_inc
    where dt = '2020-06-14'
      and type = 'bootstrap-insert'
),
     log as (
--{"ar":"110000","ba":"iPhone","ch":"Appstore","is_new":"1","md":"iPhone X","mid":"mid_160098","os":"iOS 13.3.1","uid":"12","vc":"v2.1.134"}
--{"during_time":"6927","item":null,"item_type":null,"last_page_id":"login","page_id":"register","source_type":null}
         select common.ar  area_code,
                common.ba  brand,
                common.ch  channel,
                common.md  model,
                common.mid mid_id,
                common.os  operate_system,
                common.uid user_id,
                common.vc  version_code
         from ods_log_inc
         where dt = '2020-06-14'
           and page is not null
           and page.page_id = 'register'
           and common.uid is not null
     ),
     pro as (
         select area_code,
                id province_id
         from ods_base_province_full
         where dt = '2020-06-14'
     )
insert
overwrite
table
dwd_user_register_inc
partition
(
dt
)
select ui.`user_id`,    --STRING COMMENT '用户ID',
       `date_id`,       --STRING COMMENT '日期ID',
       `create_time`,   --STRING COMMENT '注册时间',
       `channel`,       --STRING COMMENT '应用下载渠道',
       `province_id`,   --STRING COMMENT '省份id',
       `version_code`,  --STRING COMMENT '应用版本',
       `mid_id`,        --STRING COMMENT '设备id',
       `brand`,         --STRING COMMENT '设备品牌',
       `model`,         --STRING COMMENT '设备型号',
       `operate_system`,--STRING COMMENT '设备操作系统'
       date_id
from ui
         left join log on ui.user_id = log.user_id
         left join pro on log.area_code = pro.area_code;

-- 9.2.2 每日数据加载
with ui as (
-- {"id":null,"login_name":null,"nick_name":null,"passwd":null,"name":null,"phone_num":null,"email":null,"head_img":null,"user_level":null,"birthday":null,"gender":null,"create_time":null,"operate_time":null,"status":null}
    select data.id                                              user_id,
           date_format(data.create_time, 'yyyy-MM-dd')          date_id,
           date_format(data.create_time, 'yyyy-MM-dd HH:mm:ss') create_time
    from ods_user_info_inc
    where dt = '2020-06-15'
      and type = 'insert'
),
     log as (
--{"ar":"110000","ba":"iPhone","ch":"Appstore","is_new":"1","md":"iPhone X","mid":"mid_160098","os":"iOS 13.3.1","uid":"12","vc":"v2.1.134"}
--{"during_time":"6927","item":null,"item_type":null,"last_page_id":"login","page_id":"register","source_type":null}
         select common.ar  area_code,
                common.ba  brand,
                common.ch  channel,
                common.md  model,
                common.mid mid_id,
                common.os  operate_system,
                common.uid user_id,
                common.vc  version_code
         from ods_log_inc
         where dt = '2020-06-15'
           and page is not null
           and page.page_id = 'register'
           and common.uid is not null
     ),
     pro as (
         select area_code,
                id province_id
         from ods_base_province_full
         where dt = '2020-06-15'
     )
insert
overwrite
table
dwd_user_register_inc
partition
(
dt = '2020-06-15'
)
select ui.`user_id`,    --STRING COMMENT '用户ID',
       `date_id`,       --STRING COMMENT '日期ID',
       `create_time`,   --STRING COMMENT '注册时间',
       `channel`,       --STRING COMMENT '应用下载渠道',
       `province_id`,   --STRING COMMENT '省份id',
       `version_code`,  --STRING COMMENT '应用版本',
       `mid_id`,        --STRING COMMENT '设备id',
       `brand`,         --STRING COMMENT '设备品牌',
       `model`,         --STRING COMMENT '设备型号',
       `operate_system` --STRING COMMENT '设备操作系统'
from ui
         left join log on ui.user_id = log.user_id
         left join pro on log.area_code = pro.area_code;




-- 10、用户域用户登录事务事实表
-- 10.1 建表语句

DROP TABLE IF EXISTS dwd_user_login_inc;
CREATE EXTERNAL TABLE dwd_user_login_inc
(
    `user_id`        STRING COMMENT '用户ID',
    `date_id`        STRING COMMENT '日期ID',
    `login_time`     STRING COMMENT '登录时间',
    `channel`        STRING COMMENT '应用下载渠道',
    `province_id`    STRING COMMENT '省份id',
    `version_code`   STRING COMMENT '应用版本',
    `mid_id`         STRING COMMENT '设备id',
    `brand`          STRING COMMENT '设备品牌',
    `model`          STRING COMMENT '设备型号',
    `operate_system` STRING COMMENT '设备操作系统'
) COMMENT '用户域用户登录事务事实表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dwd/dwd_user_login_inc/'
    TBLPROPERTIES ("orc.compress" = "snappy");


-- 10.2 装载数据
--{"ar":"110000","ba":"iPhone","ch":"Appstore","is_new":"1","md":"iPhone X","mid":"mid_160098","os":"iOS 13.3.1","uid":"12","vc":"v2.1.134"}
--{"during_time":"11546","item":null,"item_type":null,"last_page_id":null,"page_id":"home","source_type":null
insert overwrite table dwd_user_login_inc partition (dt='2020-06-14')
select user_id,
       date_format(from_utc_timestamp(ts, 'GMT+8'), 'yyyy-MM-dd')          date_id,
       date_format(from_utc_timestamp(ts, 'GMT+8'), 'yyyy-MM-dd HH:mm:ss') login_time,
       channel,
       province_id,
       version_code,
       mid_id,
       brand,
       model,
       operate_system
from (
         select user_id,
                mid_id,
                channel,
                area_code,
                version_code,
                brand,
                model,
                operate_system,
                ts,
                session_id,
                rk
         from (
                  select user_id,
                         mid_id,
                         channel,
                         area_code,
                         version_code,
                         brand,
                         model,
                         operate_system,
                         ts,
                         session_id,
                         row_number() over (partition by session_id order by ts) rk
                  from (
                           select user_id,
                                  mid_id,
                                  channel,
                                  area_code,
                                  version_code,
                                  brand,
                                  model,
                                  operate_system,
                                  ts,
                                  concat(mid_id, '-',
                                         last_value(session_start_point, true)
                                                    over (partition by mid_id order by ts)) session_id
                           from (
                                    select common.mid                                mid_id,
                                           common.ch                                 channel,
                                           common.ar                                 area_code,
                                           common.vc                                 version_code,
                                           common.ba                                 brand,
                                           common.md                                 model,
                                           common.os                                 operate_system,
                                           common.uid                                user_id,
                                           ts,
                                           `if`(page.last_page_id is null, ts, null) session_start_point
                                    from ods_log_inc
                                    where dt = '2020-06-14'
                                      and page is not null
                                ) t1
                       ) t2
                  where t2.user_id is not null
              ) t3
         where rk = 1
     ) r4
         left join
     (select id province_id,
             area_code
      from ods_base_province_full
         where dt='2020-06-14'
     ) pro on pro.area_code = r4.area_code;














