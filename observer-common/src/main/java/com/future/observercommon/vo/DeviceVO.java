package com.future.observercommon.vo;

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
}
