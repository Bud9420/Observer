package com.future.observermonitorpublic.service;

import com.future.observercommon.dto.DeviceDTO;
import com.future.observercommon.vo.PublicIllegalInfoVO;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * 应用场景：公共场所
 */
public interface PublicMonitorService {

    /**
     * <调用百度AI接口检测图片>
     * <根据非法信息标准，存储非法的图片及其检测信息，删除合法的图片>
     *
     * @param deviceDTO 监控设备DTO
     * @throws Exception baidu-ai服务异常或JSON解析异常
     */
    void check(DeviceDTO deviceDTO) throws Exception;

    /**
     * <获取当前设备的所有非法监控信息>
     *
     * @param deviceDTO 监控设备DTO
     * @return 当前设备的所有非法监控图片及非法信息列表
     * @throws ParseException JSON解析异常
     * @throws IOException    监控图片获取异常
     */
    List<PublicIllegalInfoVO> findIllegalInfoAll(DeviceDTO deviceDTO) throws ParseException, IOException;
}
