package com.future.ysopenapi.config;

import com.future.ysopenapi.dto.YSOpenRequestInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 调用萤石开放平台API的配置
 */
@Configuration
public class YSOpenAPIConfig {

    @Bean
    public YSOpenRequestInfo ysOpenRequestInfo() {
        YSOpenRequestInfo requestInfoDto = new YSOpenRequestInfo();

        requestInfoDto.setUrlOfAccessToken("https://open.ys7.com/api/lapp/token/get");

        String paramsOfAccessToken = "appKey=%s&appSecret=%s";
        requestInfoDto.setParamsOfAccessToken(paramsOfAccessToken);

        requestInfoDto.setUrlOfCapture("https://open.ys7.com/api/lapp/device/capture");

        String paramsOfCapture = "accessToken=%s&deviceSerial=%s&channelNo=%d";
        requestInfoDto.setParamsOfCapture(paramsOfCapture);

        return requestInfoDto;
    }
}
