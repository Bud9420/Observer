package com.future.observermonitorpublic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.future.observermonitorpublic.po.PublicImg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PublicImgMapper extends BaseMapper<PublicImg> {

    List<PublicImg> selectPageByDeviceSerial(IPage<PublicImg> page, String deviceSerial);

    void insertByDeviceSerial(
            @Param("publicImg") PublicImg publicImg,
            @Param("deviceSerial") String deviceSerial
    );
}
