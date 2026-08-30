package com.company.aiassess.user.controller;

import com.company.aiassess.common.web.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 人员接口：个人信息 + 首次补录
 */
@Tag(name = "人员")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Operation(summary = "我的信息")
    @GetMapping("/me")
    public Result<Void> me() {
        // TODO: 当前登录用户信息
        throw new UnsupportedOperationException("待实现：SDD T3");
    }

    /** 首次补录：岗位/职级/AI经验/使用频次 4 字段，强制完成才能开考（41001） */
    @Operation(summary = "首次补录标签")
    @PostMapping("/profile")
    public Result<Void> fillProfile(@RequestBody Object profileRequest) {
        // TODO: 更新 t_user 4 字段 + profile_filled=1
        throw new UnsupportedOperationException("待实现：SDD T3");
    }
}
