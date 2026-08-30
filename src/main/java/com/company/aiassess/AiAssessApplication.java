package com.company.aiassess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * AI 能力测评考试系统启动类
 * <p>
 * 定时任务（超时结算、难度回流）通过 @Scheduled 触发，
 * 单实例部署下无需分布式调度，预留 ShedLock 升级位。
 */
@EnableScheduling
@SpringBootApplication
public class AiAssessApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiAssessApplication.class, args);
    }
}
