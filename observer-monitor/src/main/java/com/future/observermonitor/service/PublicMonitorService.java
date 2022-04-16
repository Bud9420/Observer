package com.future.observermonitor.service;

import com.future.observercommon.dto.DeviceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("observer-monitor-public")
@Service
public interface PublicMonitorService {

    @PostMapping("/monitor-public")
    void check(@RequestBody DeviceDTO deviceDTO) throws Exception;

    // List<IllegalImgVo> findImgsAll(User user);
}
