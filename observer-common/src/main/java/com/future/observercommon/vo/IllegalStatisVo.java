package com.future.observercommon.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel("非法信息统计VO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IllegalStatisVo {

    @ApiModelProperty("统计日期")
    private Date date;

    @ApiModelProperty("非法总数")
    private Integer totalNum;

    @ApiModelProperty("未处理总数")
    private Integer untreatedNum;

    @ApiModelProperty("处理中总数")
    private Integer processingNum;

    @ApiModelProperty("已处理总数")
    private Integer processedNum;
}
