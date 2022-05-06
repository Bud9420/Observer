package com.future.observermonitorpublic.controller;

import com.future.observercommon.dto.DeviceDTO;
import com.future.observercommon.dto.PublicStandardDTO;
import com.future.observercommon.vo.ResponseResult;
import com.future.observermonitorpublic.service.PublicStandardService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monitor/standards/public")
public class PublicStandardController {

    @Autowired
    private PublicStandardService publicStandardService;

    @ApiOperation("获取非法信息标准")
    @GetMapping
    public ResponseResult getStandard(@RequestBody DeviceDTO deviceDTO) {
        return ResponseResult.success(publicStandardService.getOne(deviceDTO));
    }

    @ApiOperation("修改非法信息标准")
    @PutMapping
    public ResponseResult putStandard(@RequestBody PublicStandardDTO publicStandardDTO) {
        publicStandardService.update(publicStandardDTO);
        return ResponseResult.success();
    }
}
