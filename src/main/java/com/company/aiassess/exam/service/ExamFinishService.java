package com.company.aiassess.exam.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 考试结束与报告生成服务 [SDD §4.6]
 * <p>
 * θ 终值 → 六维收缩 → 分数 → 等级 → 学习建议；耗时 < 1s，
 * 学习建议的 LLM 润色走异步（先返回报告，advice 字段异步补）。
 */
@Service
public class ExamFinishService {

    @Transactional
    public void finish(Long recordId) {
        // TODO: ①汇总流水 ②六维收缩 ③分数 ④等级 ⑤薄弱点 ⑥组装报告+is_best
        //  ⑦异步润色 advice ⑧第二次作答对比更新 is_best
        throw new UnsupportedOperationException("待实现：SDD §4.6");
    }
}
