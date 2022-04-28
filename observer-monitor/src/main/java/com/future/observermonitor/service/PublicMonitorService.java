package com.future.observermonitor.service;

import com.future.observercommon.dto.DeviceDTO;
import com.future.observercommon.dto.PublicStandardDTO;
import com.future.observercommon.dto.PublicStatisDTO;
import com.future.observercommon.vo.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;

@FeignClient("observer-monitor-public")
@Service
public interface PublicMonitorService {

    @PostMapping("/monitor-public")
    ResponseResult check(@RequestBody DeviceDTO deviceDTO) throws Exception;

    @GetMapping("/monitor-public")
    ResponseResult getIllegalInfoAll(@RequestBody DeviceDTO deviceDTO);

    @PutMapping("/monitor-public")
    ResponseResult putStandard(@RequestBody PublicStandardDTO publicStandardDTO);

    @GetMapping("/monitor-public/statis")
    ResponseResult getStatis(@RequestBody PublicStatisDTO publicStatisDTO) throws ParseException;
}
