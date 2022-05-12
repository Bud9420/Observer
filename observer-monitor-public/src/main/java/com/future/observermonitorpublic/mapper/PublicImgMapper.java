package com.future.observermonitorpublic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.future.observermonitorpublic.po.PublicImg;

import java.util.List;

public interface PublicImgMapper extends BaseMapper<PublicImg> {

    List<PublicImg> selectPageByDeviceSerial(IPage<PublicImg> page, String deviceSerial);

    void insertByDeviceSerial(PublicImg publicImg);
}
