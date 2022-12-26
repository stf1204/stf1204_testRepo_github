--创建数据库
CREATE DATABASE my1;
student

--查看创建的数据库
SHOW CREATE DATABASE my1;
 
--使用数据库
USE my1;


--创建学生信息表
CREATE TABLE student(
id INT,
`name` VARCHAR(20),
class VARCHAR(20),
age INT );

--添加列  不建议使用
ALTER TABLE student ADD address VARCHAR(30);

--修改列的数据类型  不建议使用
ALTER TABLE student MODIFY address INT;

--修改列  不建议使用
ALTER TABLE student CHANGE address newaddress VARCHAR(30);

--删除列  不建议使用
ALTER TABLE student DROP newaddress;


