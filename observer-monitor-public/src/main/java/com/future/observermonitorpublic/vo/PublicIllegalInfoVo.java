package com.future.observermonitorpublic.vo;

import com.future.observermonitorpublic.po.PublicPeople;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@ApiModel("非法信息VO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicIllegalInfoVo {

    @ApiModelProperty("非法信息出现时间")
    private LocalDateTime createTime;

    @ApiModelProperty("监控图像")
    private byte[] monitorImg;

    @ApiModelProperty("非法信息处理状态")
    private String status;

    @ApiModelProperty("非法信息列表")
    private List<PublicPeople> illegalInfoList;
}
