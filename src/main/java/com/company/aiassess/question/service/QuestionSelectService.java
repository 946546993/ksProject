package com.company.aiassess.question.service;

import com.company.aiassess.exam.entity.ExamRecord;
import com.company.aiassess.user.entity.User;
import org.springframework.stereotype.Service;

/**
 * 选题编排服务 [SDD §4.2]
 * <p>
 * 流程：定维度（缺口优先 → 配额轮转）→ 定目标难度 b_target ≈ θ（首题 -0.3）
 * → 题库匹配（知识点去重、岗位适配）→ 命中随机取一；未命中走生成编排。
 * 返回前强制剥离答案与解析（VO 层不含这两个字段，双保险）。
 */
@Service
public class QuestionSelectService {

    public Object selectNext(ExamRecord record, User user) {
        // TODO: pickDimension → targetB → matchFromBank → orElse(generate) → NextQuestionVO.of(record, q)
        throw new UnsupportedOperationException("待实现：SDD §4.2");
    }

    /** 维度调度：未达每维最低题量(3)的维度中取缺口最大者；否则按剩余配额轮转 */
    private Object pickDimension(ExamRecord record) {
        // TODO: 锚点题插槽（第 5/10/15/20 题）命中时直接返回锚点题
        throw new UnsupportedOperationException("待实现");
    }
}
