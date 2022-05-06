package com.future.observercommon.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("监控设备DTO")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeviceDTO {

    @ApiModelProperty("设备序列号")
    private String deviceSerial;

    @ApiModelProperty("通道号")
    private Integer channelNo;

    @ApiModelProperty("监控图片的网络路径")
    private String picUrl;

    @ApiModelProperty("应用场景")
    private String scene;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("萤石开放平台的AccessToken")
    private String accessToken;
}
