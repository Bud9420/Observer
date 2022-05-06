package com.future.observermonitorpublic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.observercommon.dto.PublicStatisticDTO;
import com.future.observercommon.util.BeanUtil;
import com.future.observercommon.util.DateUtil;
import com.future.observercommon.vo.PublicStatisticVO;
import com.future.observermonitorpublic.mapper.PublicStatisticForDeviceMapper;
import com.future.observermonitorpublic.po.PublicStatisticForDevice;
import com.future.observermonitorpublic.service.PublicStatisticForDeviceService;
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
public class PublicStatisticForDeviceServiceImpl extends ServiceImpl<PublicStatisticForDeviceMapper, PublicStatisticForDevice> implements PublicStatisticForDeviceService {

    @Autowired
    @SuppressWarnings("all")
    private PublicStatisticForDeviceMapper publicStatisticForDeviceMapper;

    @Override
    public List<PublicStatisticVO> list(PublicStatisticDTO publicStatisticDTO) throws ParseException {
        String[] dateRange = publicStatisticDTO.getDateRange().split("->");

        Date begin = DateUtil.toDate(dateRange[0], "yyyy-MM-dd");
        Date end = DateUtil.toDate(dateRange[dateRange.length - 1], "yyyy-MM-dd");

        List<PublicStatisticForDevice> publicStatisticForDeviceList = publicStatisticForDeviceMapper.selectListByDeviceSerial(publicStatisticDTO.getDeviceSerial(), begin, end);

        List<PublicStatisticVO> publicStatisticVOList = new ArrayList<>(publicStatisticForDeviceList.size());
        for (PublicStatisticForDevice publicStatisticForDevice : publicStatisticForDeviceList) {
            PublicStatisticVO publicStatisticVO = new PublicStatisticVO();
            BeanUtil.copyBeanProp(publicStatisticVO, publicStatisticForDevice);

            publicStatisticVOList.add(publicStatisticVO);
        }

        return publicStatisticVOList;
    }

    @Override
    public void add(PublicStatisticDTO publicStatisticDTO) {
        PublicStatisticForDevice statistic = publicStatisticForDeviceMapper.selectOneByDeviceSerial(publicStatisticDTO.getDeviceSerial(), publicStatisticDTO.getDate());

        if (statistic == null) {
            // 若之前未统计过当前日期的非法信息
            statistic = new PublicStatisticForDevice();
            statistic.setDate(publicStatisticDTO.getDate());
            statistic.setTotalNum(1);
            statistic.setUntreatedNum(1);
            statistic.setProcessingNum(0);
            statistic.setProcessedNum(0);
            publicStatisticForDeviceMapper.insertByDeviceSerial(statistic,publicStatisticDTO.getDeviceSerial());
        } else {
            // 已统计过
            statistic.setTotalNum(statistic.getTotalNum() + 1);
            statistic.setUntreatedNum(statistic.getUntreatedNum() + 1);
            statistic.setDeviceId(null);
            updateById(statistic);
        }
    }
}
