package com.future.observermonitorpublic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.future.observermonitorpublic", "com.future.observercommon"})
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.future.observermonitorpublic")
public class ObserverMonitorPublicApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObserverMonitorPublicApplication.class, args);
    }

}
