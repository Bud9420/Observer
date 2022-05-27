package com.future.observermonitor.controller;

import com.future.observercommon.dto.DeviceDTO;
import com.future.observercommon.dto.UserDTO;
import com.future.observercommon.vo.ResponseResult;
import com.future.observermonitor.service.DeviceService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monitor/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @ApiOperation("获取用户的所有设备")
    @GetMapping
    public ResponseResult getDeviceList(UserDTO userDTO) throws Exception {
        return ResponseResult.success(deviceService.list(userDTO));
    }

    @ApiModelProperty("更新设备")
    @PutMapping
    public ResponseResult putDevice(@RequestBody DeviceDTO deviceDTO) {
        deviceService.update(deviceDTO);

        return ResponseResult.success();
    }
}
