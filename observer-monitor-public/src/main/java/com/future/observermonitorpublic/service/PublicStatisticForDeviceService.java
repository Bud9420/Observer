package com.future.observermonitorpublic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.future.observercommon.dto.PublicStatisticDTO;
import com.future.observercommon.vo.PublicStatisticVO;
import com.future.observermonitorpublic.po.PublicStatisticForDevice;

import java.text.ParseException;
import java.util.List;

public interface PublicStatisticForDeviceService extends IService<PublicStatisticForDevice> {

    /**
     * <获取非法统计数据>
     *
     * @param publicStatisticDTO publicStatisticDTO
     * @return 非法统计数据
     * @throws ParseException 日期解析异常
     */
    List<PublicStatisticVO> list(PublicStatisticDTO publicStatisticDTO) throws ParseException;

    /**
     * <非法总数 + 1>
     * <未处理的非法数 + 1>
     *
     * @param publicStatisticDTO publicStatisticDTO
     * @throws IllegalAccessException 反射赋值异常
     */
    void add(PublicStatisticDTO publicStatisticDTO) throws IllegalAccessException;
}
