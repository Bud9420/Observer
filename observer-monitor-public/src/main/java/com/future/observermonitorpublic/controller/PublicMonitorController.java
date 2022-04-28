package com.future.observermonitorpublic.controller;

import com.future.observercommon.dto.DeviceDTO;
import com.future.observercommon.dto.PublicStandardDTO;
import com.future.observercommon.vo.ResponseResult;
import com.future.observercommon.dto.PublicStatisDTO;
import com.future.observermonitorpublic.service.PublicMonitorService;
import com.future.observercommon.vo.PublicIllegalInfoVO;
import com.future.observermonitorpublic.service.PublicStatisService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/monitor-public")
public class PublicMonitorController {

    @Autowired
    private PublicMonitorService publicMonitorService;

    @Autowired
    private PublicStatisService publicStatisService;

    @ApiOperation("调用百度AI接口进行对图片进行检测，添加非法监控图片及非法信息，返回非法信息")
    @PostMapping
    public ResponseResult check(@RequestBody DeviceDTO deviceDTO) throws Exception {
        return ResponseResult.success(publicMonitorService.check(deviceDTO));
    }

    @ApiOperation("获取非法监控图片及非法信息列表")
    @GetMapping
    public ResponseResult getIllegalInfoAll(@RequestBody DeviceDTO deviceDTO) throws IOException, ParseException {
        List<PublicIllegalInfoVO> illegalInfoVOList = publicMonitorService.findIllegalInfoAll(deviceDTO);
        return ResponseResult.success(illegalInfoVOList);
    }

    @ApiOperation("修改非法信息标准")
    @PutMapping
    public ResponseResult putStandard(@RequestBody PublicStandardDTO publicStandardDTO) {
        publicMonitorService.modifyStandard(publicStandardDTO);
        return ResponseResult.success();
    }

    @ApiOperation("获取非法统计信息")
    @GetMapping("/statis")
    public ResponseResult getStatis(@RequestBody PublicStatisDTO publicStatisDTO) throws ParseException {
        return ResponseResult.success(publicStatisService.listByPublicStatisDTO(publicStatisDTO));
    }
}
