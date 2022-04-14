package com.future.observeruser.service.impl;

import com.future.observeruser.dto.PasswordDTO;
import lombok.Data;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * 在shiro中的密码服务
 */
@Service
@ConfigurationProperties(prefix = "shiro")
@Data
public class PasswordServiceImpl implements PasswordService {

    private String hashAlgorithmName;

    private int hashIterations;

    @Override
    public String encryptPassword(Object passwordDTO) throws IllegalArgumentException {
        PasswordDTO dto = (PasswordDTO) passwordDTO;
        return new SimpleHash(hashAlgorithmName, dto.getPassword(), dto.getSalt(), hashIterations).toBase64();
    }

    @Override
    public boolean passwordsMatch(Object submittedPlaintext, String encrypted) {
        return false;
    }
}
