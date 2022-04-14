package com.future.observeruser.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("修改密码DTO")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ModifyPasswordDTO {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("原密码")
    private String password;

    @ApiModelProperty("新密码")
    private String newPassword;
}
