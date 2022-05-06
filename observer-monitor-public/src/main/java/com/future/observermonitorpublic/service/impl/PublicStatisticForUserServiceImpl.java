package com.future.observermonitorpublic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.observercommon.dto.PublicStatisticDTO;
import com.future.observercommon.util.BeanUtil;
import com.future.observercommon.util.DateUtil;
import com.future.observercommon.vo.PublicStatisticVO;
import com.future.observermonitorpublic.mapper.PublicStatisticForUserMapper;
import com.future.observermonitorpublic.po.PublicStatisticForUser;
import com.future.observermonitorpublic.service.PublicStatisticForUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PublicStatisticForUserServiceImpl extends ServiceImpl<PublicStatisticForUserMapper, PublicStatisticForUser> implements PublicStatisticForUserService {

    @Autowired
    @SuppressWarnings("all")
    private PublicStatisticForUserMapper publicStatisticForUserMapper;

    @Override
    public List<PublicStatisticVO> list(PublicStatisticDTO publicStatisticDTO) throws ParseException {
        String[] dateRange = publicStatisticDTO.getDateRange().split("->");

        Date begin = DateUtil.toDate(dateRange[0], "yyyy-MM-dd");
        Date end = DateUtil.toDate(dateRange[dateRange.length - 1], "yyyy-MM-dd");

        List<PublicStatisticForUser> publicStatisticForUserList = publicStatisticForUserMapper.selectListByUsername(publicStatisticDTO.getUsername(), begin, end);

        List<PublicStatisticVO> publicStatisticVOList = new ArrayList<>(publicStatisticForUserList.size());
        for (PublicStatisticForUser publicStatisticForUser : publicStatisticForUserList) {
            PublicStatisticVO publicStatisticVO = new PublicStatisticVO();
            BeanUtil.copyBeanProp(publicStatisticVO, publicStatisticForUser);

            publicStatisticVOList.add(publicStatisticVO);
        }

        return publicStatisticVOList;
    }

    @Override
    public void add(PublicStatisticDTO publicStatisticDTO) {
        PublicStatisticForUser statistic = publicStatisticForUserMapper.selectOneByUsername(publicStatisticDTO.getUsername(), publicStatisticDTO.getDate());
        if (statistic == null) {
            // 未统计过当前日期的非法信息
            statistic = new PublicStatisticForUser();
            statistic.setDate(publicStatisticDTO.getDate());
            statistic.setTotalNum(1);
            statistic.setUntreatedNum(1);
            statistic.setProcessingNum(0);
            statistic.setProcessedNum(0);
            publicStatisticForUserMapper.insertByUsername(statistic, publicStatisticDTO.getUsername());
        } else {
            // 已统计过
            statistic.setTotalNum(statistic.getTotalNum() + 1);
            statistic.setUntreatedNum(statistic.getUntreatedNum() + 1);
            statistic.setUserId(null);
            updateById(statistic);
        }
    }
}
