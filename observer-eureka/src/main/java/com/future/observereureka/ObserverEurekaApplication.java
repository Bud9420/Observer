package com.future.observereureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ObserverEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObserverEurekaApplication.class, args);
    }

}
