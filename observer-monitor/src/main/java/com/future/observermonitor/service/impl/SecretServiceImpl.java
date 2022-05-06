package com.future.observermonitor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.observercommon.dto.SecretDTO;
import com.future.observercommon.dto.UserDTO;
import com.future.observercommon.util.BeanUtil;
import com.future.observermonitor.mapper.SecretMapper;
import com.future.observermonitor.po.Secret;
import com.future.observermonitor.service.SecretService;
import com.future.observermonitor.service.YSOpenService;
import com.future.observermonitor.vo.SecretVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SecretServiceImpl extends ServiceImpl<SecretMapper, Secret> implements SecretService {

    @Autowired
    @SuppressWarnings("all")
    private YSOpenService ysOpenService;

    @Autowired
    @SuppressWarnings("all")
    private SecretMapper secretMapper;

    public SecretVO getOne(UserDTO userDTO) {
        Secret secret = secretMapper.selectOneByUsername(userDTO.getUsername());

        SecretVO secretVO = new SecretVO();
        BeanUtil.copyBeanProp(secretVO, secret);

        return secretVO;
    }

    @Override
    public String getAccessTokenAgain(UserDTO userDTO) throws Exception {
        Secret secret = secretMapper.selectOneByUsername(userDTO.getUsername());

        SecretDTO secretDTO = new SecretDTO();
        BeanUtil.copyBeanProp(secretDTO, secret);

        // 获取监控AccessToken
        String accessToken = (String) ysOpenService.getAccessToken(secretDTO).getResult();

        secret.setAccessToken(accessToken);

        // 更新用户的AccessToken
        secretMapper.update(secret);

        // 返回AccessToken
        return accessToken;
    }
}
