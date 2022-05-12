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

import java.lang.reflect.Field;
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
    public void add(PublicStatisticDTO publicStatisticDTO) throws IllegalAccessException {
        PublicStatisticForDevice publicStatisticForDevice = publicStatisticForDeviceMapper.selectOneByDeviceSerial(publicStatisticDTO.getDeviceSerial(), publicStatisticDTO.getDate());

        if (publicStatisticForDevice == null) {
            // 若之前未统计过当前日期的非法信息
            publicStatisticForDevice = new PublicStatisticForDevice();
            BeanUtil.copyBeanProp(publicStatisticForDevice, publicStatisticDTO);
            publicStatisticForDeviceMapper.insertByDeviceSerial(publicStatisticForDevice);
        } else {
            // 已统计过
            Field[] publicStatisticForDeviceFields = PublicStatisticForDevice.class.getDeclaredFields();
            Field[] publicStatisticDTOFields = PublicStatisticDTO.class.getDeclaredFields();
            for (int i = 4; i < publicStatisticDTOFields.length; i++) {
                publicStatisticForDeviceFields[i].setAccessible(true);
                publicStatisticDTOFields[i].setAccessible(true);

                Integer v1 = (Integer) publicStatisticForDeviceFields[i].get(publicStatisticForDevice);
                Integer v2 = (Integer) publicStatisticDTOFields[i].get(publicStatisticDTO);

                publicStatisticForDeviceFields[i].set(
                        publicStatisticForDevice,
                        v1 + v2
                );
            }
            updateById(publicStatisticForDevice);
        }
    }
}
