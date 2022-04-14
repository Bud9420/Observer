package com.future.observermonitorpublic.vo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@ApiModel("公共场所非法信息的统计结果（以天为统计单位）pojo")
@TableName("monitor_public_statis")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicStatis {

    @ApiModelProperty("主键id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

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

    @ApiModelProperty("用户id")
    private Integer userId;
}
