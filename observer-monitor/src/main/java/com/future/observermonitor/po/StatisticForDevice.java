package com.future.observermonitor.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@TableName("monitor_statistic_device")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StatisticForDevice {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private Date date;

    private Integer totalNum;

    private Integer untreatedNum;

    private Integer processingNum;

    private Integer processedNum;

    private Integer genderNum;

    private Integer ageNum;

    private Integer upperWearNum;

    private Integer upperColorNum;

    private Integer upperWearTextureNum;

    private Integer upperWearFgNum;

    private Integer lowerWearNum;

    private Integer lowerColorNum;

    private Integer headWearNum;

    private Integer glassesNum;

    private Integer bagNum;

    private Integer faceMaskNum;

    private Integer orientationNum;

    private Integer cellphoneNum;

    private Integer smokeNum;

    private Integer carryingItemNum;

    private Integer umbrellaNum;

    private Integer vehicleNum;

    private Integer occlusionNum;

    private Integer upperCutNum;

    private Integer lowerCutNum;

    private Integer isHumanNum;

    private Integer deviceId;

    @TableField(exist = false)
    private String deviceSerial;
}
