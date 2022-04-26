package com.future.ysopenapi.service;

import com.future.observercommon.dto.DeviceDTO;

/**
 * 调用萤石开发平台的接口
 */
public interface YSOpenService {

    /**
     * <获取萤石开放平台的AccessToken>
     *
     * @param deviceDTO 监控设备DTO
     * @return AccessToken
     * @throws Exception Http请求异常或JSON解析异常
     */
    String getAccessToken(DeviceDTO deviceDTO) throws Exception;

    /**
     * <获取监控设备抓拍的图片>
     *
     * @param deviceDTO 监控设备DTO
     * @return json响应结果
     * @throws Exception Http请求异常
     */
    String capture(DeviceDTO deviceDTO) throws Exception;

    /**
     * <获取设备的状态信息>
     *
     * @param deviceDTO 监控设备DTO
     * @return json响应结果
     * @throws Exception Http请求异常
     */
    String getDeviceInfo(DeviceDTO deviceDTO) throws Exception;
}
