# Algorithm API

一个基于Spring Boot的算法服务API演示项目，用于提供基础的算法逻辑处理与请求验证功能。

## 项目概述

本项目是一个基于Spring Boot的算法服务API演示项目，用于提供基础的算法逻辑处理与请求验证功能，适合作为微服务中算法模块的原型开发。

目标用户: 后端开发者、算法工程师、Spring Boot学习者。

## 技术栈

- Java 17
- Spring Boot 3.5.6
- Maven 3.6+
- SpringDoc OpenAPI (Swagger UI)
- Lombok
- Spring Boot Validation

## 功能特性

- 提供基础REST接口用于服务验证
- 支持复杂对象的请求参数校验（如范围、大小比较等）
- 实现链表等数据结构的操作与测试
- 全局异常处理统一响应格式
- 集成Swagger UI用于API文档展示

## 快速开始

### 环境要求

- JDK 17 或更高版本
- Maven 3.6 或更高版本

### 构建项目

```bash
mvn clean package
```

### 运行项目

#### 开发环境运行

```bash
mvn spring-boot:run
```

#### 生产环境运行

```bash
mvn clean package
java -jar target/algorithmapi-0.0.1-SNAPSHOT.jar
```

#### 调试模式运行

```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--debug"
```

或者

```bash
java -jar target/algorithmapi-0.0.1-SNAPSHOT.jar --debug
```

## API接口

项目启动后，可以通过以下URL访问API：

- 应用主界面: http://localhost:8080/
- Swagger UI: http://localhost:8080/swagger-ui.html
- API文档: http://localhost:8080/v3/api-docs

### 主要API端点

1. **算法接口**
   - `GET /invoke` - 执行链表算法
   - `POST /invoke-post` - 通过POST请求执行链表算法

2. **验证接口**
   - `GET /validation/basic` - 基本参数验证示例
   - `GET /validation/custom` - 自定义参数验证示例

## 项目结构

```
src/
├── main/
│   ├── java/
│   │   └── com/playground/algorithmapi/
│   │       ├── config/           # 配置类
│   │       ├── controller/       # 控制器层
│   │       ├── dto/              # 数据传输对象
│   │       ├── exception/        # 异常处理
│   │       ├── model/            # 数据模型
│   │       ├── service/          # 业务逻辑层
│   │       ├── validation/       # 自定义验证
│   │       └── DemoApplication.java  # 主应用入口
│   └── resources/
│       └── application.properties    # 应用配置
└── test/
    └── java/                     # 测试代码
```

## 开发指南

### 代码规范

- 使用Lombok简化POJO类代码
- 使用SpringDoc OpenAPI注解编写API文档
- 使用自定义注解实现参数校验

### 添加新功能

1. 在[service](file:///Users/cnallexu/Documents/Works/labs/spring-playground/algorithm.api/src/main/java/com/playground/algorithmapi/service)包中实现业务逻辑
2. 在[controller](file:///Users/cnallexu/Documents/Works/labs/spring-playground/algorithm.api/src/main/java/com/playground/algorithmapi/controller)包中添加API端点
3. 如需要，创建相应的DTO类
4. 更新README文档

## 测试

运行单元测试:

```bash
mvn test
```

## 许可证

本项目仅供学习和参考使用。