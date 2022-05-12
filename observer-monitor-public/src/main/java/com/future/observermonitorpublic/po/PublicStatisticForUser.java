package com.future.observermonitorpublic.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@TableName("monitor_public_statistic_user")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PublicStatisticForUser {

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

    public Integer userId;

    @TableField(exist = false)
    private String username;
}
