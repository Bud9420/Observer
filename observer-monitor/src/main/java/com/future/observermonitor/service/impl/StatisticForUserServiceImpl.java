package com.future.observermonitor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.observermonitor.dto.StatisticDTO;
import com.future.observercommon.util.BeanUtil;
import com.future.observercommon.util.DateUtil;
import com.future.observermonitor.mapper.StatisticForUserMapper;
import com.future.observermonitor.po.StatisticForUser;
import com.future.observermonitor.service.StatisticForUserService;
import com.future.observermonitor.vo.StatisticVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class StatisticForUserServiceImpl extends ServiceImpl<StatisticForUserMapper, StatisticForUser> implements StatisticForUserService {

    @Autowired
    @SuppressWarnings("all")
    private StatisticForUserMapper statisticForUserMapper;

    @Override
    public List<StatisticVO> list(StatisticDTO statisticDTO) throws ParseException {
        String[] dateRange = statisticDTO.getDateRange().split("->");

        Date begin = DateUtil.toDate(dateRange[0], "yyyy-MM-dd");
        Date end = DateUtil.toDate(dateRange[dateRange.length - 1], "yyyy-MM-dd");

        List<StatisticForUser> statisticForUserList = statisticForUserMapper.selectListByUsername(statisticDTO.getUsername(), begin, end);

        List<StatisticVO> statisticVOList = new ArrayList<>(statisticForUserList.size());
        BeanUtil.copyListProp(statisticVOList, statisticForUserList);

        return statisticVOList;
    }

    @Override
    public void add(StatisticDTO statisticDTO) throws IllegalAccessException, InvocationTargetException {
        StatisticForUser statisticForUser = statisticForUserMapper.selectOneByUsername(statisticDTO.getUsername(), statisticDTO.getDate());

        if (statisticForUser == null) {
            // 之前未统计过当前日期的非法信息
            statisticForUser = new StatisticForUser();
            BeanUtil.copyBeanProp(statisticForUser, statisticDTO);
            statisticForUserMapper.insertByUsername(statisticForUser);
        } else {
            // 已统计过
            List<Method> setterMethodsOfPO = BeanUtil.getSetterMethods(statisticForUser);
            List<Method> getterMethodsOfPO = BeanUtil.getGetterMethods(statisticForUser);

            List<Method> getterMethodsOfDTO = BeanUtil.getGetterMethods(statisticDTO);

            for (int i = 4; i < getterMethodsOfDTO.size(); i++) {
                Method setterMethodOfPO = setterMethodsOfPO.get(i);
                Method getterMethodOfPO = getterMethodsOfPO.get(i);

                Method getterMethodOfDTO = getterMethodsOfDTO.get(i);

                Integer fieldOfPO = (Integer) getterMethodOfPO.invoke(statisticForUser);
                fieldOfPO = fieldOfPO == null ? 0 : fieldOfPO;

                Integer fieldOfDTO = (Integer) getterMethodOfDTO.invoke(statisticDTO);
                fieldOfDTO = fieldOfDTO == null ? 0 : fieldOfDTO;

                setterMethodOfPO.invoke(statisticForUser, fieldOfDTO + fieldOfPO);
            }

            updateById(statisticForUser);
        }
    }
}
