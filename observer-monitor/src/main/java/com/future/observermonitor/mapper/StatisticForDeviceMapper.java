package com.future.observermonitor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.future.observermonitor.po.StatisticForDevice;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface StatisticForDeviceMapper extends BaseMapper<StatisticForDevice> {

    List<StatisticForDevice> selectListByDeviceSerial(
            @Param("deviceSerial") String deviceSerial,
            @Param("begin") Date begin,
            @Param("end") Date end
    );

    StatisticForDevice selectOneByDeviceSerial(
            @Param("deviceSerial") String deviceSerial,
            @Param("date") Date date
    );

    void insertByDeviceSerial(StatisticForDevice publicStatisticForDevice);
}
