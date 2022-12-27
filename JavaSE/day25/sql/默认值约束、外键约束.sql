
DROP TABLE person

-- 创建默认值约束 创建表时候，直接添加
CREATE TABLE person(
id INT  PRIMARY KEY AUTO_INCREMENT,
firstname VARCHAR(30)DEFAULT '孙',
lastname VARCHAR(30)
)

INSERT INTO person (lastname)VALUE ('强');
INSERT INTO person VALUE (NULL,'南','台南');
INSERT INTO person VALUE
 (NULL,'宋','西哈'),
 (NULL,'李','咩');
 INSERT INTO person VALUES
 (NULL,'赵','哈'),
 (NULL,'赵','敏');
 

-- 删除默认值约束  modify
ALTER TABLE person MODIFY firstname VARCHAR(30); 

-- 增加默认值约束 用modify增加
ALTER TABLE person MODIFY firstname VARCHAR(30)DEFAULT '孙'; 




-- 创建数据库
CREATE DATABASE my2;


-- 使用数据库
USE my2;


-- 创建学生表，成绩表
CREATE TABLE student(
sid INT PRIMARY KEY AUTO_INCREMENT,
sname VARCHAR(30),
sclass VARCHAR(30)
);

CREATE TABLE score(
sid INT PRIMARY KEY AUTO_INCREMENT,
score INT,
fk_sid INT);

-- 创建外键约束
-- alter table score add constraint 约束名 foreign key(从表的外键列名) 
-- references 主表(主表主键列名) 

-- 删除外键
ALTER TABLE score DROP FOREIGN KEY  fk_student_sid;

-- 增加外键
ALTER TABLE score ADD CONSTRAINT  FOREIGN KEY(fk_sid) REFERENCES student(sid)

-- 删除外键
ALTER TABLE score DROP FOREIGN KEY score_ibfk_1; 

-- 增加外键
ALTER TABLE score ADD CONSTRAINT student_fk FOREIGN KEY (fk_sid) REFERENCES student(sid) 

-- 删除外键
ALTER TABLE score DROP FOREIGN KEY student_fk

-- 增加外键
ALTER TABLE score ADD CONSTRAINT student_fk FOREIGN KEY (fk_sid) REFERENCES student(sid)

-- 删除外键
ALTER TABLE score DROP FOREIGN KEY student_fk

-- 增加外键
ALTER TABLE score ADD CONSTRAINT fk_student_sid FOREIGN KEY (fk_sid) REFERENCES student(sid)


INSERT INTO  student VALUES
(NULL,"xiaohong","1班"),
(NULL,"xiaosong","2班"),
(NULL,"xiaoming","3班"),
(NULL,"xiaoli","4班")

INSERT INTO score VALUES
(NULL,99,1),
(NULL,98,2),
(NULL,92,3),
(NULL,89,4),
(NULL,57,1),
(NULL,88,2)

-- 受外键约束
-- insert into score value (null ,100,9)




# product 产品表，商品表
CREATE TABLE product(
    # 列名pid primary key主键（数据唯一）  auto_increment（自动增长 i++）
	pid INT PRIMARY KEY AUTO_INCREMENT,
    # 商品名
	pname VARCHAR(40),
    # 商品价格
	price DOUBLE,
    # 库存数
	num INT,
    # 商品类型
	category INT
);
INSERT INTO product VALUES(NULL,'苹果电脑',18000.0,10, 1);
INSERT INTO product VALUES(NULL,'华为5G手机',30000,20, 1);
INSERT INTO product VALUES(NULL,'小米手机',1800,30, 1);
INSERT INTO product VALUES(NULL,'iPhonex',8000,10, 1);
INSERT INTO product VALUES(NULL,'iPhone7',6000,200, 1);
INSERT INTO product VALUES(NULL,'iPhone6s',4000,1000, 1);
INSERT INTO product VALUES(NULL,'iPhone6',3500,100, 1);
INSERT INTO product VALUES(NULL,'iPhone5s',3000,100, 1);

INSERT INTO product VALUES(NULL,'方便面',4.5,1000, 2);
INSERT INTO product VALUES(NULL,'薯片',6,5000, 2);
INSERT INTO product VALUES(NULL,'咖啡',11,200, 2); 
INSERT INTO product VALUES(NULL,'矿泉水',3,500, 3);



-- 商品分类表
CREATE TABLE  category (
cid INT PRIMARY KEY AUTO_INCREMENT,
cname VARCHAR(30)
)

INSERT INTO category VALUES
(NULL,'手机数码'),
(NULL,'食品'),
(NULL,'饮品')

-- 添加外键
ALTER TABLE product ADD CONSTRAINT fk_category_id FOREIGN KEY (category) 
REFERENCES category(cid);


-- 创建订单表
CREATE TABLE orders(
oid INT PRIMARY KEY AUTO_INCREMENT,
createtime VARCHAR(30),
total DOUBLE,
`status` VARCHAR(30));

INSERT INTO orders VALUES 
(NULL,NOW(),18009,'未付款'),
(NULL,NOW(),18017,'已付款'),
(NULL,NOW(),1855,'未付款');


-- 创建中间表
CREATE TABLE product_orders(
pid INT,
oid INT );


-- 增加外键 product -> product_orders
ALTER TABLE product_orders ADD CONSTRAINT fk_product_orders_pid FOREIGN KEY(pid) 
REFERENCES product(pid);


-- 增加外键 orders ->product_orders
ALTER TABLE product_orders ADD CONSTRAINT fk_product_orders_oid FOREIGN KEY(oid) 
REFERENCES orders(oid);


-- 增加数据
-- 1号订单 苹果电脑、矿泉水、薯片
INSERT INTO product_orders VALUES(1,1),(12,1),(9,1);
-- 2号订单 苹果电脑、咖啡、矿泉水*2
INSERT INTO product_orders VALUES(1,2),(11,2),(12,2);
-- 3号订单 小米手机、咖啡*5
INSERT INTO product_orders VALUES(3,3),(11,3);

-- 插入一个错误数据
INSERT INTO product_orders VALUES(14,2);
INSERT INTO product_orders VALUES(1,5);




