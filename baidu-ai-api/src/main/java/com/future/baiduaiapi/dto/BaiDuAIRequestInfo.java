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

    /*
     * AccessToken的请求信息
     */
    // 请求URL
    private String urlOfAccessToken;

    // 请求参数
    private String paramsOfAccessToken;

    /*
     * AccessToken
     */
    private String accessToken;

    /*
     * 图片的请求参数
     */
    private String paramsOfImg;

    /*
     * 人体检测和属性识别的请求信息
     */
    // 请求URL
    private String urlOfHumanBodyDetection;

    /*
     * 驾驶行为分析的请求信息
     */
    // 请求URL
    private String urlOfDrivingBehavior;
}
