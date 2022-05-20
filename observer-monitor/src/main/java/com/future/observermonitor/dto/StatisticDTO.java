package com.future.observermonitor.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel("非法统计数据DTO")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StatisticDTO {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("设备序列号")
    private String deviceSerial;

    @ApiModelProperty("查询的日期范围")
    private String dateRange;

    @ApiModelProperty("统计日期")
    private Date date;

    @ApiModelProperty("非法总数")
    private Integer totalNum;

    @ApiModelProperty("未处理总数")
    private Integer untreatedNum;

    @ApiModelProperty("处理中总数")
    private Integer processingNum;

    @ApiModelProperty("已处理总数")
    private Integer processedNum;

    @ApiModelProperty("性别非法总数")
    private Integer genderNum;

    @ApiModelProperty("年龄非法总数")
    private Integer ageNum;

    @ApiModelProperty("上半身服饰非法总数")
    private Integer upperWearNum;

    @ApiModelProperty("上半身衣着颜色非法总数")
    private Integer upperColorNum;

    @ApiModelProperty("上身服饰纹理非法总数")
    private Integer upperWearTextureNum;

    @ApiModelProperty("上身服饰细分类非法总数")
    private Integer upperWearFgNum;

    @ApiModelProperty("下半身服饰非法总数")
    private Integer lowerWearNum;

    @ApiModelProperty("下半身衣着颜色非法总数")
    private Integer lowerColorNum;

    @ApiModelProperty("是否戴帽子非法总数")
    private Integer headWearNum;

    @ApiModelProperty("是否戴眼镜非法总数")
    private Integer glassesNum;

    @ApiModelProperty("背包非法总数")
    private Integer bagNum;

    @ApiModelProperty("是否戴口罩非法总数")
    private Integer faceMaskNum;

    @ApiModelProperty("人体朝向非法总数")
    private Integer orientationNum;

    @ApiModelProperty("是否使用手机非法总数")
    private Integer cellphoneNum;

    @ApiModelProperty("是否吸烟非法总数")
    private Integer smokeNum;

    @ApiModelProperty("是否有手提物非法总数")
    private Integer carryingItemNum;

    @ApiModelProperty("是否打伞非法总数")
    private Integer umbrellaNum;

    @ApiModelProperty("是否有交通工具非法总数")
    private Integer vehicleNum;

    @ApiModelProperty("遮挡情况非法总数")
    private Integer occlusionNum;

    @ApiModelProperty("上方截断非法总数")
    private Integer upperCutNum;

    @ApiModelProperty("下方截断非法总数")
    private Integer lowerCutNum;

    @ApiModelProperty("是否是正常人体非法总数")
    private Integer isHumanNum;
}
