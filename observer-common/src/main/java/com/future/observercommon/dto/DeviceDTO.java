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

    @ApiModelProperty("萤石开放平台的AppKey")
    private String appKey;

    @ApiModelProperty("萤石开放平台的AppSecret")
    private String appSecret;

    @ApiModelProperty("萤石开放平台的AccessToken")
    private String accessToken;

    @ApiModelProperty("设备名称")
    private String name;

    @ApiModelProperty("设备序列号")
    private String deviceSerial;

    @ApiModelProperty("通道号")
    private String channelNo;
}
