package com.future.ysopenapi.controller;

import com.future.observercommon.dto.DeviceDTO;
import com.future.observercommon.vo.ResponseResult;
import com.future.ysopenapi.service.YSOpenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "萤石开放平台的API")
@RestController
@RequestMapping("/ysopen")
public class YSOpenAPIController {

    @Autowired
    private YSOpenService ysOpenService;

    @ApiOperation(value = "获取监控access_token", notes = "主要用于监控视频接入")
    @GetMapping("/access-token")
    public ResponseResult getMonitorToken(DeviceDTO deviceDTO) throws Exception {
        return ResponseResult.success(ysOpenService.getAccessToken(deviceDTO));
    }

    @ApiOperation("抓取监控图片，返回json结果")
    @GetMapping("/monitor-img")
    public ResponseResult getMonitorImg(DeviceDTO deviceDTO) throws Exception {
        return ResponseResult.success(ysOpenService.capture(deviceDTO));
    }
}
