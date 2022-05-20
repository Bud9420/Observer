package com.future.observermonitor.controller;

import com.future.observercommon.dto.DeviceDTO;
import com.future.observercommon.vo.ResponseResult;
import com.future.observermonitor.service.DeviceService;
import com.future.observermonitor.service.MonitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;

@Api("监控的API")
@RestController
@RequestMapping("/monitor")
public class MonitorController {

    @Autowired
    private MonitorService monitorService;

    @Autowired
    private DeviceService deviceService;

    @ApiOperation("获取非法监控图像VO列表")
    @GetMapping
    public ResponseResult getIllegalInfoList(DeviceDTO deviceDTO) throws IOException, ParseException {
        return ResponseResult.success(monitorService.listOfImgVO(deviceDTO));
    }

    @ApiOperation("添加非法监控图片及非法信息, 通过萤石开放平台抓取监控图片，并调用百度AI接口进行对图片进行检测，获取非法监控图像VO")
    @PostMapping
    public ResponseResult detectMonitorVideo(@RequestBody DeviceDTO deviceDTO) throws Exception {
        deviceService.capture(deviceDTO);

        return ResponseResult.success(monitorService.check(deviceDTO));
    }
}