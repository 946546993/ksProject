package com.company.aiassess.exam.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 提交答案服务 [SDD §4.4]
 * <p>
 * 高频写路径，重点保证幂等与并发正确：
 * Redis 锁 → 校验状态/有效期 → 校验序号 → 判分 → 引擎更新 → 写流水 →
 * 乐观锁更新 record → 题目计数原子自增 → 返回判分结果。
 */
@Service
public class AnswerService {

    @Transactional
    public void submit(Long recordId, Long questionId, java.util.List<String> answer, int durationSec) {
        // TODO: SDD §4.4 ①~⑧
        throw new UnsupportedOperationException("待实现：SDD §4.4");
    }
}
