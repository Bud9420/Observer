package com.future.observermonitordriving;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.future.observermonitordriving", "com.future.observercommon"})
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.future.observermonitordriving")
public class ObserverMonitorDrivingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObserverMonitorDrivingApplication.class, args);
    }

}
