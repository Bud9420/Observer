package com.future.observermonitorpublic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.future.observercommon.dto.DeviceDTO;
import com.future.observercommon.dto.PublicStandardDTO;
import com.future.observermonitorpublic.po.PublicStandard;
import com.future.observermonitorpublic.vo.PublicStandardVO;

public interface PublicStandardService extends IService<PublicStandard> {

    /**
     * <根据设备id获取设备的非法信息标准>
     *
     * @param deviceDTO 设备DTO
     * @return 设备的非法信息标准
     */
    PublicStandardVO getOneByDeviceDTO(DeviceDTO deviceDTO);

    /**
     * <根据设备id更新设备的非法信息标准>
     *
     * @param publicStandardDTO 公共场所非法信息标准DTO
     */
    void updateByPublicStandardDTO(PublicStandardDTO publicStandardDTO);
}
