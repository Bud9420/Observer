package com.future.observermonitorpublic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.observercommon.dto.DeviceDTO;
import com.future.observercommon.dto.PublicStandardDTO;
import com.future.observercommon.util.BeanUtil;
import com.future.observermonitorpublic.mapper.PublicStandardMapper;
import com.future.observermonitorpublic.po.PublicStandard;
import com.future.observermonitorpublic.service.PublicStandardService;
import com.future.observermonitorpublic.vo.PublicStandardVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PublicStandardServiceImpl extends ServiceImpl<PublicStandardMapper, PublicStandard> implements PublicStandardService {

    @Override
    public PublicStandardVO getOneByDeviceDTO(DeviceDTO deviceDTO) {
        PublicStandard publicStandard = getOne(new QueryWrapper<PublicStandard>().eq("device_id", deviceDTO.getDeviceId()));

        PublicStandardVO publicStandardVO = new PublicStandardVO();
        BeanUtil.copyBeanProp(publicStandardVO, publicStandard);

        return publicStandardVO;
    }

    @Override
    public void updateByPublicStandardDTO(PublicStandardDTO publicStandardDTO) {
        PublicStandard publicStandard = new PublicStandard();
        BeanUtil.copyBeanProp(publicStandard, publicStandardDTO);

        update(publicStandard, new UpdateWrapper<PublicStandard>().eq("device_id", publicStandardDTO.getDeviceId()));
    }
}
