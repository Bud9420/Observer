package com.future.observermonitor.controller;

import com.future.observercommon.constant.StatusCode;
import com.future.observercommon.dto.PublicStatisticDTO;
import com.future.observercommon.vo.ResponseResult;
import com.future.observermonitor.service.PublicMonitorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/monitor/statistics")
public class StatisticController {

    @Autowired
    private PublicMonitorService publicMonitorService;

    @ApiOperation(value = "按用户获取非法统计信息")
    @GetMapping("/{scene}/user")
    public ResponseResult getStatisticByUser(@PathVariable String scene, PublicStatisticDTO publicStatisticDTO) throws ParseException {
        switch (scene) {
            case "public":
                return ResponseResult.success(publicMonitorService.getStatisticListByUser(publicStatisticDTO).getResult());
            case "driving":

            default:
                return ResponseResult.fail(StatusCode.BAD_REQUEST, "不存在应用场景" + scene);
        }
    }

    @ApiOperation(value = "按设备获取非法统计信息")
    @GetMapping("/{scene}/device")
    public ResponseResult getStatisticByDevice(@PathVariable String scene, PublicStatisticDTO publicStatisticDTO) throws ParseException {
        switch (scene) {
            case "public":
                return ResponseResult.success(publicMonitorService.getStatisticListByDevice(publicStatisticDTO).getResult());
            case "driving":

            default:
                return ResponseResult.fail(StatusCode.BAD_REQUEST, "不存在应用场景" + scene);
        }
    }
}
