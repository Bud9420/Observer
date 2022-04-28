package com.future.observermonitorpublic.controller;

import com.future.observercommon.dto.PublicStatisDTO;
import com.future.observercommon.vo.ResponseResult;
import com.future.observermonitorpublic.service.PublicStatisService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/monitor/statis/public")
public class PublicStatisController {

    @Autowired
    private PublicStatisService publicStatisService;

    @ApiOperation("获取非法统计信息")
    @GetMapping
    public ResponseResult getStatis(@RequestBody PublicStatisDTO publicStatisDTO) throws ParseException {
        return ResponseResult.success(publicStatisService.listByPublicStatisDTO(publicStatisDTO));
    }
}
