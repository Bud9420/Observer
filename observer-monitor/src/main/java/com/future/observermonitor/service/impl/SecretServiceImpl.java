package com.future.observermonitor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.observercommon.dto.UserDTO;
import com.future.observercommon.util.BeanUtil;
import com.future.observermonitor.mapper.SecretMapper;
import com.future.observermonitor.po.Secret;
import com.future.observermonitor.service.SecretService;
import com.future.observermonitor.vo.SecretVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SecretServiceImpl extends ServiceImpl<SecretMapper, Secret> implements SecretService {

    @Override
    public SecretVO getOneByUserDTO(UserDTO userDTO) {
        Secret secret = getOne(new QueryWrapper<Secret>().eq("user_id", userDTO.getUserId()));
        SecretVO secretVO = new SecretVO();
        BeanUtil.copyBeanProp(secretVO, secret);
        return secretVO;
    }
}
