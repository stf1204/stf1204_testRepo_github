SHOW CREATE TABLE product;
SELECT * FROM USER WHERE username='badou' AND `password` = '11';
SELECT * FROM product LIMIT 1,200000;
DELETE FROM product WHERE pid >=15;

UPDATE  product SET price=3.5 WHERE pid=12

CREATE TABLE account(
aid INT PRIMARY KEY AUTO_INCREMENT,
aname VARCHAR(30),
anum INT)

INSERT INTO account VALUES(NULL,'张三',10000),(NULL,'李四',10000);

UPDATE account SET anum = anum-1000 WHERE aname = '张三';
UPDATE account SET anum = anum+1000 WHERE aname= '李四';