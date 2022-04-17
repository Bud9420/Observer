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
    private String name;

    @ApiModelProperty("设备序列号")
    private String deviceSerial;

    @ApiModelProperty("通道号")
    private Integer channelNo;
}
