package com.future.baiduaiapi.service.impl;

import com.future.baiduaiapi.dto.BaiDuAIRequestInfo;
import com.future.baiduaiapi.service.BaiDuAIService;
import com.future.observercommon.util.Base64Util;
import com.future.observercommon.util.HttpUtil;
import com.future.observercommon.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URLEncoder;

@Service
public class BaiDuAIServiceImpl implements BaiDuAIService {

    @Autowired
    private BaiDuAIRequestInfo baiDuAIRequestInfo;

    @PostConstruct
    public void init() throws IOException {
        baiDuAIRequestInfo.setAccessToken(getAccessToken());
    }

    @Override
    public String getAccessToken() throws IOException {
        // 获取access_token的完整请求地址
        String url = baiDuAIRequestInfo.getGetURLOfAccessToken()
                + "?grant_type=" + baiDuAIRequestInfo.getGetGrantTypeOfAccessToken()
                + "&client_id=" + baiDuAIRequestInfo.getAPIKey()
                + "&client_secret=" + baiDuAIRequestInfo.getSecretKey();

        String result = HttpUtil.get(url);

        // 返回access_token
        return JacksonUtil.jsonNodeOf(result, "access_token").asText();
    }

    @Override
    public String check(byte[] img, String url) throws Exception {
        String imgStr = Base64Util.encode(img);
        String imgParam = URLEncoder.encode(imgStr, "UTF-8");
        String params = "image=" + imgParam;

        String result = HttpUtil.post(url, baiDuAIRequestInfo.getAccessToken(), params);

        // 获取result失败
        int errorCode = JacksonUtil.jsonNodeOf(result, "error_code").asInt();
        if (errorCode == 110 || errorCode == 100) {
            // 重新获取accessToken
            baiDuAIRequestInfo.setAccessToken(getAccessToken());
            // 重新向百度AI发送请求
            result = HttpUtil.post(url, baiDuAIRequestInfo.getAccessToken(), params);
        }

        return result;
    }
}
