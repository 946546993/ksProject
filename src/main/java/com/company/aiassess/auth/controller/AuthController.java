package com.company.aiassess.auth.controller;

import com.company.aiassess.common.web.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 免登入口 [SDD §1.1 / O1]
 * <p>
 * 企微 vs 钉钉最终选型待定（O1），接口已抽象：code → userid → 签发 JWT。
 */
@Tag(name = "认证")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    /** 免登换 code → 换 userid → 签发 JWT */
    @Operation(summary = "免登")
    @PostMapping("/login")
    public Result<String> login(@RequestParam String code) {
        // TODO: OAuth code 换 userid → 查/建 t_user → 签发 JWT（12h，Redis auth:token:{userId}）
        throw new UnsupportedOperationException("待实现：SDD T2");
    }
}
