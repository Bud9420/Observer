package com.future.observermonitorpublic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.observercommon.util.BeanUtil;
import com.future.observercommon.util.DateUtil;
import com.future.observercommon.vo.PublicStatisVO;
import com.future.observercommon.dto.PublicStatisDTO;
import com.future.observermonitorpublic.mapper.PublicStatisMapper;
import com.future.observermonitorpublic.po.PublicStatis;
import com.future.observermonitorpublic.service.PublicStatisService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PublicStatisServiceImpl extends ServiceImpl<PublicStatisMapper, PublicStatis> implements PublicStatisService {

    @Override
    public void add(PublicStatisDTO publicStatisDTO) {
        /*
         * 根据用户id和统计日期，更新非法统计信息
         */
        PublicStatis statis = getOne(
                new QueryWrapper<PublicStatis>()
                        .eq("device_id", publicStatisDTO.getDeviceId())
                        .eq("date", publicStatisDTO.getDate())
        );
        if (statis == null) {
            // 若之前未统计过当前日期的非法信息
            statis = new PublicStatis();
            statis.setDate(publicStatisDTO.getDate());
            statis.setTotalNum(1);
            statis.setUntreatedNum(1);
            statis.setProcessingNum(0);
            statis.setProcessedNum(0);
            statis.setDeviceId(publicStatisDTO.getDeviceId());
            save(statis);
        } else {
            // 已统计过
            statis.setTotalNum(statis.getTotalNum() + 1);
            statis.setUntreatedNum(statis.getUntreatedNum() + 1);
            updateById(statis);
        }
    }

    @Override
    public List<PublicStatisVO> listByPublicStatisDTO(PublicStatisDTO publicStatisDTO) throws ParseException {
        String[] dateRange = publicStatisDTO.getDateRange().split("->");

        Date begin = DateUtil.toDate(dateRange[0], "yyyy-MM-dd");
        Date end = DateUtil.toDate(dateRange[dateRange.length - 1], "yyyy-MM-dd");

        List<PublicStatis> publicStatisList = list(new QueryWrapper<PublicStatis>()
                .eq("device_id", publicStatisDTO.getDeviceId())
                .between("date", begin, end)
        );

        List<PublicStatisVO> publicStatisVOList = new ArrayList<>(publicStatisList.size());

        for (PublicStatis publicStatis : publicStatisList) {
            PublicStatisVO publicStatisVO = new PublicStatisVO();
            BeanUtil.copyBeanProp(publicStatisVO, publicStatis);

            publicStatisVOList.add(publicStatisVO);
        }

        return publicStatisVOList;
    }
}
