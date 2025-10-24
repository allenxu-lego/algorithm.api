# Spring Boot 项目配置说明

## 已完成的配置

### 1. MySQL数据库访问配置 ✅
- **依赖**: 已添加MyBatis-Plus Boot Starter (3.5.5)
- **MySQL驱动**: 已添加mysql-connector-j
- **数据库连接配置**:
  - URL: `jdbc:mysql://localhost:3306/catalog`
  - 用户名: `appuser`
  - 密码: `12345678`

### 2. HikariCP连接池配置 ✅
- **依赖**: 已添加HikariCP（Spring Boot默认连接池）
- **连接池配置**:
  - 最小空闲连接数: 5
  - 最大连接数: 20
  - 连接超时: 30秒
  - 空闲超时: 30秒
  - 最大生命周期: 30分钟

### 3. Log4j2日志配置 ✅
- **依赖**: 已添加spring-boot-starter-log4j2
- **日志文件输出路径**: `./logs/`
- **日志文件**:
  - `all.log` - 所有日志
  - `error.log` - 错误日志
  - `sql.log` - SQL执行日志
- **日志级别**: 
  - 应用程序: DEBUG
  - MyBatis-Plus SQL: DEBUG
  - Spring框架: INFO

## 文件结构

```
src/
├── main/
│   ├── java/
│   │   └── com/playground/algorithmapi/
│   │       ├── config/
│   │       │   ├── MyBatisPlusConfig.java     # MyBatis-Plus配置
│   │       │   ├── SwaggerConfig.java
│   │       │   └── ValidationConfig.java
│   │       ├── controller/
│   │       │   └── UserController.java        # 数据库测试控制器
│   │       ├── entity/
│   │       │   └── User.java                  # 用户实体类
│   │       ├── mapper/
│   │       │   └── UserMapper.java            # MyBatis-Plus Mapper
│   │       └── service/
│   │           ├── IUserService.java          # 用户服务接口
│   │           └── impl/
│   │               └── UserServiceImpl.java   # 用户服务实现
│   └── resources/
│       ├── application.properties             # 数据库和连接池配置
│       └── log4j2-spring.xml                 # Log4j2配置文件
└── init_database.sql                          # 数据库初始化脚本
```

## 使用说明

### 1. 数据库准备
执行项目根目录下的 `init_database.sql` 脚本来创建数据库、表和测试数据：

```bash
mysql -u root -p < init_database.sql
```

### 2. 启动项目
```bash
./mvnw spring-boot:run
```

### 3. 测试API接口
项目启动后，可以通过以下接口测试数据库连接：

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **获取所有用户**: GET http://localhost:8080/api/users
- **根据ID获取用户**: GET http://localhost:8080/api/users/{id}
- **创建用户**: POST http://localhost:8080/api/users
- **更新用户**: PUT http://localhost:8080/api/users/{id}
- **删除用户**: DELETE http://localhost:8080/api/users/{id}

### 4. 查看日志
日志文件将保存在项目根目录的 `logs/` 文件夹中：
- `all.log` - 查看所有日志
- `error.log` - 查看错误日志
- `sql.log` - 查看SQL执行日志

## 配置说明

### MyBatis-Plus配置特性
- ✅ 自动驼峰命名转换
- ✅ SQL执行日志输出
- ✅ 自动主键生成
- ✅ 分页插件支持
- ✅ 基础CRUD操作自动生成

### HikariCP连接池优势
- ✅ 高性能连接池
- ✅ 自动连接泄漏检测
- ✅ 连接有效性检查
- ✅ 优化的连接管理

### Log4j2日志优势
- ✅ 异步日志处理
- ✅ 按日期和大小自动滚动
- ✅ 多种日志级别配置
- ✅ 彩色控制台输出

## 注意事项

1. **数据库连接**: 确保MySQL服务已启动，且catalog数据库已创建
2. **用户权限**: 确保appuser用户已创建并有catalog数据库的操作权限
3. **端口冲突**: 默认使用8080端口，如有冲突请修改application.properties中的server.port
4. **日志目录**: 项目会自动创建logs目录，确保有写入权限
