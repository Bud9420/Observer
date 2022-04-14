package com.future.observermonitorpublic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.observermonitorpublic.dto.PublicStatisDTO;
import com.future.observermonitorpublic.mapper.PublicStatisMapper;
import com.future.observermonitorpublic.po.PublicStatis;
import com.future.observermonitorpublic.service.PublicStatisService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
                        .eq("user_id", publicStatisDTO.getUserId())
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
            statis.setUserId(publicStatisDTO.getUserId());
            save(statis);
        } else {
            // 已统计过
            statis.setTotalNum(statis.getTotalNum() + 1);
            statis.setUntreatedNum(statis.getUntreatedNum() + 1);
            updateById(statis);
        }
    }
}
