package com.company.aiassess.llm.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * LLM 网关 [SDD §7.2]
 * <p>
 * 路由策略：primary_first（默认）/ cost_first / quality_first<br>
 * 熔断：Resilience4j CircuitBreaker，按 provider 独立实例
 * （failureRate=50%，slidingWindow=10，openWait=60s）<br>
 * 超时：TimeLimiter 15s（生成）/ 10s（自审）；重试 2 次，退避 500ms/1000ms<br>
 * 全量调用写 t_llm_call_log。
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LlmGateway {

    /** providerId -> provider 实例（Spring 注入所有实现） */
    private final Map<String, LlmProvider> providerMap;

    public QuestionDraft generateQuestion(QuestionGenContext ctx) {
        // TODO: routeOrder() 遍历 → 熔断状态检查 → TimeLimiter 执行 → 失败写日志换下一家
        //  全部失败抛 LlmAllChannelDownException（上层锚点题兜底）
        throw new UnsupportedOperationException("待实现：SDD §7.2");
    }

    public ReviewResult reviewQuestion(QuestionDraft draft) {
        throw new UnsupportedOperationException("待实现：SDD §7.2");
    }

    public String polishAdvice(String template, String wrongDigest) {
        throw new UnsupportedOperationException("待实现：SDD §7.2");
    }

    /** 路由顺序（按 route-strategy 配置） */
    private List<LlmProvider> routeOrder() {
        // TODO: 读 llm.route-strategy 配置，按 primary/成本/质量排序，跳过熔断 OPEN 的通道
        throw new UnsupportedOperationException("待实现：SDD §7.2");
    }
}
