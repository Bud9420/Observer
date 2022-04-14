package com.future.ysopenapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class YsopenApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(YsopenApiApplication.class, args);
    }
}
