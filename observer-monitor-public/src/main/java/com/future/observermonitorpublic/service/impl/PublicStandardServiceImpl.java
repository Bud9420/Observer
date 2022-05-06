package com.future.observermonitorpublic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.observercommon.dto.DeviceDTO;
import com.future.observercommon.dto.PublicStandardDTO;
import com.future.observercommon.util.BeanUtil;
import com.future.observermonitorpublic.mapper.PublicStandardMapper;
import com.future.observermonitorpublic.po.PublicStandard;
import com.future.observermonitorpublic.service.PublicStandardService;
import com.future.observermonitorpublic.vo.PublicStandardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PublicStandardServiceImpl extends ServiceImpl<PublicStandardMapper, PublicStandard> implements PublicStandardService {

    @Autowired
    @SuppressWarnings("all")
    private PublicStandardMapper publicStandardMapper;

    @Override
    public PublicStandardVO getOne(DeviceDTO deviceDTO) {
        PublicStandard publicStandard = publicStandardMapper.selectOneByDeviceSerial(deviceDTO.getDeviceSerial());

        PublicStandardVO publicStandardVO = new PublicStandardVO();
        BeanUtil.copyBeanProp(publicStandardVO, publicStandard);

        return publicStandardVO;
    }

    @Override
    public void update(PublicStandardDTO publicStandardDTO) {
        PublicStandard publicStandard = new PublicStandard();
        BeanUtil.copyBeanProp(publicStandard, publicStandardDTO);

        publicStandardMapper.updateByDeviceSerial(publicStandard, publicStandardDTO.getDeviceSerial());
    }
}
