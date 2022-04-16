package com.future.baiduaiapi.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.future.baiduaiapi.dto.BaiDuAIRequestInfo;
import com.future.baiduaiapi.service.BaiDuAIService;
import com.future.observercommon.dto.DeviceDTO;
import com.future.observercommon.util.Base64Util;
import com.future.observercommon.util.FileUtil;
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
    public String getAccessToken() throws JsonProcessingException, IOException {
        // 获取AccessToken的完整请求地址
        String url = baiDuAIRequestInfo.getUrlOfAccessToken() + "?" + baiDuAIRequestInfo.getParamsOfAccessToken();

        // 获取json响应结果
        String result = HttpUtil.get(url);

        // 返回AccessToken
        return JacksonUtil.jsonNodeOf(result, "access_token").asText();
    }

    @Override
    public String check(DeviceDTO deviceDTO, String url) throws Exception {
        /*
         * 获取图片的字节流
         * 将字节流转换为base64编码
         * 将base64编码转换为utf-8格式
         * 将utf-8格式的base64编码包装为请求参数
         */
        byte[] img = FileUtil.receiveFile(deviceDTO.getPicUrl());
        String imgStr = Base64Util.encode(img);
        String imgParam = URLEncoder.encode(imgStr, "UTF-8");
        String params = String.format(baiDuAIRequestInfo.getParamsOfImg(), imgParam);

        /*
         * 发送post请求，获取响应结果并返回
         */
        String result = HttpUtil.post(url, baiDuAIRequestInfo.getAccessToken(), params);

        // 获取result失败
        int errorCode = JacksonUtil.jsonNodeOf(result, "error_code").asInt();
        if (errorCode == 110 || errorCode == 100) {
            // 重新获取AccessToken
            baiDuAIRequestInfo.setAccessToken(getAccessToken());
            // 重新向百度AI发送请求
            result = HttpUtil.post(url, baiDuAIRequestInfo.getAccessToken(), params);
        }

        // 返回响应结果
        return result;
    }
}
