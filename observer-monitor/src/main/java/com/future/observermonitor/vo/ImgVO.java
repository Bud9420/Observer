package com.future.observermonitor.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@ApiModel("非法信息VO")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImgVO {

    @ApiModelProperty("非法信息出现时间")
    private LocalDateTime createTime;

    @ApiModelProperty("监控图像的Base64编码")
    private String base64OfImg;

    @ApiModelProperty("非法类型")
    private String illegalType;

    @ApiModelProperty("非法信息的处理状态")
    private String processStatus;

    @ApiModelProperty("非法信息列表")
    private List<IllegalInfoVO> illegalInfoVOList;
}
