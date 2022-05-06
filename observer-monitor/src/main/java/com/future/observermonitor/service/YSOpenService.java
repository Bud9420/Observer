package com.future.observermonitor.service;

import com.future.observercommon.dto.DeviceDTO;
import com.future.observercommon.dto.SecretDTO;
import com.future.observercommon.vo.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("ysopen-api")
@Service
public interface YSOpenService {

    @GetMapping("/ysopen/access-token")
    ResponseResult getAccessToken(@RequestBody SecretDTO secretDTO) throws Exception;

    @GetMapping("/ysopen/capture")
    ResponseResult capture(@RequestBody DeviceDTO deviceDTO) throws Exception;

    @GetMapping("/ysopen/device-info")
    ResponseResult getDeviceInfo(@RequestBody DeviceDTO deviceDTO) throws Exception;
}
