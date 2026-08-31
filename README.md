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

## 已完成事项

- ✅ 环境准备：安装 Temurin JDK 17 → `D:\tools\jdk17`，`JAVA_HOME` 已切换
- ✅ **T1** 工程脚手架 + common 层（统一响应体 `Result` / 错误码 `ErrorCode` / 全局异常 / 枚举 / MP 配置 / Redisson）
- ✅ **T5** `sql/schema.sql` 全量 DDL + `t_sys_config` 参数初始化，10 张表实体 + Mapper 生成
- ✅ 前端工程骨架（Vue3 + Vite5 + Element Plus + Pinia + ECharts + vue-router，路由按 SDD §10.1）
- ✅ 后端 `mvn clean compile` 通过（75 class）、前端 `npm run build` 通过

## 未完成事项

> 业务逻辑均未实现，方法体为 `UnsupportedOperationException` + TODO（已标注对应 SDD 章节）。

- ⬜ **T2** 登录免登 + JWT + 权限拦截
- ⬜ **T3** t_user 全套 + 首次补录接口
- ⬜ **T4** 场次管理 CRUD + 参数配置
- ⬜ **T6** 能力引擎 RaschAbilityEngine + 单测
- ⬜ **T7** 开考 / 断点续答 / 结束 + 超时结算任务
- ⬜ **T8** 选题服务（维度调度 + 题库匹配 SQL）
- ⬜ **T9** 提交答案（幂等 / 判分 / θ 更新）
- ⬜ **T10** LLM 网关（SPI / 路由 / 熔断 / 日志）
- ⬜ **T11** Prompt 管理 + Schema 校验器
- ⬜ **T12** 生成编排（生成 → 自审 → 入库 → 降级）
- ⬜ **T13** 锚点题插槽机制
- ⬜ **T14** 难度回流任务 + 单测
- ⬜ **T15** 六维收缩 + 等级判定 + finish 服务
- ⬜ **T16** 报告接口（画像 / 明细 / 主管视图）
- ⬜ **T17** 举报 + 复核 + 作废重算
- ⬜ **T18** 重考逻辑（第二次作答 + is_best）
- ⬜ **T19** 前端：答题页（倒计时 / 防回退 / 解析区）
- ⬜ **T20** 前端：报告页（雷达图 / 薄弱点 / 建议）
- ⬜ **T21** 前端：管理后台（场次 / 题库 / 复核 / 看板）
- ⬜ **T22** 集成测试 + 压测
- ⬜ **T23** 锚点题内容编写（业务侧并行）

关键路径：T1 → T5 → T6 → T8 → T9 → T14 → T15 → T16 → T22。

## 环境说明（本机）

- JDK 17：`D:\tools\jdk17`（JAVA_HOME 已指向，JDK 8 保留在 `D:\tools\jdk8`）
- Maven 3.9.16：`D:\projectSpace\maven\apache-maven-3.9.16`（阿里云镜像 + 本地仓库）
- 数据库/Redis/LLM 密钥等敏感配置走环境变量，见 `application.yml`
