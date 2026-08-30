package com.company.aiassess.question.service;

import com.company.aiassess.llm.core.QuestionGenContext;
import com.company.aiassess.question.entity.Question;
import org.springframework.stereotype.Service;

/**
 * 题目生成编排服务 [SDD §4.3]
 * <p>
 * 结构化生成 → Schema 校验 → 即时自审 → 入库。
 * 任一环节失败重试，总共最多 3 轮；3 轮全败抛 GenFailedException
 * → 上层 QuestionSelectService 捕获后降级到锚点题兜底。
 */
@Service
public class QuestionGenService {

    public Question generate(QuestionGenContext ctx) {
        // TODO: for round 1..3 { generateQuestion → SchemaValidator → reviewQuestion → persist }
        //  全败抛 GenFailedException
        throw new UnsupportedOperationException("待实现：SDD §4.3");
    }

    /** 入库：difficulty_prior = 自评难度映射（1→-2.0 ... 5→+2.0），difficulty_b = prior，answer_count=0 */
    private Question persist(Object draft, QuestionGenContext ctx, Object review) {
        throw new UnsupportedOperationException("待实现");
    }
}
