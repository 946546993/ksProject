package com.company.aiassess.auth.jwt;

import org.springframework.stereotype.Component;

/**
 * JWT 工具（jjwt 0.12.x API）
 * <p>
 * 会话：Redis auth:token:{userId} TTL 12h，支持提前失效。
 */
@Component
public class JwtUtil {

    // TODO: generate（HS256，userId/externalId 载荷）、parse、Redis 会话管理
}
