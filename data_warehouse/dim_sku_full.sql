-- 维度表
-- 主维表 & 相关维表
-- 都是业务中的表，如果维度表是用户，那么主维表就是业务系统中的用户表，和用户信息相关的表就是相关维表
--ORC & Snappy


-- 1、商品维度表：dim_sku_full
-- 确定表字段（维度属性）？
-- 主维表 - 商品信息表 （Sku,Spu）
-- 存储策略 - 每日全量

DROP TABLE IF EXISTS dim_sku_full;
CREATE EXTERNAL TABLE dim_sku_full
(
    `id`                   string COMMENT 'sku_id',
    `price`                decimal(16, 2) COMMENT '商品价格',
    `sku_name`             string COMMENT '商品名称',
    `sku_desc`             STRING COMMENT '商品描述',
    `weight`               DECIMAL(16, 2) COMMENT '重量',
    `is_sale`              BOOLEAN COMMENT '是否在售',
    `spu_id`               string COMMENT 'spu编号',
    `spu_name`             string COMMENT 'spu名称',
    `category3_id`         string COMMENT '三级分类id',
    `category3_name`       string COMMENT '三级分类名称',
    `category2_id`         string COMMENT '二级分类id',
    `category2_name`       string COMMENT '二级分类名称',
    `category1_id`         string COMMENT '一级分类id',
    `category1_name`       string COMMENT '一级分类名称',
    `tm_id`                string COMMENT '品牌id',
    `tm_name`              string COMMENT '品牌名称',
    `sku_attr_values`      ARRAY<STRUCT<attr_id :STRING,value_id :STRING,attr_name :STRING,value_name
                                        :STRING>> COMMENT '平台属性',
    `sku_sale_attr_values` ARRAY<STRUCT<sale_attr_id :STRING,sale_attr_value_id :STRING,sale_attr_name :STRING,sale_attr_value_name
                                        :STRING>> COMMENT '销售属性',
    `create_time`          string COMMENT '创建时间'
) COMMENT '商品维度表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dim/dim_sku_full/'
    TBLPROPERTIES ('orc.compress' = 'snappy');

show tables;

-- 数据加载
-- 数据来源：ODS - 8张表
-- JSON
-- CTE 语法（公共表 表达式）：将查询结果集作为一个临时表使用，但是只对当前SQL有效
with sku as
         (
             select id,
                    price,
                    sku_name,
                    sku_desc,
                    weight,
                    is_sale,
                    spu_id,
                    category3_id,
                    tm_id,
                    create_time
             from ods_sku_info_full
             where dt = '2020-06-14'
         ),
     spu as
         (
             select id,
                    spu_name
             from ods_spu_info_full
             where dt = '2020-06-14'
         ),
     c3 as
         (
             select id,
                    name,
                    category2_id
             from ods_base_category3_full
             where dt = '2020-06-14'
         ),
     c2 as
         (
             select id,
                    name,
                    category1_id
             from ods_base_category2_full
             where dt = '2020-06-14'
         ),
     c1 as
         (
             select id,
                    name
             from ods_base_category1_full
             where dt = '2020-06-14'
         ),
     tm as
         (
             select id,
                    tm_name
             from ods_base_trademark_full
             where dt = '2020-06-14'
         ),
     attr as
         (
             select sku_id,
                    collect_set(
                            named_struct('attr_id', attr_id, 'value_id', value_id, 'attr_name', attr_name, 'value_name',
                                         value_name)) attrs
             from ods_sku_attr_value_full
             where dt = '2020-06-14'
             group by sku_id
         ),
     sale_attr as
         (
             select sku_id,
                    collect_set(named_struct('sale_attr_id', sale_attr_id, 'sale_attr_value_id', sale_attr_value_id,
                                             'sale_attr_name', sale_attr_name, 'sale_attr_value_name',
                                             sale_attr_value_name)) sale_attrs
             from ods_sku_sale_attr_value_full
             where dt = '2020-06-14'
             group by sku_id
         )
insert
overwrite
table
dim_sku_full
partition
(
dt = '2020-06-14'
)
select sku.id,
       sku.price,
       sku.sku_name,
       sku.sku_desc,
       sku.weight,
       sku.is_sale,
       sku.spu_id,
       spu.spu_name,
       sku.category3_id,
       c3.name,
       c3.category2_id,
       c2.name,
       c2.category1_id,
       c1.name,
       sku.tm_id,
       tm.tm_name,
       attr.attrs,
       sale_attr.sale_attrs,
       sku.create_time
from sku
         join spu on sku.spu_id = spu.id
         join c3 on sku.category3_id = c3.id
         join c2 on c3.category2_id = c2.id
         join c1 on c2.category1_id = c1.id
         left join tm on sku.tm_id = tm.id
         left join attr on sku.id = attr.sku_id
         left join sale_attr on sku.id = sale_attr.sku_id;



-- 2、优惠卷维度表：

-- 建表语句
-- 主维表：业务系统的优惠卷表
DROP table if exists dim_coupon_full;
create external table dim_coupon_full
(
    `id`               STRING COMMENT '购物券编号',
    `coupon_name`      STRING COMMENT '购物券名称',
    `coupon_type_code` STRING COMMENT '购物券类型编码',
    `coupon_type_name` STRING COMMENT '购物券类型名称',
    `condition_amount` DECIMAL(16, 2) COMMENT '满额数',
    `condition_num`    BIGINT COMMENT '满件数',
    `activity_id`      STRING COMMENT '活动编号',
    `benefit_amount`   DECIMAL(16, 2) COMMENT '减金额',
    `benefit_discount` DECIMAL(16, 2) COMMENT '折扣',
    `benefit_rule`     STRING COMMENT '优惠规则:满元*减*元，满*件打*折',
    `create_time`      STRING COMMENT '创建时间',
    `range_type_code`  STRING COMMENT '优惠范围类型编码',
    `range_type_name`  STRING COMMENT '优惠范围类型名称',
    `limit_num`        BIGINT COMMENT '最多领取次数',
    `taken_count`      BIGINT COMMENT '已领取次数',
    `start_time`       STRING COMMENT '可以领取的开始日期',
    `end_time`         STRING COMMENT '可以领取的结束日期',
    `operate_time`     STRING COMMENT '修改时间',
    `expire_time`      STRING COMMENT '过期时间'
) COMMENT '优惠卷维度表'
    partitioned by (`dt` string)
    stored as orc
    location '/warehouse/gmall/dim/dim_coupon_full/'
    tblproperties ('orc.compress' = 'snappy');


-- 装载数据
insert overwrite table dim_coupon_full partition (dt = '2020-06-14')
select id,
       coupon_name,
       coupon_type,
       coupon_dic.dic_name,
       condition_amount,
       condition_num,
       activity_id,
       benefit_amount,
       benefit_discount,
       case coupon_type
           when '3201' then concat('满', condition_amount, '元减', benefit_amount, '元')
           when '3202' then concat('满', condition_num, '件打', 10 * (1 - benefit_discount), '折')
           when '3203' then concat('减', benefit_amount, '元')
           end benefit_rule,
       create_time,
       range_type,
       range_dic.dic_name,
       limit_num,
       taken_count,
       start_time,
       end_time,
       operate_time,
       expire_time
from (
         select id,
                coupon_name,
                coupon_type,
                condition_amount,
                condition_num,
                activity_id,
                benefit_amount,
                benefit_discount,
                create_time,
                range_type,
                limit_num,
                taken_count,
                start_time,
                end_time,
                operate_time,
                expire_time
         from ods_coupon_info_full
         where dt = '2020-06-14'
     ) ci
         left join
     (
         select dic_code,
                dic_name
         from ods_base_dic_full
         where dt = '2020-06-14'
           and parent_code = '32'
     ) coupon_dic
     on ci.coupon_type = coupon_dic.dic_code
         left join
     (
         select dic_code,
                dic_name
         from ods_base_dic_full
         where dt = '2020-06-14'
           and parent_code = '33'
     ) range_dic
     on ci.range_type = range_dic.dic_code;



-- 3、活动维度表
-- 主维表：活动规则（尽量粒度最细）
DROP TABLE IF EXISTS dim_activity_full;
CREATE EXTERNAL TABLE dim_activity_full
(
    `activity_rule_id`   STRING COMMENT '活动规则ID',
    `activity_id`        STRING COMMENT '活动ID',
    `activity_name`      STRING COMMENT '活动名称',
    `activity_type_code` STRING COMMENT '活动类型编码',
    `activity_type_name` STRING COMMENT '活动类型名称',
    `activity_desc`      STRING COMMENT '活动描述',
    `start_time`         STRING COMMENT '开始时间',
    `end_time`           STRING COMMENT '结束时间',
    `create_time`        STRING COMMENT '创建时间',
    `condition_amount`   DECIMAL(16, 2) COMMENT '满减金额',
    `condition_num`      BIGINT COMMENT '满减件数',
    `benefit_amount`     DECIMAL(16, 2) COMMENT '优惠金额',
    `benefit_discount`   DECIMAL(16, 2) COMMENT '优惠折扣',
    `benefit_rule`       STRING COMMENT '优惠规则',
    `benefit_level`      STRING COMMENT '优惠级别'
) COMMENT '活动信息表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dim/dim_activity_full/'
    TBLPROPERTIES ('orc.compress' = 'snappy');


-- 装载数据
insert overwrite table dim_activity_full partition (dt = '2020-06-14')
select rule.id     activity_rule_id
     , activity_id
     , activity_name
     , activity_type
     , at.dic_name activity_type_name
     , activity_desc
     , start_time
     , end_time
     , create_time
     , condition_amount
     , condition_num
     , benefit_amount
     , benefit_discount
     , case rule.activity_type
           when '3101' then concat('满', condition_amount, '元减', benefit_amount, '元')
           when '3102' then concat('满', condition_num, '件打', 10 * (1 - benefit_discount), '折')
           when '3103' then concat('打', 10 * (1 - benefit_discount), '折')
    end            benefit_rule
     , benefit_level
from (
         select id,
                activity_id,
                activity_type,
                condition_amount,
                condition_num,
                benefit_amount,
                benefit_discount,
                benefit_level
         from ods_activity_rule_full
         where dt = '2020-06-14'
     ) rule
         left join (
    select id,
           activity_name,
           activity_desc,
           start_time,
           end_time,
           create_time
    from ods_activity_info_full
    where dt = '2020-06-14'
) info on rule.activity_id = info.id
         left join (
    select dic_code,
           dic_name
    from ods_base_dic_full
    where dt = '2020-06-14'
      and parent_code = '31'
) at on rule.activity_type = at.dic_code;



-- 4、 地区维度表
-- 主维表：省份表
DROP TABLE IF EXISTS dim_province_full;
CREATE EXTERNAL TABLE dim_province_full
(
    `id`            STRING COMMENT 'id',
    `province_name` STRING COMMENT '省市名称',
    `area_code`     STRING COMMENT '地区编码',
    `iso_code`      STRING COMMENT '旧版ISO-3166-2编码，供可视化使用',
    `iso_3166_2`    STRING COMMENT '新版IOS-3166-2编码，供可视化使用',
    `region_id`     STRING COMMENT '地区id',
    `region_name`   STRING COMMENT '地区名称'
) COMMENT '地区维度表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dim/dim_province_full/'
    TBLPROPERTIES ('orc.compress' = 'snappy');


-- 装载数据
insert overwrite table dim_province_full partition (dt = '2020-06-14')
select province.id,
       province.name,
       province.area_code,
       province.iso_code,
       province.iso_3166_2,
       region_id,
       region_name
from (
         select id,
                name,
                region_id,
                area_code,
                iso_code,
                iso_3166_2
         from ods_base_province_full
         where dt = '2020-06-14'
     ) province
         left join
     (
         select id,
                region_name
         from ods_base_region_full
         where dt = '2020-06-14'
     ) region
     on province.region_id = region.id;



-- 5、日期维度表
-- 日期表的数据不是同步采集过来的，而是上传文件来的
-- 文件一般为文本文件，而表采用ORC的格式，所以不匹配
-- 可以采用中间表中转一下：text => text table => select => insert => orc table

-- 建表语句
DROP TABLE IF EXISTS dim_date;
CREATE EXTERNAL TABLE dim_date
(
    `date_id`    STRING COMMENT '日期ID',
    `week_id`    STRING COMMENT '周ID,一年中的第几周',
    `week_day`   STRING COMMENT '周几',
    `day`        STRING COMMENT '每月的第几天',
    `month`      STRING COMMENT '一年中的第几月',
    `quarter`    STRING COMMENT '一年中的第几季度',
    `year`       STRING COMMENT '年份',
    `is_workday` STRING COMMENT '是否是工作日',
    `holiday_id` STRING COMMENT '节假日'
) COMMENT '时间维度表'
    STORED AS ORC
    LOCATION '/warehouse/gmall/dim/dim_date/'
    TBLPROPERTIES ('orc.compress' = 'snappy');


-- 创建临时表
DROP TABLE IF EXISTS tmp_dim_date_info;
CREATE EXTERNAL TABLE tmp_dim_date_info
(
    `date_id`    STRING COMMENT '日',
    `week_id`    STRING COMMENT '周ID',
    `week_day`   STRING COMMENT '周几',
    `day`        STRING COMMENT '每月的第几天',
    `month`      STRING COMMENT '第几月',
    `quarter`    STRING COMMENT '第几季度',
    `year`       STRING COMMENT '年',
    `is_workday` STRING COMMENT '是否是工作日',
    `holiday_id` STRING COMMENT '节假日'
) COMMENT '时间维度表'
    ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'
    LOCATION '/warehouse/gmall/tmp/tmp_dim_date_info/';


-- 装载数据
insert overwrite table dim_date
select *
from tmp_dim_date_info;


-- 6、用户维度表
DROP TABLE IF EXISTS dim_user_zip;
CREATE EXTERNAL TABLE dim_user_zip
(
    `id`           STRING COMMENT '用户id',
    `login_name`   STRING COMMENT '用户名称',
    `nick_name`    STRING COMMENT '用户昵称',
    `name`         STRING COMMENT '用户姓名',
    `phone_num`    STRING COMMENT '手机号码',
    `email`        STRING COMMENT '邮箱',
    `user_level`   STRING COMMENT '用户等级',
    `birthday`     STRING COMMENT '生日',
    `gender`       STRING COMMENT '性别',
    `create_time`  STRING COMMENT '创建时间',
    `operate_time` STRING COMMENT '操作时间',
    `start_date`   STRING COMMENT '开始日期',
    `end_date`     STRING COMMENT '结束日期'
) COMMENT '用户表'
    PARTITIONED BY (`dt` STRING)
    STORED AS ORC
    LOCATION '/warehouse/gmall/dim/dim_user_zip/'
    TBLPROPERTIES ('orc.compress' = 'snappy');


-- 首日装载
insert overwrite table dim_user_zip partition (dt = '9999-12-31')
select data.id,
       data.login_name,
       data.nick_name,
       md5(data.name),
       md5(if(data.phone_num regexp '^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$',
              data.phone_num, null)),
       md5(if(data.email regexp '^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$', data.email, null)),
       data.user_level,
       data.birthday,
       data.gender,
       data.create_time,
       data.operate_time,
       '2020-06-14' start_date,
       '9999-12-31' end_date
from ods_user_info_inc
where dt = '2020-06-14'
  -- 过滤掉start和finish
  and type = 'bootstrap-insert';


-- 每日装载
set hive.exec.dynamic.partition.mode=nonstrict;
insert overwrite table dim_user_zip partition (dt)
select id,
       login_name,
       nick_name,
       name,
       phone_num,
       email,
       user_level,
       birthday,
       gender,
       create_time,
       operate_time,
       start_date,
       if(rn = 2, date_sub('2020-06-15', 1), end_date)     end_date,
       if(rn = 1, '9999-12-31', date_sub('2020-06-15', 1)) dt
from (
         select id,
                login_name,
                nick_name,
                name,
                phone_num,
                email,
                user_level,
                birthday,
                gender,
                create_time,
                operate_time,
                start_date,
                end_date,
                row_number() over (partition by id order by start_date desc) rn
         from (
             // 历史有效用户
                  select id,
                         login_name,
                         nick_name,
                         name,
                         phone_num,
                         email,
                         user_level,
                         birthday,
                         gender,
                         create_time,
                         operate_time,
                         start_date,
                         end_date
                  from dim_user_zip
                  where dt = '9999-12-31'
                  union
                  // 每日变更用户数据
                  select id,
                         login_name,
                         nick_name,
                         md5(name)                                                                                name,
                         md5(if(phone_num regexp
                                '^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$',
                                phone_num,
                                null))                                                                            phone_num,
                         md5(if(email regexp '^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$', email, null)) email,
                         user_level,
                         birthday,
                         gender,
                         create_time,
                         operate_time,
                         '2020-06-15'                                                                             start_date,
                         '9999-12-31'                                                                             end_date
                  from (
                      // 拿出最后一次修改的记录
                           select data.id,
                                  data.login_name,
                                  data.nick_name,
                                  data.name,
                                  data.phone_num,
                                  data.email,
                                  data.user_level,
                                  data.birthday,
                                  data.gender,
                                  data.create_time,
                                  data.operate_time,
                                  row_number() over (partition by data.id order by ts desc) rn
                           from ods_user_info_inc
                           where dt = '2020-06-15'
                       ) t1
                  where rn = 1
              ) t2
     ) t3;


