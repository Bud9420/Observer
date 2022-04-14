package com.future.observermonitorpublic.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@TableName("monitor_public_people")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PublicPeople {

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

    private String gender;

    private String age;

    private String upperWear;

    private String upperColor;

    private String upperWearTexture;

    private String upperWearFg;

    private String lowerWear;

    private String lowerColor;

    private String headWear;

    private String glasses;

    private String bag;

    private String faceMask;

    private String orientation;

    private String cellphone;

    private String smoke;

    private String carryingItem;

    private String umbrella;

    private String vehicle;

    private String occlusion;

    private String upperCut;

    private String lowerCut;

    private String isHuman;

    private Integer imgId;
}
