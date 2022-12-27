--1、内部表、外部表
create table stu(name varchar ,age int ,account BIGINT);
create external table stu(name varchar ,age int ,account BIGINT);

-- 2、回顾 hive中的复杂数据结构
    -- 2.1 Array
select `array`(1, 2, 3);
select `array`(1, 2, 3, "a");

    -- 2.2 Map （k-v对）
select `map`(1,2,3,4);
select `map`("1",2,3,"4");

    -- 2.3 struct（k-v对）
select struct(1, 2, 3, "a");
select named_struct("id", 1001, "name", "fang", "sex", "female");


-- 3、 分区表 partition（表的虚拟列）
show tables;
create table my_partition(id int,name string)
partitioned by (dt string)
location "/user/hive/warehouse/my_partition";

    -- 静态分区
insert into my_partition partition (dt="2020") values (1000,"xiaoning");

    -- 动态分区需要设置非严格模式
set hive.exec.dynamic.partition.mode=nonstrict;
insert  into table my_partition partition (dt) values (1001,"zhangsan","2021"),(1002,"lisi","2022"),(1003,"zhaoliu","2022");;
select * from my_partition;

-- 4、 JSON表 （数据为JSON格式，表还是文本文件）
use default;
create table JSON_test(id int, name string,age bigint)
row format SERDE 'org.apache.hive.hcatalog.data.JsonSerDe'
stored as textfile;
show tables ;

select * from JSON_test;
-- 5、 存储格式 gzip
-- 6、 位置 location
