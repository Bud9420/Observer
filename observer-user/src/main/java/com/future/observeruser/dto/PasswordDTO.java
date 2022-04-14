package com.future.observeruser.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("密码DTO")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PasswordDTO {

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("盐值")
    private String salt;
}
