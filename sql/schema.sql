-- ============================================================
-- AI 能力测评考试系统 全量 DDL
-- 依据：《AI 能力测评考试系统 详细设计文档》V1.0 §2.2
-- 环境：MySQL 8.0，utf8mb4 / utf8mb4_0900_ai_ci
-- ============================================================

CREATE DATABASE IF NOT EXISTS ai_assess
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_0900_ai_ci;

USE ai_assess;

-- ============================================================
-- 1. 人员表
-- ============================================================
CREATE TABLE t_user (
    id              BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    external_id     VARCHAR(64)  NOT NULL COMMENT '企微/钉钉 userid',
    name            VARCHAR(32)  NOT NULL COMMENT '姓名',
    dept_id         BIGINT       DEFAULT NULL COMMENT '部门ID',
    dept_name       VARCHAR(64)  DEFAULT NULL COMMENT '部门名称(冗余)',
    manager_id      BIGINT       DEFAULT NULL COMMENT '直属主管ID(t_user.id)',
    job_category    TINYINT      DEFAULT NULL COMMENT '岗位类别 1技术 2产品 3销售 4职能 5其他',
    job_level       VARCHAR(16)  DEFAULT NULL COMMENT '职级',
    ai_exp_self     TINYINT      DEFAULT NULL COMMENT 'AI经验自评 1几乎没用过 2偶尔 3常用 4深度使用',
    ai_usage_freq   TINYINT      DEFAULT NULL COMMENT '每周使用频次 0零次 1低(1-3) 2中(4-10) 3高(>10)',
    profile_filled  TINYINT      NOT NULL DEFAULT 0 COMMENT '标签已补录 0否 1是',
    status          TINYINT      NOT NULL DEFAULT 1 COMMENT '状态 1正常 0停用',
    created_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY uk_external_id (external_id),
    KEY idx_manager (manager_id),
    KEY idx_dept (dept_id)
) ENGINE=InnoDB COMMENT='人员表';

-- ============================================================
-- 2. 场次表
-- ============================================================
CREATE TABLE t_exam_session (
    id                  BIGINT       NOT NULL AUTO_INCREMENT,
    name                VARCHAR(64)  NOT NULL COMMENT '场次名称',
    start_time          DATETIME     NOT NULL COMMENT '窗口期开始',
    end_time            DATETIME     NOT NULL COMMENT '窗口期结束',
    question_count      INT          NOT NULL DEFAULT 25 COMMENT '题量',
    duration_minutes    INT          NOT NULL DEFAULT 30 COMMENT '限时(分钟)',
    max_attempts        INT          NOT NULL DEFAULT 2 COMMENT '最大作答次数',
    min_per_dimension   INT          NOT NULL DEFAULT 3 COMMENT '每维度最低题量',
    early_stop_se       DECIMAL(3,2) NOT NULL DEFAULT 0.30 COMMENT '提前终止标准误阈值',
    status              TINYINT      NOT NULL DEFAULT 0 COMMENT '0未开始 1进行中 2已结束',
    created_at          DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    KEY idx_status_time (status, start_time, end_time)
) ENGINE=InnoDB COMMENT='考试场次表';

-- ============================================================
-- 3. 考核记录表
-- ============================================================
CREATE TABLE t_exam_record (
    id                  BIGINT        NOT NULL AUTO_INCREMENT,
    record_no           VARCHAR(32)   NOT NULL COMMENT '记录编号 ER+yyyyMMdd+seq',
    user_id             BIGINT        NOT NULL,
    session_id          BIGINT        NOT NULL,
    attempt             TINYINT       NOT NULL DEFAULT 1 COMMENT '第几次作答',
    parent_id           BIGINT        DEFAULT NULL COMMENT '首次记录ID(重考关联)',
    is_best             TINYINT       NOT NULL DEFAULT 0 COMMENT '是否该场次有效成绩(取高分)',
    status              TINYINT       NOT NULL DEFAULT 1 COMMENT '1进行中 2已完成 3超时作废 4已重算',
    start_time          DATETIME      NOT NULL,
    deadline            DATETIME      NOT NULL COMMENT '开始时间+限时,结算依据',
    end_time            DATETIME      DEFAULT NULL,
    duration_seconds    INT           DEFAULT NULL,
    answered_count      INT           NOT NULL DEFAULT 0,
    theta_global        DECIMAL(5,3)  DEFAULT NULL COMMENT '全局能力值[-3,3]',
    theta_basic         DECIMAL(5,3)  DEFAULT NULL COMMENT '维度1 基础认知',
    theta_prompt        DECIMAL(5,3)  DEFAULT NULL COMMENT '维度2 提示词与工具',
    theta_scenario      DECIMAL(5,3)  DEFAULT NULL COMMENT '维度3 场景判断',
    theta_engineering   DECIMAL(5,3)  DEFAULT NULL COMMENT '维度4 工程与前沿',
    theta_agent         DECIMAL(5,3)  DEFAULT NULL COMMENT '维度5 Agent落地',
    theta_boundary      DECIMAL(5,3)  DEFAULT NULL COMMENT '维度6 业务边界判断',
    score_global        INT           DEFAULT NULL COMMENT '综合分0-100',
    score_dimensions    JSON          DEFAULT NULL COMMENT '六维分明细[{key,name,score,count}]',
    level               CHAR(2)       DEFAULT NULL COMMENT '等级 L1-L5',
    advice              TEXT          DEFAULT NULL COMMENT '学习建议(JSON数组)',
    is_calibration      TINYINT       NOT NULL DEFAULT 1 COMMENT '是否标定期成绩 1是 0否',
    manager_id          BIGINT        DEFAULT NULL COMMENT '冗余主管ID,主管查询免join',
    version             INT           NOT NULL DEFAULT 0 COMMENT '乐观锁',
    created_at          DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY uk_record_no (record_no),
    UNIQUE KEY uk_user_session_attempt (user_id, session_id, attempt) COMMENT '防重复开考',
    KEY idx_manager (manager_id, status),
    KEY idx_session_level (session_id, level),
    KEY idx_deadline (status, deadline) COMMENT '超时结算任务扫描用'
) ENGINE=InnoDB COMMENT='考核记录表';

-- ============================================================
-- 4. 题目表
-- ============================================================
CREATE TABLE t_question (
    id                  BIGINT        NOT NULL AUTO_INCREMENT,
    question_no         VARCHAR(32)   NOT NULL COMMENT '题目编号 Q+yyyyMMdd+seq',
    type                TINYINT       NOT NULL COMMENT '1单选 2多选',
    stem                TEXT          NOT NULL COMMENT '题干',
    options             JSON          NOT NULL COMMENT '[{"key":"A","text":"..."}]',
    correct_answer      JSON          NOT NULL COMMENT '["A"] 或 ["A","C"]',
    explanation         TEXT          NOT NULL COMMENT '解析',
    dimension           VARCHAR(24)   NOT NULL COMMENT '维度枚举 BASIC/PROMPT/SCENARIO/ENGINEERING/AGENT/BOUNDARY',
    knowledge_point     VARCHAR(64)   NOT NULL COMMENT '知识点',
    difficulty_b        DECIMAL(5,3)  NOT NULL COMMENT '当前难度值[-3,3]',
    difficulty_prior    DECIMAL(5,3)  NOT NULL COMMENT '模型自评难度映射的先验值',
    answer_count        INT           NOT NULL DEFAULT 0 COMMENT '累计作答次数(置信度)',
    correct_count       INT           NOT NULL DEFAULT 0 COMMENT '累计完全正确次数',
    job_fit             VARCHAR(16)   NOT NULL DEFAULT 'ALL' COMMENT 'TECH/PRODUCT/SALES/FUNC/OTHER/ALL',
    is_anchor           TINYINT       NOT NULL DEFAULT 0 COMMENT '锚点题 1是(难度固定不回流)',
    source              TINYINT       NOT NULL DEFAULT 1 COMMENT '1模型生成 2人工录入',
    gen_model           VARCHAR(32)   DEFAULT NULL COMMENT '生成模型标识',
    gen_prompt_version  VARCHAR(16)   DEFAULT NULL COMMENT 'Prompt版本',
    self_review_passed  TINYINT       DEFAULT NULL COMMENT '通过即时自审 1是 0否',
    report_count        INT           NOT NULL DEFAULT 0 COMMENT '被标记次数',
    status              TINYINT       NOT NULL DEFAULT 1 COMMENT '1正常 2待复核 3已作废',
    deleted             TINYINT       NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    version             INT           NOT NULL DEFAULT 0 COMMENT '乐观锁',
    created_at          DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY uk_question_no (question_no),
    KEY idx_dim_diff (dimension, difficulty_b) COMMENT '选题核心索引',
    KEY idx_dim_point (dimension, knowledge_point) COMMENT '知识点去重查询',
    KEY idx_status (status, is_anchor)
) ENGINE=InnoDB COMMENT='题目表';

-- ============================================================
-- 5. 答题流水表
-- ============================================================
CREATE TABLE t_answer_record (
    id                  BIGINT        NOT NULL AUTO_INCREMENT,
    record_id           BIGINT        NOT NULL COMMENT '考核记录ID',
    user_id             BIGINT        NOT NULL COMMENT '冗余,跨记录统计用',
    question_id         BIGINT        NOT NULL,
    seq                 INT           NOT NULL COMMENT '作答序号(1起)',
    user_answer         JSON          NOT NULL COMMENT '["B"]',
    score               DECIMAL(3,2)  NOT NULL COMMENT '0 / 0.5 / 1.0',
    is_correct          TINYINT       NOT NULL COMMENT '是否完全正确',
    duration_seconds    INT           DEFAULT NULL COMMENT '本题用时',
    theta_snapshot      DECIMAL(5,3)  NOT NULL COMMENT '作答时能力值快照[PRD §7.2]',
    difficulty_snapshot DECIMAL(5,3)  NOT NULL COMMENT '作答时题目难度快照[PRD §7.2]',
    dimension           VARCHAR(24)   NOT NULL COMMENT '冗余维度',
    reflow_flag         TINYINT       NOT NULL DEFAULT 0 COMMENT '已参与难度回流 0否 1是',
    answered_at         DATETIME      NOT NULL,
    created_at          DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY uk_record_seq (record_id, seq) COMMENT '防重复提交',
    KEY idx_question_reflow (question_id, reflow_flag) COMMENT '回流任务核心索引',
    KEY idx_user (user_id)
) ENGINE=InnoDB COMMENT='答题流水表';

-- ============================================================
-- 6. 题目举报表
-- ============================================================
CREATE TABLE t_question_report (
    id              BIGINT       NOT NULL AUTO_INCREMENT,
    question_id     BIGINT       NOT NULL,
    record_id       BIGINT       NOT NULL COMMENT '来源考核记录',
    user_id         BIGINT       NOT NULL COMMENT '举报人',
    report_type     TINYINT      NOT NULL COMMENT '1答案错误 2选项歧义 3超纲 4表述不清 5其他',
    description     VARCHAR(500) DEFAULT NULL,
    status          TINYINT      NOT NULL DEFAULT 1 COMMENT '1待处理 2判定无误 3判定有误已作废',
    handler_id      BIGINT       DEFAULT NULL,
    handle_result   VARCHAR(500) DEFAULT NULL,
    handled_at      DATETIME     DEFAULT NULL,
    created_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    KEY idx_question (question_id),
    KEY idx_status (status)
) ENGINE=InnoDB COMMENT='题目举报表';

-- ============================================================
-- 7. 难度回流日志表
-- ============================================================
CREATE TABLE t_difficulty_log (
    id              BIGINT       NOT NULL AUTO_INCREMENT,
    question_id     BIGINT       NOT NULL,
    b_before        DECIMAL(5,3) NOT NULL,
    b_after         DECIMAL(5,3) NOT NULL,
    sample_count    INT          NOT NULL,
    expected_rate   DECIMAL(5,4) NOT NULL COMMENT '期望正确率',
    observed_rate   DECIMAL(5,4) NOT NULL COMMENT '实际正确率',
    reflowed_at     DATETIME     NOT NULL,
    batch_no        VARCHAR(32)  NOT NULL COMMENT '回流批次号(整批回滚用)',
    created_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    KEY idx_question (question_id),
    KEY idx_batch (batch_no)
) ENGINE=InnoDB COMMENT='难度回流日志';

-- ============================================================
-- 8. 系统参数配置表(算法参数热更新)
-- ============================================================
CREATE TABLE t_sys_config (
    id              BIGINT       NOT NULL AUTO_INCREMENT,
    config_key      VARCHAR(64)  NOT NULL COMMENT '如 exam.k0 / reflow.w0',
    config_value    VARCHAR(255) NOT NULL,
    description     VARCHAR(255) DEFAULT NULL,
    updated_by      BIGINT       DEFAULT NULL,
    created_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY uk_key (config_key)
) ENGINE=InnoDB COMMENT='系统参数配置';

-- ============================================================
-- 9. LLM 调用日志表
-- ============================================================
CREATE TABLE t_llm_call_log (
    id              BIGINT       NOT NULL AUTO_INCREMENT,
    trace_id        VARCHAR(40)  NOT NULL COMMENT '链路ID',
    provider        VARCHAR(32)  NOT NULL COMMENT '供应商标识',
    model           VARCHAR(64)  DEFAULT NULL,
    scene           VARCHAR(32)  NOT NULL COMMENT 'GEN/REVIEW/ADVICE',
    prompt_version  VARCHAR(16)  DEFAULT NULL,
    input_digest    VARCHAR(255) DEFAULT NULL COMMENT '入参摘要(SHA256前255位,不存全文)',
    success         TINYINT      NOT NULL,
    cost_ms         INT          DEFAULT NULL,
    prompt_tokens   INT          DEFAULT NULL,
    completion_tokens INT        DEFAULT NULL,
    error_msg       VARCHAR(500) DEFAULT NULL,
    created_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    KEY idx_scene_time (scene, created_at),
    KEY idx_trace (trace_id)
) ENGINE=InnoDB COMMENT='LLM调用日志';

-- ============================================================
-- 10. Prompt 模板表(热更新优先于资源文件)
-- ============================================================
CREATE TABLE t_prompt_template (
    id              BIGINT       NOT NULL AUTO_INCREMENT,
    scene           VARCHAR(32)  NOT NULL COMMENT 'GEN/REVIEW/ADVICE',
    version         VARCHAR(16)  NOT NULL COMMENT 'v1/v2',
    system_prompt   TEXT         NOT NULL,
    user_template   TEXT         NOT NULL COMMENT '含 {placeholder} 占位符',
    status          TINYINT      NOT NULL DEFAULT 0 COMMENT '0草稿 1生效 2下线',
    created_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY uk_scene_version (scene, version)
) ENGINE=InnoDB COMMENT='Prompt模板表';

-- ============================================================
-- 初始化数据：系统参数（SDD §11 键清单默认值）
-- ============================================================
INSERT INTO t_sys_config (config_key, config_value, description) VALUES
('exam.question_count',       '25',                '题量'),
('exam.duration_minutes',     '30',                '限时(分钟)'),
('exam.min_per_dimension',    '3',                 '每维最低题量'),
('exam.early_stop_se',        '0.30',              '提前终止阈值'),
('exam.multi_partial_ratio',  '0.5',               '多选漏选得分比例'),
('algo.k0',                   '0.8',               '能力更新初始步长'),
('algo.tau',                  '8',                 '能力更新衰减常数'),
('algo.kb0',                  '1.5',               '回流步长'),
('algo.tau_b',                '20',                '回流衰减常数'),
('algo.w0',                   '10',                '难度先验权重'),
('algo.shrink_k',             '3',                 '六维收缩系数'),
('algo.score_slope',          '15',                '分数映射斜率'),
('level.thresholds',          'L1:-1.2,L2:-0.2,L3:0.8,L4:1.8', '等级 θ 阈值(JSON)'),
('reflow.cron',               '0 0 2 * * ?',       '回流触发'),
('reflow.batch_size',         '200',               '回流批量');
