DROP TABLE product;

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
	-- 商品跟雷
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
INSERT INTO product VALUES(NULL,'咖啡',11,200, 3); 
INSERT INTO product VALUES(NULL,'矿泉水',3,500, 3);


-- 对临时数据加条件选择
SELECT SUM(num) num ,category FROM product GROUP BY category HAVING num>10;

SELECT SUM(price) price ,category FROM product GROUP BY category ;


-- 查看部分数据
SELECT * FROM product LIMIT 0,5;
SELECT * FROM product LIMIT 5,5;
SELECT * FROM product LIMIT 10,5;


-- 主键1、
CREATE TABLE person(
id INT PRIMARY KEY,
firstname VARCHAR(30),
lastname VARCHAR(30)
)

DROP TABLE person



-- 主键2、
CREATE TABLE person(
id INT,
firstname VARCHAR(30),
lastname VARCHAR(30),
CONSTRAINT PRIMARY  KEY(id)
)

DROP TABLE person



-- 主键3、
CREATE TABLE person(
id INT,
firstname VARCHAR(30),
lastname VARCHAR(30)
)

ALTER TABLE person ADD PRIMARY KEY(id)


-- 删除主键
ALTER TABLE person DROP PRIMARY KEY


-- 主键自动增长，只能设置在主键上
CREATE TABLE person(
id INT PRIMARY KEY AUTO_INCREMENT,
firstname VARCHAR(30),
lastname VARCHAR(30)
)

INSERT INTO  person VALUE(NULL,'李','123');
INSERT INTO  person VALUE(NULL,'徐','龙象');

-- 摧毁表
-- delete 和truncate都可以删除表数据
-- delete不删除自动增长，truncate会删除表再重新建。

DELETE FROM person;
INSERT INTO  person VALUE(NULL,'徐','凤年');

TRUNCATE TABLE person;
INSERT INTO  person VALUE(NULL,'徐','凤年');





