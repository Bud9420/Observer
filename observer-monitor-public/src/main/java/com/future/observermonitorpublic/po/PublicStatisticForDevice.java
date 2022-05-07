package com.future.observermonitorpublic.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@TableName("monitor_public_statistic_device")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PublicStatisticForDevice {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Date date;

    private Integer totalNum;

    private Integer untreatedNum;

    private Integer processingNum;

    private Integer processedNum;

    private Integer deviceId;
}
