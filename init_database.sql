-- 创建catalog数据库（如果不存在）
CREATE DATABASE IF NOT EXISTS catalog CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用catalog数据库
USE catalog;

-- 创建用户表
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    email VARCHAR(100) NOT NULL COMMENT '邮箱',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- 插入测试数据
INSERT INTO users (username, email) VALUES
('test_user1', 'test1@example.com'),
('test_user2', 'test2@example.com'),
('admin', 'admin@example.com');

-- 创建数据库用户（如果不存在）
-- 注意：您需要有足够的权限执行以下命令，通常需要root用户
CREATE USER IF NOT EXISTS 'appuser'@'localhost' IDENTIFIED BY '12345678';
CREATE USER IF NOT EXISTS 'appuser'@'%' IDENTIFIED BY '12345678';

-- 给用户授权
GRANT ALL PRIVILEGES ON catalog.* TO 'appuser'@'localhost';
GRANT ALL PRIVILEGES ON catalog.* TO 'appuser'@'%';

-- 刷新权限
FLUSH PRIVIES;

-- 查看创建的表
SHOW TABLES;

-- 查看用户表结构
DESCRIBE users;

-- 查看测试数据
SELECT * FROM users;