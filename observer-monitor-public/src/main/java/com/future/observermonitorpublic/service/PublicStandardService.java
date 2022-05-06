package com.future.observermonitorpublic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.future.observercommon.dto.DeviceDTO;
import com.future.observercommon.dto.PublicStandardDTO;
import com.future.observermonitorpublic.po.PublicStandard;
import com.future.observermonitorpublic.vo.PublicStandardVO;

public interface PublicStandardService extends IService<PublicStandard> {

    /**
     * <根据设备序列号获取设备的非法信息标准>
     *
     * @param deviceDTO deviceDTO
     * @return 设备的非法信息标准
     */
    PublicStandardVO getOne(DeviceDTO deviceDTO);

    /**
     * <根据设备序列号更新设备的非法信息标准>
     *
     * @param publicStandardDTO publicStandardDTO
     */
    void update(PublicStandardDTO publicStandardDTO);
}
