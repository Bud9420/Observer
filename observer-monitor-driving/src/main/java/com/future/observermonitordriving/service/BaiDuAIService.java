package com.future.observermonitordriving.service;

import com.future.observercommon.vo.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("baidu-ai-api")
@Service
public interface BaiDuAIService {

    @GetMapping("/{scene}")
    ResponseResult checkImg(@PathVariable String scene, byte[] img) throws Exception;
}
