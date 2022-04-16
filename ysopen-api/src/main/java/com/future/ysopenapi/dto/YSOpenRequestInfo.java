package com.future.ysopenapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 调用萤石开放平台接口时的请求信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YSOpenRequestInfo {

    /*
     * 获取AccessToken
     */
    // 请求URL
    private String urlOfAccessToken;

    // 请求参数
    private String paramsOfAccessToken;

    /*
     * 抓取监控图片
     */
    // 请求URL
    private String urlOfCapture;

    // 请求参数
    private String paramsOfCapture;
}
