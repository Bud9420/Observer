package com.future.observermonitor.controller;

import com.future.observercommon.constant.StatusCode;
import com.future.observercommon.dto.DeviceDTO;
import com.future.observercommon.dto.UserDTO;
import com.future.observercommon.vo.ResponseResult;
import com.future.observermonitor.service.DeviceService;
// import com.future.observermonitor.service.DrivingMonitorService;
import com.future.observermonitor.service.PublicMonitorService;
import com.future.observermonitor.service.SecretService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Api("监控的API")
@RestController
@RequestMapping("/monitor")
public class MonitorController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private SecretService secretService;

    @Autowired
    private PublicMonitorService publicMonitorService;

    // @Autowired
    // private DrivingMonitorService drivingMonitorService;

    @ApiOperation("获取用户的所有设备")
    @GetMapping("/devices")
    public ResponseResult getDeviceList(@RequestBody UserDTO userDTO) {
        return ResponseResult.success(deviceService.listByUserDTO(userDTO));
    }

    @ApiOperation("获取用户的secret")
    @GetMapping("/secret")
    public ResponseResult getSecret(@RequestBody UserDTO userDTO) {
        return ResponseResult.success(secretService.getOneByUserDTO(userDTO));
    }

    @ApiOperation("获取监控AccessToken，用于监控视频接入")
    @GetMapping("/access-token")
    public ResponseResult getMonitorToken(DeviceDTO deviceDTO) throws Exception {
        return ResponseResult.success(deviceService.getAccessToken(deviceDTO));
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

    @ApiOperation("获取非法监控图片及非法信息列表")
    @GetMapping("/{scene}")
    public ResponseResult getIllegalInfoAll(@PathVariable String scene, @RequestBody DeviceDTO deviceDTO) {
        deviceService.getId(deviceDTO);

        switch (scene) {
            case "public":
                return ResponseResult.success(publicMonitorService.illegalInfoVOList(deviceDTO).getResult());
            case "driving":
                // List<IllegalImgVo> drivingImgVos = monitorOfDrivingService.findImgsAll(user);
                // return ResponseResult.success(map);
            default:
                return ResponseResult.fail(StatusCode.BAD_REQUEST, "不存在该应用场景" + scene);
        }
    }

    // @ApiOperation(value = "获取非法统计信息", notes = "根据session中的user参数获取当前用户的非法统计信息")
    // @ApiImplicitParam(name = "scene", value = "应用场景", required = true)
    // @GetMapping("/statis/{scene}")
    // public ResponseResult getStatis(@PathVariable String scene, HttpSession session) {
    //     User user = (User) session.getAttribute("user");
    //
    //     switch (scene) {
    //         case "public":
    //             PublicStatis publicStatis = publicStatisService.getOne(new QueryWrapper<PublicStatis>().eq("user_id", user.getId()));
    //             return ResponseResult.success(publicStatis);
    //         case "driving":
    //             DrivingStatis drivingStatis = drivingStatisService.getOne(new QueryWrapper<DrivingStatis>().eq("user_id", user.getId()));
    //             return ResponseResult.success(drivingStatis);
    //         default:
    //             return ResponseResult.failure(StatusCode.BAD_REQUEST, "不存在应用场景" + scene);
    //     }
    // }
}
