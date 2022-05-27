package com.future.observermonitor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.observermonitor.dto.StatisticDTO;
import com.future.observercommon.util.BeanUtil;
import com.future.observercommon.util.DateUtil;
import com.future.observermonitor.mapper.StatisticForDeviceMapper;
import com.future.observermonitor.po.StatisticForDevice;
import com.future.observermonitor.vo.StatisticVO;
import com.future.observermonitor.service.StatisticForDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class StatisticForDeviceServiceImpl extends ServiceImpl<StatisticForDeviceMapper, StatisticForDevice> implements StatisticForDeviceService {

    @Autowired
    @SuppressWarnings("all")
    private StatisticForDeviceMapper statisticForDeviceMapper;

    @Override
    public List<StatisticVO> list(StatisticDTO statisticDTO) throws ParseException {
        String[] dateRange = statisticDTO.getDateRange().split("->");

        Date begin = DateUtil.toDate(dateRange[0], "yyyy-MM-dd");
        Date end = DateUtil.toDate(dateRange[dateRange.length - 1], "yyyy-MM-dd");

        List<StatisticForDevice> statisticForDeviceList = statisticForDeviceMapper.selectListByDeviceSerial(statisticDTO.getDeviceSerial(), begin, end);

        List<StatisticVO> statisticVOList = new ArrayList<>(statisticForDeviceList.size());
        BeanUtil.copyListProp(statisticVOList, statisticForDeviceList, StatisticVO.class);

        return statisticVOList;
    }

    @Override
    public void add(StatisticDTO statisticDTO) throws InvocationTargetException, IllegalAccessException {
        StatisticForDevice statisticForDevice = statisticForDeviceMapper.selectOneByDeviceSerial(statisticDTO.getDeviceSerial(), statisticDTO.getDate());

        if (statisticForDevice == null) {
            // 之前未统计过当前日期的非法信息
            statisticForDevice = new StatisticForDevice();
            BeanUtil.copyBeanProp(statisticForDevice, statisticDTO);
            statisticForDeviceMapper.insertByDeviceSerial(statisticForDevice);
        } else {
            // 已统计过
            Field[] fieldsOfPO = statisticForDevice.getClass().getDeclaredFields();
            Field[] fieldsOfDTO = statisticDTO.getClass().getDeclaredFields();

            for (int i = 4; i < fieldsOfDTO.length; i++) {
                fieldsOfDTO[i].setAccessible(true);
                Integer fieldValueOfDTO = (Integer) fieldsOfDTO[i].get(statisticDTO);
                fieldValueOfDTO = fieldValueOfDTO == null ? 0 : fieldValueOfDTO;

                fieldsOfPO[i].setAccessible(true);
                Integer fieldValueOfPO = (Integer) fieldsOfPO[i].get(statisticForDevice);
                fieldValueOfPO = fieldValueOfPO == null ? 0 : fieldValueOfPO;

                fieldsOfPO[i].set(statisticForDevice, fieldValueOfDTO + fieldValueOfPO);
            }

            updateById(statisticForDevice);
        }
    }
}
