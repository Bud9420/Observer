package com.future.observermonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.future.observermonitor", "com.future.observercommon"})
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.future.observermonitor")
public class ObserverMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObserverMonitorApplication.class, args);
    }

}
