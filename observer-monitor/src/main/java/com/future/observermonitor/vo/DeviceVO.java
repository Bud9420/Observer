package com.future.observermonitor.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("监控设备VO")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeviceVO {

    @ApiModelProperty("设备名")
    private String deviceName;

    @ApiModelProperty("设备序列号")
    private String deviceSerial;

    @ApiModelProperty("通道号")
    private Integer channelNo;

    @ApiModelProperty("应用场景名")
    private String sceneName;

    @ApiModelProperty("设备在线状态，1表示在线，0表示离线")
    private Integer status;

    @ApiModelProperty("设备信号")
    private String deviceSignal;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("年龄阶段")
    private String age;

    @ApiModelProperty("上半身服饰")
    private String upperWear;

    @ApiModelProperty("上半身衣着颜色")
    private String upperColor;

    @ApiModelProperty("上身服饰纹理")
    private String upperWearTexture;

    @ApiModelProperty("上身服饰细分类")
    private String upperWearFg;

    @ApiModelProperty("下半身服饰")
    private String lowerWear;

    @ApiModelProperty("下半身衣着颜色")
    private String lowerColor;

    @ApiModelProperty("是否戴帽子")
    private String headWear;

    @ApiModelProperty("是否戴眼镜")
    private String glasses;

    @ApiModelProperty("背包")
    private String bag;

    @ApiModelProperty("是否戴口罩")
    private String faceMask;

    @ApiModelProperty("人体朝向")
    private String orientation;

    @ApiModelProperty("是否使用手机")
    private String cellphone;

    @ApiModelProperty("是否吸烟")
    private String smoke;

    @ApiModelProperty("是否有手提物")
    private String carryingItem;

    @ApiModelProperty("是否打伞")
    private String umbrella;

    @ApiModelProperty("是否有交通工具")
    private String vehicle;

    @ApiModelProperty("遮挡情况")
    private String occlusion;

    @ApiModelProperty("上方截断")
    private String upperCut;

    @ApiModelProperty("下方截断")
    private String lowerCut;

    @ApiModelProperty("是否是正常人体")
    private String isHuman;
}
