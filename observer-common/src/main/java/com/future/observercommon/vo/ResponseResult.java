package com.future.observercommon.vo;

import com.future.observercommon.constant.StatusCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("响应结果")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseResult {

    @ApiModelProperty("响应码")
    private Integer code;

    @ApiModelProperty("响应消息")
    private String message;

    @ApiModelProperty("响应结果")
    private Object result;

    public static ResponseResult success(Object result) {
        ResponseResult rs = new ResponseResult();
        rs.setCode(StatusCode.OK.getCode());
        rs.setMessage("成功");
        rs.setResult(result);
        return rs;
    }

    public static ResponseResult success() {
        ResponseResult rs = new ResponseResult();
        rs.setCode(StatusCode.OK.getCode());
        rs.setMessage("成功");
        return rs;
    }

    public static ResponseResult success(String msg) {
        ResponseResult rs = new ResponseResult();
        rs.setCode(StatusCode.OK.getCode());
        rs.setMessage(msg);
        return rs;
    }

    public static ResponseResult fail(StatusCode statusCode, String message) {
        ResponseResult rs = new ResponseResult();
        rs.setCode(statusCode.getCode());
        rs.setMessage(message);
        return rs;
    }
}
