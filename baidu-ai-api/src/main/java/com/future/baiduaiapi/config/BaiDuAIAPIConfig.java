package com.future.baiduaiapi.config;

import com.future.baiduaiapi.dto.BaiDuAIRequestInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 调用百度AI接口的配置
 */
@Configuration
public class BaiDuAIAPIConfig {

    @Bean
    public BaiDuAIRequestInfo baiDuAIRequestInfoDto() {
        BaiDuAIRequestInfo requestInfoDto = new BaiDuAIRequestInfo();

        requestInfoDto.setGetURLOfAccessToken("https://aip.baidubce.com/oauth/2.0/token");
        requestInfoDto.setGetGrantTypeOfAccessToken("client_credentials");
        requestInfoDto.setAPIKey("UhHirGcxb0Td337DKyyx3Vq4");
        requestInfoDto.setSecretKey("1okNz3NNfYfw2ASvhKq8i6SrrGUSI6hv");

        requestInfoDto.setURLOfHumanBodyDetection("https://aip.baidubce.com/rest/2.0/image-classify/v1/body_attr");

        requestInfoDto.setURLOfDrivingBehavior("https://aip.baidubce.com/rest/2.0/image-classify/v1/driver_behavior");

        return requestInfoDto;
    }
}
