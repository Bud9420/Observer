package com.future.observermonitor.controller;

import com.future.observercommon.dto.UserDTO;
import com.future.observercommon.vo.ResponseResult;
import com.future.observermonitor.service.SecretService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitor/secrets")
public class SecretController {

    @Autowired
    private SecretService secretService;

    @ApiOperation("获取用户的AccessToken")
    @GetMapping
    public ResponseResult getAccessToken(UserDTO userDTO) throws Exception {
        return ResponseResult.success(secretService.getAccessTokenAgain(userDTO));
    }
}
