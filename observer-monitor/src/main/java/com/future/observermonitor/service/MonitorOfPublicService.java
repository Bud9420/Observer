package com.future.observermonitor.service;

import com.future.observercommon.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@FeignClient("observer-monitor-public")
@Service
public interface MonitorOfPublicService {

    void monitor(UserDTO userDTO) throws Exception;

    List<IllegalImgVo> findImgsAll(User user);
}
