package com.future.observeruser.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("企业VO")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompanyVO {

    @ApiModelProperty("企业名")
    private String companyName;

    @ApiModelProperty("营业执照的base64编码")
    private String license;

    @ApiModelProperty("企业法人")
    private String legalPerson;

    @ApiModelProperty("公司电话")
    private String companyPhone;

    @ApiModelProperty("公司官网")
    private String companyWebsite;
}
