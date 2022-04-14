package com.future.observercommon.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("驾驶行为产生非法信息的驾驶员VO")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DrivingDriverVO {

    @ApiModelProperty("人体区域的高度")
    private Integer locHeight;

    @ApiModelProperty("人体区域的宽度")
    private Integer locWidth;

    @ApiModelProperty("人体区域离左边界的距离")
    private Integer locLeft;

    @ApiModelProperty("人体区域离左边界的距离")
    private Integer locTop;

    @ApiModelProperty("吸烟")
    private Integer smoke;

    @ApiModelProperty("打手机")
    private Integer cellphone;

    @ApiModelProperty("未系安全带")
    private Integer notBucklingUp;

    @ApiModelProperty("双手离开方向盘")
    private Integer bothHandsLeavingWheel;

    @ApiModelProperty("视角未看前方")
    private Integer notFacingFront;

    @ApiModelProperty("未正确佩戴口罩")
    private Integer noFaceMask;

    @ApiModelProperty("打哈欠")
    private Integer yawning;

    @ApiModelProperty("闭眼")
    private Integer eyesClosed;

    @ApiModelProperty("低头")
    private Integer headLowered;
}

