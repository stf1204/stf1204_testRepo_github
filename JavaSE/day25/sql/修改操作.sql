-- update 表名 set 列=值，列=值 where 条件
-- where后面是条件，不加条件就是修改整个表

UPDATE student SET class = '大一',age=22 WHERE id=6;

-- delete from 表名 where 条件
DELETE FROM student WHERE id=2; 

-- drop table 表名