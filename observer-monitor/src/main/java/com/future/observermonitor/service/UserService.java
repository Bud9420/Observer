package com.future.observermonitor.service;

import com.future.observercommon.dto.UserDTO;
import com.future.observercommon.vo.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("observer-user")
@Service
public interface UserService {

    @GetMapping("/users/id")
    ResponseResult getId(@RequestBody UserDTO userDTO);
}
