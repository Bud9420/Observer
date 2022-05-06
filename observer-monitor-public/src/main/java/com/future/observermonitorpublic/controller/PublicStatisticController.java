package com.future.observermonitorpublic.controller;

import com.future.observercommon.dto.PublicStatisticDTO;
import com.future.observercommon.vo.ResponseResult;
import com.future.observermonitorpublic.service.PublicStatisticForDeviceService;
import com.future.observermonitorpublic.service.PublicStatisticForUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/monitor/statistics/public")
public class PublicStatisticController {

    @Autowired
    private PublicStatisticForUserService publicStatisticForUserService;

    @Autowired
    private PublicStatisticForDeviceService publicStatisticForDeviceService;

    @ApiOperation("按用户获取非法统计信息")
    @GetMapping("/user")
    public ResponseResult getStatisticListByUser(@RequestBody PublicStatisticDTO publicStatisticDTO) throws ParseException {
        return ResponseResult.success(publicStatisticForUserService.list(publicStatisticDTO));
    }

    @ApiOperation("按监控设备获取非法统计信息")
    @GetMapping("/device")
    public ResponseResult getStatisticListByDevice(@RequestBody PublicStatisticDTO publicStatisticDTO) throws ParseException {
        return ResponseResult.success(publicStatisticForDeviceService.list(publicStatisticDTO));
    }
}
