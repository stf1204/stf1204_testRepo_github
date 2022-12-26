DROP TABLE person;

-- 唯一约束 unique
-- UNIQUE 唯一约束，不能重复
CREATE TABLE person(
id INT PRIMARY KEY AUTO_INCREMENT,
firstname VARCHAR(30),
lastname VARCHAR(30)UNIQUE
)

INSERT INTO  person VALUE(NULL,'李','123');
INSERT INTO  person VALUE(NULL,'徐','龙象');
INSERT INTO  person VALUE(NULL,'宋','象');

-- 删除 唯一约束unique，需要删除索引
ALTER TABLE person DROP INDEX lastname;


DROP TABLE person;

-- 非空约束  创建一、
CREATE TABLE person(
id INT PRIMARY KEY AUTO_INCREMENT,
firstname VARCHAR(30),
lastname VARCHAR(30) NOT NULL
)
-- 可以添加成功，‘null’为字符串常量
INSERT INTO person VALUE(NULL,'徐','NULL');

-- 添加不成功，null为空值
INSERT INTO person VALUE(NULL,'徐',NULL);


DROP TABLE person;
-- 创建非空约束 方法二、
CREATE TABLE person(
id INT PRIMARY KEY AUTO_INCREMENT,
firstname VARCHAR(30),
lastname VARCHAR(30)
)

ALTER TABLE person MODIFY lastname VARCHAR(30) NOT NULL;


-- 删除非空约束只有一种方法
ALTER TABLE person MODIFY lastname VARCHAR(30);
