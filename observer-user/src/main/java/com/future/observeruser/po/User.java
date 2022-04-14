package com.future.observeruser.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("user_userinfo")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private String username;

    private String password;

    private String phone;

    private String headPath;

    private Integer companyId;
}
