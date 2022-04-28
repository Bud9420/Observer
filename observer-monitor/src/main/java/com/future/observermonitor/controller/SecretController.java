package com.future.observermonitor.controller;

import com.future.observercommon.dto.DeviceDTO;
import com.future.observercommon.dto.UserDTO;
import com.future.observercommon.vo.ResponseResult;
import com.future.observermonitor.service.DeviceService;
import com.future.observermonitor.service.SecretService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitor/secret")
public class SecretController {

    @Autowired
    private SecretService secretService;

    @Autowired
    private DeviceService deviceService;

    @ApiOperation("获取用户的secret")
    @GetMapping
    public ResponseResult getSecret(UserDTO userDTO) {
        return ResponseResult.success(secretService.getOneByUserDTO(userDTO));
    }

    @ApiOperation("获取监控AccessToken，用于监控视频接入")
    @GetMapping("/access-token")
    public ResponseResult getMonitorToken(DeviceDTO deviceDTO) throws Exception {
        return ResponseResult.success(deviceService.getAccessToken(deviceDTO));
    }
}
