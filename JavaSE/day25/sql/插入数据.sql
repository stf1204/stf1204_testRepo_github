--方式一：添加用户
INSERT INTO student(id,NAME,class,age) VALUES (1,'张三','一年级一班',7); 

--方式二：添加用户
INSERT INTO student VALUES (2,'李四','二年级一班',8);

--方法三：批量添加
INSERT  INTO student VALUES 
(3,'刘德华','班级未知',55),
(4,'韩梅梅','班级未知',57),
(5,'任贤齐','班级未知',58);

--方法四：部分添加
INSERT INTO student(id,NAME) VALUES
(6,'林俊杰'),
(7,'张杰');