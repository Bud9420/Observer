package com.future.observercommon.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@ApiModel("公共场所非法信息VO")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PublicIllegalInfoVO {

    @ApiModelProperty("非法信息出现时间")
    private LocalDateTime createTime;

    @ApiModelProperty("监控图像的Base64编码")
    private String monitorImg;

    @ApiModelProperty("非法信息处理状态")
    private String status;

    @ApiModelProperty("非法信息列表")
    private List<PublicPeopleVO> illegalInfoList;
}
