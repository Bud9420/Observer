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
public class SecretVO {

    @ApiModelProperty("AppKey")
    private String appKey;

    @ApiModelProperty("AppSecret")
    private String appSecret;
}
