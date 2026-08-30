package com.company.aiassess.user.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 人员表 t_user
 */
@Data
@TableName("t_user")
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 企微/钉钉 userid */
    private String externalId;

    private String name;

    private Long deptId;

    /** 部门名称（冗余） */
    private String deptName;

    /** 直属主管ID(t_user.id) */
    private Long managerId;

    /** 岗位类别 1技术 2产品 3销售 4职能 5其他 */
    private Integer jobCategory;

    private String jobLevel;

    /** AI经验自评 1几乎没用过 2偶尔 3常用 4深度使用 */
    private Integer aiExpSelf;

    /** 每周使用频次 0零次 1低(1-3) 2中(4-10) 3高(>10) */
    private Integer aiUsageFreq;

    /** 标签已补录 0否 1是 */
    private Integer profileFilled;

    /** 状态 1正常 0停用 */
    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
