package com.future.observermonitorpublic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.future.observermonitorpublic.po.PublicStatisticForUser;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PublicStatisticForUserMapper extends BaseMapper<PublicStatisticForUser> {

    List<PublicStatisticForUser> selectListByUsername(
            @Param("username") String username,
            @Param("begin") Date begin,
            @Param("end") Date end
    );

    PublicStatisticForUser selectOneByUsername(
            @Param("username") String username,
            @Param("date") Date date
    );

    void insertByUsername(PublicStatisticForUser publicStatisticForUser);
}
