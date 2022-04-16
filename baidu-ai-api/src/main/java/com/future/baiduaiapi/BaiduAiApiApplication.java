package com.future.baiduaiapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.future.baiduaiapi", "com.future.observercommon"})
@EnableEurekaClient
public class BaiduAiApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaiduAiApiApplication.class, args);
    }

}
