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

    @GetMapping("/monitor/public")
    ResponseResult getIllegalInfoAll(@RequestBody DeviceDTO deviceDTO);

    @PostMapping("/monitor/public")
    ResponseResult check(@RequestBody DeviceDTO deviceDTO) throws Exception;

    @GetMapping("/monitor/standards/public")
    ResponseResult getStandard(@RequestBody DeviceDTO deviceDTO);

    @PutMapping("/monitor/standards/public")
    ResponseResult putStandard(@RequestBody PublicStandardDTO publicStandardDTO);

    @GetMapping("/monitor/statis/public")
    ResponseResult getStatis(@RequestBody PublicStatisDTO publicStatisDTO) throws ParseException;
}
