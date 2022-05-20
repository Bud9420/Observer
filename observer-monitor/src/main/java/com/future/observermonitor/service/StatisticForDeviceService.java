package com.future.observermonitor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.future.observermonitor.dto.StatisticDTO;
import com.future.observermonitor.po.StatisticForDevice;
import com.future.observermonitor.vo.StatisticVO;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

public interface StatisticForDeviceService extends IService<StatisticForDevice> {

    /**
     * <获取非法统计数据>
     *
     * @param statisticDTO 非法统计数据DTO
     * @return 非法统计数据
     * @throws ParseException 日期解析异常
     */
    List<StatisticVO> list(StatisticDTO statisticDTO) throws ParseException;

    /**
     * <非法总数 + 1>
     * <未处理的非法数 + 1>
     *
     * @param statisticDTO 非法统计数据DTO
     * @throws InvocationTargetException 反射异常
     * @throws IllegalAccessException    反射异常
     */
    void add(StatisticDTO statisticDTO) throws InvocationTargetException, IllegalAccessException;
}
