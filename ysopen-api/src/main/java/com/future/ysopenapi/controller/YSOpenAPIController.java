package com.future.ysopenapi.controller;

import com.future.observercommon.dto.DeviceDTO;
import com.future.observercommon.vo.ResponseResult;
import com.future.ysopenapi.service.YSOpenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "萤石开放平台的API")
@RestController
@RequestMapping("/ysopen")
public class YSOpenAPIController {

    @Autowired
    private YSOpenService ysOpenService;

    @ApiOperation(value = "获取监控AccessToken", notes = "主要用于监控视频接入")
    @GetMapping("/access-token")
    public ResponseResult getAccessToken(@RequestBody DeviceDTO deviceDTO) throws Exception {
        return ResponseResult.success(ysOpenService.getAccessToken(deviceDTO));
    }

    @ApiOperation("抓取监控图片，返回json结果")
    @GetMapping("/capture")
    public ResponseResult capture(@RequestBody DeviceDTO deviceDTO) throws Exception {
        return ResponseResult.success(ysOpenService.capture(deviceDTO));
    }
}
