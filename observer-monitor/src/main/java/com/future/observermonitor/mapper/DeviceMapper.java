package com.future.observermonitor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.future.observermonitor.po.Device;

import java.util.List;

public interface DeviceMapper extends BaseMapper<Device> {

    List<Device> selectOneByUsername(String username);
}
