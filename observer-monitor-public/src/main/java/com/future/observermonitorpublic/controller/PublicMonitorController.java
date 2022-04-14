package com.future.observermonitorpublic.controller;

import com.future.observercommon.dto.UserDTO;
import com.future.observercommon.vo.ResponseResult;
import com.future.observermonitorpublic.service.PublicMonitorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitor-public")
public class PublicMonitorController {

    @Autowired
    private PublicMonitorService publicMonitorService;

    @ApiOperation(value = "添加非法监控图片及非法信息, 通过萤石开放平台抓取监控图片，并调用百度AI接口进行对图片进行检测")
    @PostMapping
    public ResponseResult autoMonitor(UserDTO userDTO) throws Exception {
        publicMonitorService.autoMonitor(userDTO);
        return ResponseResult.success();
    }
}
