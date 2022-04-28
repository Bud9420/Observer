package com.future.observermonitor.controller;

import com.future.observercommon.constant.StatusCode;
import com.future.observercommon.dto.PublicStatisDTO;
import com.future.observercommon.vo.ResponseResult;
import com.future.observermonitor.service.DeviceService;
import com.future.observermonitor.service.PublicMonitorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/monitor/statis")
public class StatisController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private PublicMonitorService publicMonitorService;

    @ApiOperation(value = "获取非法统计信息")
    @GetMapping("/{scene}")
    public ResponseResult getStatis(@PathVariable String scene, PublicStatisDTO publicStatisDTO) throws ParseException {
        deviceService.getId(publicStatisDTO);

        switch (scene) {
            case "public":
                return ResponseResult.success(publicMonitorService.getStatis(publicStatisDTO).getResult());
            case "driving":

            default:
                return ResponseResult.fail(StatusCode.BAD_REQUEST, "不存在应用场景" + scene);
        }
    }
}
