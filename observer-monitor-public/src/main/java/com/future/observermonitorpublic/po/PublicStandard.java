package com.future.observermonitorpublic.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@TableName("monitor_public_standard")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PublicStandard {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private Integer locHeight;

    private Integer locWidth;

    private Integer locLeft;

    private Integer locTop;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String gender;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String age;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String upperWear;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String upperColor;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String upperWearTexture;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String upperWearFg;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String lowerWear;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String lowerColor;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String headWear;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String glasses;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String bag;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String faceMask;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String orientation;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String cellphone;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String smoke;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String carryingItem;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String umbrella;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String vehicle;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String occlusion;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String upperCut;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String lowerCut;

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String isHuman;

    private Integer deviceId;
}
