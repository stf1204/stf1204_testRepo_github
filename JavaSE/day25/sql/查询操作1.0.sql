# product 产品表，商品表
CREATE TABLE product(
    # 列名pid primary key主键（数据唯一）  auto_increment（自动增长 i++）
	pid INT PRIMARY KEY AUTO_INCREMENT,
    # 商品名
	pname VARCHAR(40),
    # 商品价格
	price DOUBLE,
    # 库存数
	num INT
);
INSERT INTO product VALUES(NULL,'苹果电脑',18000.0,10);
INSERT INTO product VALUES(NULL,'华为5G手机',30000,20);
INSERT INTO product VALUES(NULL,'小米手机',1800,30);
INSERT INTO product VALUES(NULL,'iPhonex',8000,10);
INSERT INTO product VALUES(NULL,'iPhone7',6000,200);
INSERT INTO product VALUES(NULL,'iPhone6s',4000,1000);
INSERT INTO product VALUES(NULL,'iPhone6',3500,100);
INSERT INTO product VALUES(NULL,'iPhone5s',3000,100);

INSERT INTO product VALUES(NULL,'方便面',4.5,1000);
INSERT INTO product VALUES(NULL,'咖啡',11,200); 
INSERT INTO product VALUES(NULL,'矿泉水',3,500);


-- 查询全表 *不建议使用
SELECT * FROM product;

SELECT pid,pname,price,num FROM product;


-- 查询指定列
SELECT pid,pname,price,num FROM product WHERE pid=4;


-- 去重查询 distinct()
SELECT DISTINCT(num) FROM product;

-- 计算以及重命名 查询
SELECT pid,pname,price*1.2 price,num FROM product;

-- 计算表有多少行
SELECT COUNT(1) FROM product;

-- 计算价格的平均数
SELECT AVG(price) FROM product;

-- 计算价格总数
SELECT SUM(price) FROM product;


-- 模糊查找
SELECT * FROM product WHERE pname LIKE '%p%';


-- 查找以手机结尾的数据信息
SELECT * FROM product WHERE pname LIKE '%手机';


-- 查询价格等于3000的商品
SELECT * FROM product WHERE price =3000;

-- 查询价格大于3000的商品
SELECT * FROM product WHERE price >=3000;

-- 查询价格小于3000的商品
SELECT * FROM product WHERE price <=3000;

-- 查询数量为空的值
SELECT * FROM product WHERE num IS NULL;

-- 查询数量非空的值
SELECT * FROM product WHERE num IS NOT NULL;

-- 查询1，3，5，7，9的商品
SELECT * FROM product WHERE pid IN (1,3,5,7,9);

-- 查询价格大于3000的商品，按照价格降序排列
SELECT * FROM  product WHERE price >3000 ORDER BY price DESC;