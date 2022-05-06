package com.future.observermonitorpublic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.future.observermonitorpublic.po.PublicStatisticForDevice;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PublicStatisticForDeviceMapper extends BaseMapper<PublicStatisticForDevice> {

    List<PublicStatisticForDevice> selectListByDeviceSerial(
            @Param("deviceSerial") String deviceSerial,
            @Param("begin") Date begin,
            @Param("end") Date end
    );

    PublicStatisticForDevice selectOneByDeviceSerial(
            @Param("deviceSerial") String deviceSerial,
            @Param("date") Date date
    );

    void insertByDeviceSerial(
            @Param("publicStatisticForDevice") PublicStatisticForDevice publicStatisticForDevice,
            @Param("deviceSerial") String deviceSerial
    );
}
