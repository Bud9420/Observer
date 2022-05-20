package com.future.observeruser.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@TableName("user_company")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Company {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private String companyName;

    private String licensePath;

    private String legalPerson;

    private String companyPhone;

    private String companyWebsite;
}
