package com.future.observermonitorpublic.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@TableName("monitor_public_statis")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PublicStatis {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private Date date;

    private Integer totalNum;

    private Integer untreatedNum;

    private Integer processingNum;

    private Integer processedNum;

    private Integer userId;
}
