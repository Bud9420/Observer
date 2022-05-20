package com.future.observermonitor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.future.observermonitor.po.Img;

import java.util.List;

public interface ImgMapper extends BaseMapper<Img> {

    List<Img> selectPageByDeviceSerial(IPage<Img> page, String deviceSerial);

    void insertByDeviceSerial(Img publicImg);
}
