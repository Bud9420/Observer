package com.future.observermonitor.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel("非法统计数据VO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticVO {

    @ApiModelProperty("统计日期")
    private Date date;

    @ApiModelProperty("非法总数")
    private Integer totalNum;

    @ApiModelProperty("未处理数")
    private Integer untreatedNum;

    @ApiModelProperty("处理中数")
    private Integer processingNum;

    @ApiModelProperty("已处理数")
    private Integer processedNum;

    @ApiModelProperty("性别非法数")
    private String genderNum;

    @ApiModelProperty("年龄阶段非法数")
    private String ageNum;

    @ApiModelProperty("上半身服饰非法数")
    private String upperWearNum;

    @ApiModelProperty("上半身衣着颜色非法数")
    private String upperColorNum;

    @ApiModelProperty("上身服饰纹理非法数")
    private String upperWearTextureNum;

    @ApiModelProperty("上身服饰细分类非法数")
    private String upperWearFgNum;

    @ApiModelProperty("下半身服饰非法数")
    private String lowerWearNum;

    @ApiModelProperty("下半身衣着颜色非法数")
    private String lowerColorNum;

    @ApiModelProperty("是否戴帽子非法数")
    private String headWearNum;

    @ApiModelProperty("是否戴眼镜非法数")
    private String glassesNum;

    @ApiModelProperty("背包非法数")
    private String bagNum;

    @ApiModelProperty("是否戴口罩非法数")
    private String faceMaskNum;

    @ApiModelProperty("人体朝向非法数")
    private String orientationNum;

    @ApiModelProperty("是否使用手机非法数")
    private String cellphoneNum;

    @ApiModelProperty("是否吸烟非法数")
    private String smokeNum;

    @ApiModelProperty("是否有手提物非法数")
    private String carryingItemNum;

    @ApiModelProperty("是否打伞非法数")
    private String umbrellaNum;

    @ApiModelProperty("是否有交通工具非法数")
    private String vehicleNum;

    @ApiModelProperty("遮挡情况非法数")
    private String occlusionNum;

    @ApiModelProperty("上方截断非法数")
    private String upperCutNum;

    @ApiModelProperty("下方截断非法数")
    private String lowerCutNum;

    @ApiModelProperty("是否是正常人体非法数")
    private String isHumanNum;
}
