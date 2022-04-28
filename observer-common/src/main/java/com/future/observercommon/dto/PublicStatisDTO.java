package com.future.observercommon.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PublicStatisDTO {

    @ApiModelProperty("设备的主键id")
    private Integer deviceId;

    @ApiModelProperty("设备序列号")
    private String deviceSerial;

    @ApiModelProperty("统计日期")
    private Date date;

    @ApiModelProperty("查询的日期范围")
    private String dateRange;
}
