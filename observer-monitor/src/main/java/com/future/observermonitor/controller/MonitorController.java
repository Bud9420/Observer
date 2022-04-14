package com.future.observermonitor.controller;

import com.future.observercommon.constant.StatusCode;
import com.future.observercommon.dto.UserDTO;
import com.future.observercommon.vo.ResponseResult;
import com.future.observermonitor.service.YSOpenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Api("监控的API")
@RestController
public class MonitorController {

    @Autowired
    private YSOpenService ysOpenService;

    @ApiOperation(value = "获取监控access_token，用于监控视频接入")
    @GetMapping("/monitor-access-token")
    public ResponseResult getMonitorToken() throws Exception {
        return ResponseResult.success(ysOpenService.getMonitorToken());
    }

    @ApiOperation(value = "添加非法监控图片及非法信息, 通过萤石开放平台抓取监控图片，并调用百度AI接口进行对图片进行检测")
    @PostMapping("/{scene}")
    public ResponseResult postMonitor(@PathVariable String scene, HttpSession session) throws Exception {
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        Integer userId = (Integer) session.getAttribute("user_id");

        switch (scene) {
            case "public":
                monitorOfPublicService.monitor(user);
                break;
            case "driving":
                monitorOfDrivingService.monitor(user);
                break;
            default:
                return ResponseResult.fail(StatusCode.BAD_REQUEST, "不存在应用场景" + scene);
        }

        return ResponseResult.success();
    }

    @ApiOperation(value = "获取非法监控图片及非法信息列表", notes = "根据session中的user参数获取当前用户的所有非法监控图片及非法信息列表")
    @GetMapping("/{scene}")
    public ResponseResult getMonitor(@PathVariable String scene, HttpSession session) {
        User user = (User) session.getAttribute("user");

        Map<String, Object> map = new HashMap<>();
        switch (scene) {
            case "public":
                List<IllegalImgVo> publicImgVos = monitorOfPublicService.findImgsAll(user);
                map.put("scene", "公共场所");
                map.put("illegalImgList", publicImgVos);
                return ResponseResult.success(map);
            case "driving":
                List<IllegalImgVo> drivingImgVos = monitorOfDrivingService.findImgsAll(user);
                map.put("scene", "驾驶行为");
                map.put("illegalImgList", drivingImgVos);
                return ResponseResult.success(map);
            default:
                return ResponseResult.failure(StatusCode.BAD_REQUEST, "不存在应用场景" + scene);
        }
    }

    @ApiOperation(value = "获取非法统计信息", notes = "根据session中的user参数获取当前用户的非法统计信息")
    @ApiImplicitParam(name = "scene", value = "应用场景", required = true)
    @GetMapping("/statis/{scene}")
    public ResponseResult getStatis(@PathVariable String scene, HttpSession session) {
        User user = (User) session.getAttribute("user");

        switch (scene) {
            case "public":
                PublicStatis publicStatis = publicStatisService.getOne(new QueryWrapper<PublicStatis>().eq("user_id", user.getId()));
                return ResponseResult.success(publicStatis);
            case "driving":
                DrivingStatis drivingStatis = drivingStatisService.getOne(new QueryWrapper<DrivingStatis>().eq("user_id", user.getId()));
                return ResponseResult.success(drivingStatis);
            default:
                return ResponseResult.failure(StatusCode.BAD_REQUEST, "不存在应用场景" + scene);
        }
    }
}
