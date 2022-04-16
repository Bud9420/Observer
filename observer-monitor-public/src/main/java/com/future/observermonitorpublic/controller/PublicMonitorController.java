package com.future.observermonitorpublic.controller;

import com.future.observercommon.dto.DeviceDTO;
import com.future.observercommon.vo.ResponseResult;
import com.future.observermonitorpublic.service.PublicMonitorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitor-public")
public class PublicMonitorController {

    @Autowired
    private PublicMonitorService publicMonitorService;

    @ApiOperation(value = "调用百度AI接口进行对图片进行检测，添加非法监控图片及非法信息")
    @PostMapping
    public ResponseResult check(@RequestBody DeviceDTO deviceDTO) throws Exception {
        publicMonitorService.check(deviceDTO);
        return ResponseResult.success();
    }
}
