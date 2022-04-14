package com.future.observeruser;

import org.apache.shiro.authc.credential.PasswordService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ObserverUserApplicationTests {

    @Resource
    PasswordService passwordService;

    @Test
    void contextLoads() {
    }

}
