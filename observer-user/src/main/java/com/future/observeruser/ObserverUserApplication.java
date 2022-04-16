package com.future.observeruser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.future.observeruser", "com.future.observercommon"})
@EnableEurekaClient
public class ObserverUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObserverUserApplication.class, args);
    }

}
