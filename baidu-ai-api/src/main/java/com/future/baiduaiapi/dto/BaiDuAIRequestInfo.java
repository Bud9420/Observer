package com.future.baiduaiapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 调用百度AI接口时的请求信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaiDuAIRequestInfo {

    /**
     * access_token的请求信息
     */
    // 获取access_token的URL
    private String getURLOfAccessToken;

    // 获取access_token的固定参数
    private String getGrantTypeOfAccessToken;

    // API Key
    private String APIKey;

    // Secret Key
    private String SecretKey;

    /**
     * access_token
     */
    private String accessToken;

    /**
     * 人体检测和属性识别的请求信息
     */
    // 请求URL
    private String URLOfHumanBodyDetection;

    /**
     * 驾驶行为分析的请求信息
     */
    // 请求URL
    private String URLOfDrivingBehavior;
}
