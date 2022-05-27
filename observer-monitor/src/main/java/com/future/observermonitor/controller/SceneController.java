package com.future.observermonitor.controller;

import com.future.observercommon.vo.ResponseResult;
import com.future.observermonitor.service.SceneService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitor/scenes")
public class SceneController {

    @Autowired
    private SceneService sceneService;

    @ApiOperation("获取所有应用场景")
    @GetMapping
    public ResponseResult getSceneList() {
        return ResponseResult.success(sceneService.listOfVO());
    }
}
