package com.future.observermonitor.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("SecretVO")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SceneVO {

    @ApiModelProperty("应用场景名")
    private String sceneName;

    @ApiModelProperty("用户在当前应用场景下，拥有的监控设备总数量")
    private Integer deviceNum;
}
