package com.future.observermonitorpublic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.future.observermonitorpublic.po.PublicStandard;
import org.apache.ibatis.annotations.Param;

public interface PublicStandardMapper extends BaseMapper<PublicStandard> {

    PublicStandard selectOneByDeviceSerial(String deviceSerial);

    void updateByDeviceSerial(
            @Param("publicStandard") PublicStandard publicStandard,
            @Param("deviceSerial") String deviceSerial
    );
}
