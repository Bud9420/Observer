package com.future.observermonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.future.observermonitor")
public class ObserverMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObserverMonitorApplication.class, args);
    }

}
