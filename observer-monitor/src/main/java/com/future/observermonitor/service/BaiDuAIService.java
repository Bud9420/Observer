package com.future.observermonitor.service;

import com.future.observercommon.dto.DeviceDTO;
import com.future.observercommon.vo.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("baidu-ai-api")
@Service
public interface BaiDuAIService {

    @GetMapping("baidu-ai")
    ResponseResult check(@RequestBody DeviceDTO deviceDTO) throws Exception;
}
