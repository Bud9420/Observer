package com.future.observermonitor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.future.observermonitor.po.StatisticForUser;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface StatisticForUserMapper extends BaseMapper<StatisticForUser> {

    List<StatisticForUser> selectListByUsername(
            @Param("username") String username,
            @Param("begin") Date begin,
            @Param("end") Date end
    );

    StatisticForUser selectOneByUsername(
            @Param("username") String username,
            @Param("date") Date date
    );

    void insertByUsername(StatisticForUser statisticForUser);
}
