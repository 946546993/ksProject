package com.company.aiassess.exam.service;

import org.springframework.stereotype.Service;

/**
 * 考试编排服务：开考 / 断点续答 / 结束（SDD §6.2）
 */
@Service
public class ExamService {

    // TODO: start（事务 + Redis 锁 + uk 兜底）、resume、finish（幂等）
}
