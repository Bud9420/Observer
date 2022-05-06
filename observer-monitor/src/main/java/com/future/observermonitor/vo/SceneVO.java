package com.future.observermonitor.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("监控的应用场景VO")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SceneVO {

    @ApiModelProperty("应用场景名")
    private String sceneName;
}
