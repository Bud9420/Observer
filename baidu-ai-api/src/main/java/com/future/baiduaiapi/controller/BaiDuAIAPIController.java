package com.future.baiduaiapi.controller;

import com.future.baiduaiapi.dto.BaiDuAIRequestInfo;
import com.future.baiduaiapi.service.BaiDuAIService;
import com.future.observercommon.constant.StatusCode;
import com.future.observercommon.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("百度AI的API")
@RestController
@RequestMapping("baidu-ai")
public class BaiDuAIAPIController {

    @Autowired
    private BaiDuAIService baiDuAIService;

    @Autowired
    private BaiDuAIRequestInfo baiDuAIRequestInfo;

    @ApiOperation("上传图片，返回检测结果")
    @PostMapping("/{scene}")
    public ResponseResult checkImg(@PathVariable String scene, byte[] img) throws Exception {
        switch (scene) {
            case "public":
                return ResponseResult.success(
                        baiDuAIService.check(img, baiDuAIRequestInfo.getURLOfHumanBodyDetection())
                );
            case "driving":
                return ResponseResult.success(
                        baiDuAIService.check(img, baiDuAIRequestInfo.getURLOfDrivingBehavior())
                );
            default:
                return ResponseResult.fail(StatusCode.BAD_REQUEST, "不存在该应用场景：" + scene);
        }
    }
}
