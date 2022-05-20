package com.future.observermonitor.controller;

import com.future.observermonitor.dto.StatisticDTO;
import com.future.observercommon.vo.ResponseResult;
import com.future.observermonitor.service.StatisticForDeviceService;
import com.future.observermonitor.service.StatisticForUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/monitor/statistics")
public class StatisticController {

    @Autowired
    private StatisticForUserService statisticForUserService;

    @Autowired
    private StatisticForDeviceService statisticForDeviceService;

    @ApiOperation(value = "按用户获取非法统计数据")
    @GetMapping("/user")
    public ResponseResult getStatisticListByUser(StatisticDTO statisticDTO) throws ParseException {
        return ResponseResult.success(statisticForUserService.list(statisticDTO));
    }

    @ApiOperation(value = "按设备获取非法统计数据")
    @GetMapping("/device")
    public ResponseResult getStatisticListByDevice(StatisticDTO statisticDTO) throws ParseException {
        return ResponseResult.success(statisticForDeviceService.list(statisticDTO));
    }
}
