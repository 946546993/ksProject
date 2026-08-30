package com.company.aiassess.common.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redisson 配置：分布式锁统一用 RLock（可重入、看门狗续期），
 * 不手写 SETNX [SDD §9]。
 * <p>
 * Redis Key 规划（lock:start / lock:answer / lock:reflow 等）见 SDD §9。
 */
@Configuration
public class RedissonConfig {

    @Value("${spring.data.redis.host:localhost}")
    private String host;

    @Value("${spring.data.redis.port:6379}")
    private int port;

    @Value("${spring.data.redis.password:}")
    private String password;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        String address = "redis://" + host + ":" + port;
        if (password == null || password.isBlank()) {
            config.useSingleServer().setAddress(address);
        } else {
            config.useSingleServer().setAddress(address).setPassword(password);
        }
        return Redisson.create(config);
    }
}
