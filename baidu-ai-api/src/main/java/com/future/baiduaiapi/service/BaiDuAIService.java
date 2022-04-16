package com.future.baiduaiapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.future.observercommon.dto.DeviceDTO;

import java.io.IOException;

/**
 * 调用百度AI接口
 */
public interface BaiDuAIService {

    /**
     * <获取百度AI接口的AccessToken>
     *
     * @return AccessToken
     * @throws JsonProcessingException JSON解析异常
     * @throws IOException             百度AI请求异常
     */
    String getAccessToken() throws IOException, JsonProcessingException;

    /**
     * <通过调用百度AI接口检测图片>
     *
     * @param deviceDTO 监控设备DTO
     * @param url        百度AI接口的请求url
     * @return json检测结果
     * @throws Exception 百度AI请求异常或JSON解析异常
     */
    String check(DeviceDTO deviceDTO, String url) throws Exception;
}
