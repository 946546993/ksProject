package com.company.aiassess.auth.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 登录态拦截器：校验 JWT + Redis 会话，未登录返回 40101。
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // TODO: 解析 Authorization: Bearer xxx → Redis 会话校验 → 用户上下文 ThreadLocal
        return true;
    }
}
