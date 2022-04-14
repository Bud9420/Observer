package com.future.baiduaiapi.service;

import java.io.File;
import java.io.IOException;

/**
 * 调用百度AI接口
 */
public interface BaiDuAIService {

    /**
     * <获取百度AI接口的access_token>
     *
     * @return access_token
     */
    String getAccessToken() throws IOException;

    /**
     * <通过调用百度AI接口检测图片>
     *
     * @param img 需要检测的图片
     * @param url 百度AI接口的请求url
     * @return json检测结果
     */
    String check(byte[] img, String url) throws Exception;
}
