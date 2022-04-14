package com.future.observercommon.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("企业DTO")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompanyDTO {

    @ApiModelProperty("企业名")
    private String name;

    @ApiModelProperty("营业执照")
    private String license;

    @ApiModelProperty("法人")
    private String legalPerson;

    @ApiModelProperty("企业电话号码")
    private String phone;

    @ApiModelProperty("企业官网")
    private String companyWebsite;
}
