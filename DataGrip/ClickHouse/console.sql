show databases;

use default;

select array(1,2,3);

select [1,3,5];

select 1-0.9;

--  order by 必选
--  primary key 非必选
--   partition by  非必选
create table t_order_mt(
    uid UInt32,
    sku_id String,
    total_amount Decimal(16,2),
    create_time  Datetime
 ) engine=MergeTree
 partition by toYYYYMMDD(create_time)
 primary key (uid)
 order by (uid,sku_id);

insert into  t_order_mt
Values
(101,'sku_001',1000.00,'2020-06-01 12:00:00') ,
(102,'sku_002',2000.00,'2020-06-01 11:00:00'),
(102,'sku_004',2500.00,'2020-06-01 12:00:00'),
(102,'sku_002',2000.00,'2020-06-01 13:00:00'),
(102,'sku_002',12000.00,'2020-06-01 13:00:00'),
(102,'sku_002',600.00,'2020-06-02 12:00:00');



select * from t_order_mt;


-- 进行分区合并
optimize table t_order_mt final;


-- 跳数索引
 create table t_order_mt2(
    uid UInt32,
    sku_id String,
    total_amount Decimal(16,2),
    create_time  Datetime,
	INDEX a total_amount TYPE minmax GRANULARITY 5
 ) engine =MergeTree
 partition by toYYYYMMDD(create_time)
   primary key (uid)
   order by (uid,sku_id);


-- Time To Live
--1、列级别TTL
-- TTL 定时过期
create table t_order_mt3(
 id UInt32,
 sku_id String,
 total_amount Decimal(16,2) TTL create_time+interval 10 SECOND,
 create_time Datetime
) engine =MergeTree
partition by toYYYYMMDD(create_time)
 primary key (id)
 order by (id, sku_id);


insert into t_order_mt3 values
(106,'sku_001',1000.00,'2020-06-12 22:52:30'),
(107,'sku_002',2000.00,'2020-06-12 22:52:30'),
(110,'sku_003',600.00,'2020-06-13 12:00:00');


optimize table t_order_mt3 final;



--2、表级别TTL
insert into  t_order_mt
Values
(106,'sku_001',1000.00,'2020-06-12 22:52:30') ,
(107,'sku_002',2000.00,'2020-06-12 22:52:30'),
(110,'sku_003',600.00,'2021-06-13 12:00:00'),
(110,'sku_003',600.00,'2022-06-13 12:00:00'),
(110,'sku_003',600.00,'2022-12-31 12:00:00');


-- 执行这条语句时，会自动进行一次TTL
alter table t_order_mt MODIFY TTL create_time + INTERVAL 10 SECOND;



-- ReplacingMergeTree
create table t_order_rmt(
    uid UInt32,
    sku_id String,
    total_amount Decimal(16,2) ,
    create_time  Datetime
 ) engine =ReplacingMergeTree(create_time)
 partition by toYYYYMMDD(create_time)
   primary key (uid)
   order by (uid,sku_id);

insert into  t_order_rmt
values
(101,'sku_001',1000.00,'2020-06-01 12:00:00') ,
(102,'sku_002',2000.00,'2020-06-01 11:00:00'),
(102,'sku_004',2500.00,'2020-06-01 12:00:00'),
(102,'sku_002',2000.00,'2020-06-01 13:00:00'),
(102,'sku_002',12000.00,'2020-06-01 13:00:00'),
(102,'sku_002',600.00,'2020-06-02 12:00:00');

optimize table t_order_rmt final;
/*
(101,'sku_001',1000.00,'2020-06-01 12:00:00') ,

(102,'sku_002',12000.00,'2020-06-01 13:00:00'),

(102,'sku_002',600.00,'2020-06-02 12:00:00'),

(102,'sku_004',2500.00,'2020-06-01 12:00:00'),
  */


-- SummingMergeTree
create table t_order_smt(
    uid UInt32,
    sku_id String,
    total_amount Decimal(16,2) ,
    create_time  Datetime
 ) engine =SummingMergeTree(total_amount)
 partition by toYYYYMMDD(create_time)
   primary key (uid)
   order by (uid,sku_id);



insert into  t_order_smt
values
(101,'sku_001',1000.00,'2020-06-01 12:00:00') ,
(102,'sku_002',2000.00,'2020-06-01 11:00:00'),
(102,'sku_004',2500.00,'2020-06-01 12:00:00'),
(102,'sku_002',2000.00,'2020-06-01 13:00:00'),
(102,'sku_002',12000.00,'2020-06-01 13:00:00'),
(102,'sku_002',600.00,'2020-06-02 12:00:00');

optimize table t_order_smt final;


create table t_order_mmt(
    uid UInt32,
    sku_id String,
    total_amount Decimal(16,2),
    create_time  Datetime
 ) engine=MergeTree
 partition by toYYYYMMDD(create_time)
 primary key (uid)
 order by (uid,sku_id);

-- 删
alter table t_order_mmt delete where sku_id ='sku_001';

-- 改
alter table t_order_mmt update total_amount=toDecimal32(2000.00,2)
where uid =102;




insert into  t_order_mmt
values
(101,'sku_001',1000.00,'2020-06-01 12:00:00') ,
(101,'sku_002',2000.00,'2020-06-01 12:00:00'),
(101,'sku_004',2500.00,'2020-06-01 12:00:00'),
(101,'sku_002',2000.00,'2020-06-01 12:00:00'),
(101,'sku_003',600.00,'2020-06-02 12:00:00'),
(110,'sku_001',1000.00,'2020-06-04 12:00:00'),
(110,'sku_002',2000.00,'2020-06-04 12:00:00'),
(110,'sku_004',2500.00,'2020-06-04 12:00:00'),
(110,'sku_002',2000.00,'2020-06-04 12:00:00'),
(110,'sku_003',600.00,'2020-06-01 12:00:00');

-- rollup
select
uid , sku_id,sum(total_amount)
from  t_order_mmt
group by uid,sku_id
with rollup;

select uid,sku_id,sum(total_amount) from  t_order_mmt group by uid,sku_id
union all
select uid,'',sum(total_amount) from  t_order_mmt group by uid
union all
select 0,'',sum(total_amount) from  t_order_mmt;


-- cube
select
uid , sku_id,sum(total_amount)
from  t_order_mmt
group by uid,sku_id
with cube;

select uid,sku_id,sum(total_amount) from  t_order_mmt group by uid,sku_id
union all
select 0,sku_id,sum(total_amount) from  t_order_mmt group by sku_id
union all
select uid,'',sum(total_amount) from  t_order_mmt group by uid
union all
select 0,'',sum(total_amount) from  t_order_mmt;

-- totals
select
uid,sku_id,sum(total_amount)
from  t_order_mmt
group by uid,sku_id
with totals ;

select uid,sku_id,sum(total_amount) from  t_order_mmt group by uid,sku_id
union all
select 0,'',sum(total_amount) from  t_order_mmt;


