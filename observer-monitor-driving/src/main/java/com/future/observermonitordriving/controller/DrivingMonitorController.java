package com.future.observermonitordriving.controller;

import com.future.observercommon.dto.UserDTO;
import com.future.observercommon.vo.ResponseResult;
import com.future.observermonitordriving.service.DrivingMonitorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/monitor-driving")
public class DrivingMonitorController {

    @Autowired
    private DrivingMonitorService drivingMonitorService;

    @ApiOperation(value = "添加非法监控图片及非法信息, 通过萤石开放平台抓取监控图片，并调用百度AI接口进行对图片进行检测")
    @PostMapping
    public ResponseResult autoMonitor(HttpSession session) throws Exception {
        UserDTO userDTO = (UserDTO) session.getAttribute("user");

        drivingMonitorService.autoMonitor(userDTO);

        return ResponseResult.success();
    }
}
