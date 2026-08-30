package com.company.aiassess.admin.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 系统参数配置表 t_sys_config（算法参数热更新）
 */
@Data
@TableName("t_sys_config")
public class SysConfig {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 如 exam.k0 / reflow.w0 */
    private String configKey;

    private String configValue;

    private String description;

    private Long updatedBy;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
