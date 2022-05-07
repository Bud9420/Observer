package com.future.observermonitor.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@TableName("ysopen_secret")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Secret {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String appKey;

    private String appSecret;

    private String accessToken;

    private Integer userId;
}
