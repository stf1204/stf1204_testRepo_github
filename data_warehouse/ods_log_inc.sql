-- 创建ods_log_inc 表

-- 1、内部表、外部表
-- 2、回顾 hive中的复杂数据结构
    -- 2.1 Array
    -- 2.2 Map （k-v对）
    -- 2.3 struct（k-v对）
-- 3、 分区表 partition（表的虚拟列）
-- 4、 JSON表 （数据为JSON格式，表还是文本文件）
-- 5、 存储格式 gzip
-- 6、 位置 location

DROP TABLE IF EXISTS ods_log_inc;
CREATE EXTERNAL TABLE ods_log_inc
(
    `common`   STRUCT<ar :STRING, ba :STRING, ch :STRING, is_new :STRING, md :STRING, mid :STRING, os :STRING, uid
                      :STRING, vc :STRING> COMMENT '公共信息',
    `page`     STRUCT<during_time :STRING, item :STRING, item_type :STRING, last_page_id :STRING, page_id :STRING,
                      source_type :STRING> COMMENT '页面信息',
    `actions`  ARRAY<STRUCT<action_id:STRING,item:STRING,item_type:STRING,ts:BIGINT>> COMMENT '动作信息',
    `displays` ARRAY<STRUCT<display_type :STRING,item :STRING,item_type :STRING,`order` :STRING,pos_id
                            :STRING>> COMMENT '曝光信息',
    `start`    STRUCT<entry :STRING,loading_time :BIGINT,open_ad_id :BIGINT,open_ad_ms :BIGINT,open_ad_skip_ms
                      :BIGINT> COMMENT '启动信息',
    `err`      STRUCT<error_code:BIGINT,msg:STRING> COMMENT '错误信息',
    `ts`       BIGINT COMMENT '时间戳'
) COMMENT '活动信息表'
    PARTITIONED BY (`dt` STRING)
    ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.JsonSerDe'
    LOCATION '/warehouse/gmall/ods/ods_log_inc/';



-- 加载ods-log-inc数据
load data inpath '/origin_data/gmall/log/topic_log/2020-06-14' into table ods_log_inc partition(dt='2020-06-14');

-- 查询数据
select * from ods_log_inc;
select common.ar,common.ba from ods_log_inc;


