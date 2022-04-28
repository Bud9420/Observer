package com.future.observermonitor.controller;

import com.future.observercommon.constant.StatusCode;
import com.future.observercommon.dto.DeviceDTO;
import com.future.observercommon.dto.UserDTO;
import com.future.observercommon.vo.ResponseResult;
import com.future.observermonitor.service.DeviceService;
import com.future.observermonitor.service.PublicMonitorService;
import com.future.observermonitor.service.SceneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("监控的API")
@RestController
@RequestMapping("/monitor")
public class MonitorController {

    @Autowired
    private SceneService sceneService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private PublicMonitorService publicMonitorService;

    @ApiOperation("获取用户拥有的所有应用场景")
    @GetMapping("/scenes")
    public ResponseResult getSceneList(UserDTO userDTO) {
        return ResponseResult.success(sceneService.listByUserDTO(userDTO));
    }

    @ApiOperation("获取非法监控图片及非法信息列表")
    @GetMapping("/{scene}")
    public ResponseResult getIllegalInfoAll(@PathVariable String scene, DeviceDTO deviceDTO) {
        deviceService.getId(deviceDTO);

        switch (scene) {
            case "public":
                return ResponseResult.success(publicMonitorService.getIllegalInfoAll(deviceDTO).getResult());
            case "driving":

            default:
                return ResponseResult.fail(StatusCode.BAD_REQUEST, "不存在该应用场景" + scene);
        }
    }

    @ApiOperation("添加非法监控图片及非法信息, 通过萤石开放平台抓取监控图片，并调用百度AI接口进行对图片进行检测，返回非法信息")
    @PostMapping("/{scene}")
    public ResponseResult detectMonitorVideo(@PathVariable String scene, @RequestBody DeviceDTO deviceDTO) throws Exception {
        deviceService.capture(deviceDTO);
        deviceDTO.setScene(scene);

        switch (scene) {
            case "public":
                return publicMonitorService.check(deviceDTO);
            case "driving":

            default:
                return ResponseResult.fail(StatusCode.BAD_REQUEST, "不存在该应用场景" + scene);
        }
    }
}