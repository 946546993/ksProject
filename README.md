# ksPro（AI 能力测评考试系统）

单体 Spring Boot 应用 + Vue3 前端。详细设计见《AI 能力测评考试系统 详细设计文档》V1.0。

## 技术栈

- **后端**：Java 17 / Spring Boot 3.2.x / MyBatis-Plus 3.5 / MySQL 8.0 / Redis 7 + Redisson / Resilience4j / jjwt / springdoc-openapi
- **前端**：Vue 3.4 + Vite 5 / Element Plus / Pinia / ECharts / vue-router

## 目录结构

```
ksPro/
├── pom.xml
├── sql/schema.sql              # 全量 DDL + 参数初始化（先执行这个）
├── src/main/java/com/company/aiassess/
│   ├── common/                 # 通用基础设施（响应体/异常/枚举/配置/工具）
│   ├── auth/                   # 免登 + JWT + 拦截器
│   ├── user/                   # 人员管理 + 首次补录
│   ├── exam/                   # 考试模块（engine 能力引擎 / scheduler 超时结算）
│   ├── question/               # 题目模块（select 选题 / gen 生成 / reflow 难度回流）
│   ├── llm/                    # LLM 网关（core SPI / provider / prompt）
│   ├── report/                 # 报告模块
│   └── admin/                  # 管理后台
├── src/main/resources/
│   ├── application.yml
│   ├── mapper/                 # MyBatis XML
│   ├── prompts/                # Prompt 模板基线（DB 可热更新覆盖）
│   └── schemas/                # LLM 生成结果 JSON Schema
└── web/                        # Vue3 前端（npm run dev）
```

## 快速开始

```bash
# 1. 建库
mysql -uroot -p < sql/schema.sql

# 2. 后端（JDK 17）
mvn spring-boot:run

# 3. 前端
cd web && npm install && npm run dev   # http://localhost:5173
```

## 注意事项

- 每次修改代码后，必须先完成验证（编译/构建通过），再进行 git 提交，并在提交信息中详细说明更改了什么，然后才能交付。

## 当前状态

骨架阶段（对应 SDD 任务拆解的 T1 + T5）：工程结构、common 层、实体/Mapper、接口签名均已就位，
所有业务方法为 `UnsupportedOperationException` + TODO 注释（标注对应 SDD 章节），待按 T2-T22 逐个填充。

## 环境说明（本机）

- JDK 17：`D:\tools\jdk17`（JAVA_HOME 已指向，JDK 8 保留在 `D:\tools\jdk8`）
- Maven 3.9.16：`D:\projectSpace\maven\apache-maven-3.9.16`（阿里云镜像 + 本地仓库）
- 数据库/Redis/LLM 密钥等敏感配置走环境变量，见 `application.yml`
