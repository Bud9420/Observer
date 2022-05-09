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
    public BaiDuAIRequestInfo baiDuAIRequestInfo() {
        BaiDuAIRequestInfo requestInfoDto = new BaiDuAIRequestInfo();

        requestInfoDto.setUrlOfAccessToken("https://aip.baidubce.com/oauth/2.0/token");
        requestInfoDto.setParamsOfAccessToken(
                String.format(
                        "grant_type=%s&client_id=%s&client_secret=%s",
                        "client_credentials",
                        "qPxbmd9wggAuuFqIF9GuuAgs",
                        "v3i9GbvBNbIAG5nteNULjyh0vju3MrQA"
                )
        );

        requestInfoDto.setParamsOfImg("image=%s");

        requestInfoDto.setUrlOfHumanBodyDetection("https://aip.baidubce.com/rest/2.0/image-classify/v1/body_attr");

        requestInfoDto.setUrlOfDrivingBehavior("https://aip.baidubce.com/rest/2.0/image-classify/v1/driver_behavior");

        return requestInfoDto;
    }
}
