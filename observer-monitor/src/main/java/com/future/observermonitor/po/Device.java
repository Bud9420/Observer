package com.future.observermonitor.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@TableName("monitor_device")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Device {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String name;

    private String deviceSerial;

    private Integer channelNo;

    private Integer userId;

    private Integer sceneId;
}
