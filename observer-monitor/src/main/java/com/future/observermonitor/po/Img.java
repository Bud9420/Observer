package com.future.observermonitor.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@TableName("monitor_img")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Img {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private String path;

    private String illegalType;

    private String processStatus;

    private Integer deviceId;

    @TableField(exist = false)
    private String deviceSerial;
}
