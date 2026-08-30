package com.company.aiassess.llm.prompt;

import org.springframework.stereotype.Component;

/**
 * Prompt 模板管理 [SDD §7.3]
 * <p>
 * 加载优先级：DB 生效版本（t_prompt_template status=1）→ classpath 资源文件（prompts/*.txt 基线）。
 * 占位符 {name} 完整性校验，缺失直接 fail-fast，防线上出题 Prompt 残缺。
 * 每次调用记录 gen_prompt_version 到题目表，支持按版本对比出题质量。
 */
@Component
public class PromptManager {

    /**
     * 按 scene 加载当前生效的 Prompt
     *
     * @param scene GEN / REVIEW / ADVICE
     */
    public String load(String scene) {
        // TODO: DB 生效版本 → 资源文件，占位符完整性校验
        throw new UnsupportedOperationException("待实现：SDD §7.3");
    }
}
