package com.future.observermonitor.controller;

import com.future.observercommon.constant.StatusCode;
import com.future.observercommon.dto.DeviceDTO;
import com.future.observercommon.dto.PublicStandardDTO;
import com.future.observercommon.vo.ResponseResult;
import com.future.observermonitor.service.DeviceService;
import com.future.observermonitor.service.PublicMonitorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monitor/standards")
public class StandardController {

    @Autowired
    private PublicMonitorService publicMonitorService;

    @ApiOperation("获取非法信息标准")
    @GetMapping("/{scene}")
    public ResponseResult getStandard(@PathVariable String scene, DeviceDTO deviceDTO) {
        switch (scene) {
            case "public":
                return ResponseResult.success(publicMonitorService.getStandard(deviceDTO).getResult());
            case "driving":

            default:
                return ResponseResult.fail(StatusCode.BAD_REQUEST, "不存在该应用场景" + scene);
        }
    }

    @ApiOperation("修改非法信息标准")
    @PutMapping("/{scene}")
    public ResponseResult putStandard(@PathVariable String scene, @RequestBody PublicStandardDTO publicStandardDTO) {
        switch (scene) {
            case "public":
                publicMonitorService.putStandard(publicStandardDTO);
                return ResponseResult.success();
            case "driving":

            default:
                return ResponseResult.fail(StatusCode.BAD_REQUEST, "不存在该应用场景" + scene);
        }
    }
}
