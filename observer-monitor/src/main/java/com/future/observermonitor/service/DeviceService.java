package com.future.observermonitor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.future.observercommon.dto.DeviceDTO;
import com.future.observermonitor.po.Device;

public interface DeviceService extends IService<Device> {

    /**
     * <获取萤石开放平台的AccessToken>
     * <将AccessToken保存至数据库>
     *
     * @param deviceDTO 监控设备DTO
     * @return 监控AccessToken
     * @throws Exception ysopen-api服务异常
     */
    String getAccessToken(DeviceDTO deviceDTO) throws Exception;

    /**
     * <通过萤石开放平台抓取监控图片>
     * <将监控图片的网络路径保存至deviceDTO>
     * <将监控设备的主键id保存至deviceDTO>
     *
     * @param deviceDTO 监控设备DTO
     * @throws Exception ysopen-api服务异常或JSON解析异常
     */
    void capture(DeviceDTO deviceDTO) throws Exception;
}
