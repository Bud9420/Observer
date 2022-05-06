package com.future.ysopenapi.controller;

import com.future.observercommon.dto.DeviceDTO;
import com.future.observercommon.dto.SecretDTO;
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

    @ApiOperation("获取监控AccessToken")
    @GetMapping("/access-token")
    public ResponseResult getAccessToken(@RequestBody SecretDTO secretDTO) throws Exception {
        return ResponseResult.success(ysOpenService.getAccessToken(secretDTO));
    }

    @ApiOperation("抓取监控图片，返回json结果")
    @GetMapping("/capture")
    public ResponseResult capture(@RequestBody DeviceDTO deviceDTO) throws Exception {
        return ResponseResult.success(ysOpenService.capture(deviceDTO));
    }

    @ApiOperation("获取设备的状态信息，返回json结果")
    @GetMapping("/device-info")
    public ResponseResult getDeviceInfo(@RequestBody DeviceDTO deviceDTO) throws Exception {
        return ResponseResult.success(ysOpenService.getDeviceInfo(deviceDTO));
    }
}
