
----------------------------------atguigu--hive-------------------------------------------------

----数据库
CREATE DATABASE [IF NOT EXISTS] database_name
[COMMENT 'database_comment']
[LOCATION 'hdfs_path']
[WITH DBPROPERTIES ('property_name'='property_value', ...)];

CREATE database if not exists atguigu comment 'databse_commit'
location '/user/hive/warehouse/atguigu' with dbproperties('aaa'='bbb');


---- 表格
CREATE [EXTERNAL] TABLE [IF NOT EXISTS] table_name 
[(col_name data_type [COMMENT col_comment], ...)] 
[COMMENT table_comment] 

[PARTITIONED BY (col_name data_type [COMMENT col_comment], ...)] 		--分区信息
[CLUSTERED BY (col_name, col_name, ...)                          		--分桶信息
[SORTED BY (col_name [ASC|DESC], ...)] INTO num_buckets BUCKETS] 		-- 分桶排序

[ROW FORMAT row_format]                                          		--行分割
[STORED AS file_format]                                          		--存储类型
[LOCATION hdfs_path]                                             		--存储位置
[TBLPROPERTIES (property_name=property_value, ...)]              		--表属性
[AS select_statement]                                            		--结果创建表
[LIKE table_name]                                                		--复制表结构

create table  test_type(
name string,
friends array<string>,
children map<string,int>,
address struct<street:string,city:string,email:int>
)
row format DELIMITED FIELDS TERMINATED by ','
collection items terminated by '_'
map keys terminated by ':'
LINES TERMINATED BY '\n';



load data local inpath '/usr/local/src/hive-1.2.2/datas/test_type.txt' into table test_type;

select
friends[1],children['xiao song'],address.city 
from test_type
where name = "songsong";

--数据库只有dbproperties这一属性可以修改，其他均不可修改
alter database atguigu set dbproperties('createtime'='2022-10-26');

create table if not exists student1(
id int,
name string)
row format DELIMITED FIELDS TERMINATED BY '\t'
LOCATION '/user/hive/warehouse/atguigu/student1/';

load data local inpath '/usr/local/src/hive-1.2.2/datas/students.txt' into table student;

CREATE table  if not exists student2 as select id,name from student;

create table if not exists student3 like student;

desc formatted student2;


CREATE EXTERNAL table if not exists teacher(
id int comment 'id',
name string comment 'name')
row format DELIMITED FIELDS terminated BY '\t'
location '/school/teacher';


内部表和外部表类型互转
---'EXTERNAL'='FALSE'   和  'EXTERNAL'='TRUE' 为固定写法，大小写不可变
alter table student set tblproperties ('EXTERNAL'='TRUE');
alter  table teacher set tblproperties ('EXTERNAL'='FALSE');

alter table student rename to student1;




create table student( id int COMMENT '学生ID',name string COMMENT '学生姓名')
               COMMENT '学生表'
               PARTITIONED BY ( `date` string COMMENT '日期')
               row format delimited fields terminated by '\t';
desc formatted student;

create table if not exists dept(
deptno int comment '部门编号',
dname string comment '部门名称',
loc int comment '部门位置')
comment '部门表'
row format delimited fields terminated BY '\t'

create table if not exists emp(
empno int comment '员工编号',
ename string comment '员工名称',
job string comment '员工岗位',
sal double comment '员工薪资',
deptno int comment '部门编号' 
)
comment '员工表'
row format delimited fields terminated by '\t'


load data local inpath '/usr/local/src/hive-1.2.2/datas/emp.txt' into table emp;
load data local inpath '/usr/local/src/hive-1.2.2/datas/dept.txt' into table emp;


select empno  no ,ename name from emp;

select empno,ename,sal *2 sal from emp;


select count(1) num,
sum(sal) sum_sal,
max(sal) max_sal,
min(sal) min_sal,
avg(sal) avg_sal
from emp;

select ename,sal from emp where sal > 1000;


select * from emp where sal between 500 and 1000;


select * from emp where job <=> null;
select * from emp where job is null;


select * from emp where sal = 1500 or sal = 5000;
select * from emp where sal in (1500,5000);


select  * from emp where ename like '小%';
select * from emp where ename rlike '^小';


select * from emp where ename like '%明';
select * from emp where ename rlike '明$';


select * from emp where ename like '%明%';


select * from emp where sal > 1000 and deptno = 30;

select * from emp where sal>1000 or deptno =30;

select * from emp where deptno not in (20,30);

select deptno ,avg(sal) avg_sal from emp group by deptno;

select deptno,
	avg(sal) avg_sal,
	max(sal) max_sal,
	min(sal) min_sal,
	sum(sal) sum_sal 
from emp group by deptno;

select job,deptno,max(sal) max_sal from emp group by deptno,job;

select  deptno,avg(sal) avg_sal from emp group by deptno ;

select deptno, avg(sal) avg_sal from emp group by deptno having avg_sal>2000;


select e.empno,e.deptno,d.dname
from emp e join dept d on d.deptno=e.deptno ;

select * from 
emp e join dept d on e.deptno=d.deptno;

select e.empno,e.deptno,d.dname
from emp e left join dept d on e.deptno=d.deptno;


insert into table dept VALUES(60,'不存在',1700);
insert into emp values(7360,'死鬼','失业',null,50);

select e.empno,e.deptno,d.dname 
from emp e right join dept  d on e.deptno=d.deptno

select e.empno,d.deptno,d.dname
from dept d full join emp e on d.deptno=e.deptno

create table location (
loc int comment '部门位置id',
loc_name string comment '部门位置')
comment '部门位置表'
row format delimited fields  terminated by '\t'

load data local inpath '/usr/local/src/hive-1.2.2/datas/location.txt' into table location;

select e.empno,e.ename,e.job,e.sal,e.deptno,d.dname,d.loc,l.loc_name
from emp  e join dept d 
on e.deptno=d.deptno
join location l
on d.loc=l.loc; 

select empno,ename,d.deptno
from emp,dept d

select * 
from emp
order by sal desc;

select empno, ename,sal s 
from emp 
order by s;

-- 先按照部门编号升序排序，相同部门按照工资降序排序
select empno,ename,sal salary,deptno dno
from emp
order BY dno,salary desc; 

-- 按照部门平均工资降序给部门排序
select deptno,avg(sal) avg_sal
from emp
group by deptno
order by avg_sal desc;

-- 全局排序数据量大可能会遇到性能问题，所以排序一般结合limit使用
select *
from emp
order by sal  desc
limit 5

-- distribute by指定查询按照什么字段的hash分区, sort by在分区内部排序
select empno,ename,deptno,sal
from emp
distribute by deptno
sort by sal desc;

-- 当distribute by和sort by字段相同且为升序时，可以用cluster by代替
select empno,ename,deptno,sal
from emp
cluster BY empno


----作业--
create table if not exists  person(personid int ,firstname string,lastname string);
create table if not exists address(addressid int, personid int,city string,state string);


insert into person(personid,lastname,firstname) values ('1','wang','reke'),('2','lee','code'),('3','song','wukong');
insert into address(addressid,personid,city,state) values('1','2','new york city','new york'),('2','3','leetcode','java');

-- 如果personid的地址不在address表中，则报告为空null
select firstname,lastname,city,state
from person p left join address a
on p.personid=a.personid


create table if not exists employee(id int, name string,salary int,managerid int);
insert into employee(id,name,salary,managerid) values ('1','joe','70000','3'),('2','herry','80000','4'),('3','sam','60000',null),('4','max','90000',null);

--查找员工收入比自己的经理高的员工
select e.name 
from employee e join employee m
on e.managerid=m.id
where e.salary>m.salary 

-- 系统函数nvl（） 空值替换
select ename,job,nvl(job,'没工作') from emp;


create table emp_sex(
name string comment '姓名',
dept_id string comment '部门id',
sex string comment '性别')
row format delimited fields terminated by '\t';

load data local inpath '/usr/local/src/hive-1.2.2/datas/emp_sex.txt' into table emp_sex;

--求出不同部门男女各多少人
select dept_id,
sum(1) sum,
count(if(sex='男',name,null)) male,
count(case sex when '女' then 1 else null end) famale,
count(case when sex='女' then 1 else null end) famale2
from emp_sex
group by dept_id;

create table person_info(
name string comment '姓名',
constellation string comment '星座',
blood_tyoe string comment '血型'
)
row format delimited fields terminated by '\t';

load data local inpath '/usr/local/src/hive-1.2.2/datas/constellation.txt' into table person_info;



输出结果：
射手座,A            大海|凤姐
白羊座,A            孙悟空|猪八戒
白羊座,B            宋宋|苍老师

--把星座和血型一样的人归类到一起
select 
concat(constellation,',',blood_tyoe) xzxx1,
concat_ws(',',constellation,blood_tyoe) xzxx2,
concat_ws('|',collect_list(name)) name
from person_info
group BY constellation,blood_tyoe


-- 将电影分类中的数组数据展开。结果如下：
《疑犯追踪》      悬疑
《疑犯追踪》      动作
《疑犯追踪》      科幻
《疑犯追踪》      剧情
《Lie to me》   悬疑
《Lie to me》   警匪
《Lie to me》   动作
《Lie to me》   心理
《Lie to me》   剧情
《战狼2》        战争
《战狼2》        动作
《战狼2》        灾难


create table movie_info(
movie string,
category string)
row format delimited fields terminated by '\t';

load data local inpath '/usr/local/src/hive-1.2.2/datas/movie_info.txt' into table movie_info;


select 
movie,type
from movie_info
lateral view explode(split(category,',')) types as type



--1、 将列转行的例子的表，转换为type,[movie1,movie2..].
select t.type,concat_ws(',',collect_list(t.movie)) movies
from (
select 
movie,type
from movie_info
lateral view explode(split(category,',')) types as type)t
group BY t.type



--2、 根据test_type表，查一下name,child,age
select name parent,child,age
from test_type
lateral view explode(children) child as child,age






create table business(
name string,
orderdate string,
cost int
)
row format delimited  fields terminated by ',';

load data local inpath '/usr/local/src/hive-1.2.2/datas/business.txt' into table business


（1）查询在2017年4月份购买过的顾客及总人数
	-- 如果想查询2017年4月购买过的人
	select distinct name 
	from business where substring(orderdate,1,7)='2017-04' 

	-- 如果想求2017年4月购买过的人数
	select count(distinct name) 
	from business where substring(orderdate,1,7)='2017-04'

	-- 如果希望将两个结果放在一张表里面展示
select name,count(name) over () cnt
from business 
where substring(orderdate,1,7)='2017-04'
group by name;



（2）查询顾客的购买明细及商店每个月购买的总额
select name,orderdate,cost,sum(cost) over (partition by substring(orderdate,1,7)) sum 
from business




（3）上述的场景，将每个顾客的cost按照日期进行累加
-- 查询每个顾客的截止到当日的累计消费
select name,orderdate,cost,
sum(cost) over(partition by name order BY substring(orderdate,1,7) rows  between unbounded preceding and current row) n_sum
from business

select name,orderdate,cost,
sum(cost)over(partition by name order by orderdate)
from business

-- 最近两次购买
select name,orderdate,cost,
sum(cost) over(partition by name order by substring(orderdate,1,7) rows between 1 preceding and current row) now_sum 
from business;


-- 购买明细和截至当日来过店的顾客
select name,orderdate,cost,
collect_set(name) over(order by substring(orderdate,1,7) rows between unbounded preceding and current row) client
from business



（4）查询顾客购买明细以及上次的购买时间(lag) 和下次购买时间(lead)
select name,cost,
lag(orderdate,1,1970) over (partition by name order by orderdate)last,
orderdate now,
lead(orderdate,1,2025) over (partition by name order BY orderdate) head
from business;


-- 订单明细按照下单时间分五组
select name,orderdate,cost,
ntile(5) over(sort by orderdate) nu
from business;


-- 如果想查询前20%的订单，怎么查.在上表基础上
（5）查询前20%时间的订单信息

select name,orderdate,cost,nu
from
(select name,orderdate,cost,
ntile(5) over(order by orderdate) nu
from business)t1
where t1.nu=1


（6）查询顾客每个月第一次的购买时间 和 每个月的最后一次购买时间


孙悟空	语文	87
孙悟空	数学	95
孙悟空	英语	68
大海	语文	94
大海	数学	56
大海	英语	84
宋宋	语文	64
宋宋	数学	86
宋宋	英语	84
婷婷	语文	65
婷婷	数学	85
婷婷	英语	78
create table score(
name string comment '姓名',
subject string comment '课程',
score int comment'分数')
comment '成绩表'
row format delimited fields terminated BY '\t';

load data local inpath '/usr/local/src/hive-1.2.2/datas/score.txt' into table score;


--计算每门学科成绩排名。
select name,subject,score,
rank() over(partition by subject order by score desc) r,
dense_rank() over (partition by subject order by score desc) dr,
row_number() over(partition by subject order by score desc) rn
from  score

--扩展：求出每门学科前三名的学生？

select name,subject,score
from
(select name,subject,score,
rank() over(partition by subject order by score desc) r
from  score)t1
where t1.r<=3



-- 练习1、返回不同国家11月的天气状况, 天气状况根据11月weather_state决定

-- avg(weather_state)<=15   寒冷
-- 15<avg(weather_state)<25   温暖
-- avg(weather_state)>=25   炎热

create table if not exists countries(
country_id int comment '国家号',
country_name string comment '国家名称'
);

drop table if exists weather;
create table if not exists weather(
country_id int comment '国家号',
weather_state string comment '天气状况',
day date comment '时间'
);

insert into countries(country_id,country_name)values('2', 'USA')
,('3', 'Australia')
,('7', 'Peru')
,('5', 'China')
,('8', 'Morocco')
,('9', 'Spain');

insert into table weather(country_id,weather_state,day)values('2', '15', '2019-11-01')
,('2', '12', '2019-10-28')
,('2', '12', '2019-10-27')
,('3', '-2', '2019-11-10')
,('3', '0', '2019-11-11')
,('3', '3', '2019-11-12')
,('5', '16', '2019-11-07')
,('5', '18', '2019-11-09')
,('5', '21', '2019-11-23')
,('7', '25', '2019-11-28')
,('7', '22', '2019-12-01')
,('7', '20', '2019-12-02')
,('8', '25', '2019-11-05')
,('8', '27', '2019-11-15')
,('8', '31', '2019-11-25')
,('9', '7', '2019-10-23')
,('9', '3', '2019-12-23');

-- 返回不同国家11月的天气状况, 天气状况根据11月weather_state决定

select  c.country_name cname,
case when avg(w.weather_state) <=15 then '寒冷'
when  avg(w.weather_state) >15 and avg(w.weather_state)<25 then '温暖'
else '炎热' end type
from countries c join weather w on c.country_id=w.country_id
where substring(w.day,1,7)='2019-11'
group by country_name



-- 练习2、开窗练习

create table sales (
id string comment '商品id'
,category_id string comment '商品属性id'
,sales_sum int comment'销售数量');

insert overwrite table sales
select  pos, 
        floor(rand(1231239819)*9) + 1, 
        floor(rand(1231457656)*10000) + 100
from (
  select posexplode(split(repeat("a",50000),"a"))
) t1
where pos <> 0;

+-----------+--------------------+------------------+--+
| sales.id  | sales.category_id  | sales.sales_sum  |
+-----------+--------------------+------------------+--+
| 49901     | 2                  | 5848             |
| 49902     | 2                  | 3244             |
| 49903     | 8                  | 4397             |
| 49904     | 2                  | 4773             |
| 49905     | 3                  | 8059             |
| 49906     | 9                  | 10047            |
| 49907     | 9                  | 6422             |
| 49908     | 8                  | 4346             |
+-----------+--------------------+------------------+--+


rank() over(partition by category_id order by id)
--1. 每个类型消费排名前十
-- category_id, id, rank

select cid,id,s
from
(select category_id cid,id,
rank() over (partition by category_id order by sales_sum desc) s
from sales )t
where s <=10;


--2. 各品类销量前十的商品列表(数组)以及销售总额
-- category_id, ids, total_sum

--前十总额
select tt.cid, collect_list(id) ids, sum(sales_sum) total_sum
from 
(select cid,id,sales_sum
from
(select category_id cid,id,sales_sum,
rank() over (partition by category_id order by sales_sum desc) s
from sales 
)t
where s <=10) tt
group BY tt.cid

--总商品总额

select cid, collect_list(if(s_order<=10,id,null)) ids,sum(ss) total_sum
from
(select category_id cid,id,sales_sum ss,
rank() over (partition by category_id order by sales_sum desc) s_order
from sales 
)t
group BY cid

