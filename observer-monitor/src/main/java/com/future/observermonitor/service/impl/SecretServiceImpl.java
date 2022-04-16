package com.future.observermonitor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.observermonitor.mapper.SecretMapper;
import com.future.observermonitor.po.Secret;
import com.future.observermonitor.service.SecretService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SecretServiceImpl extends ServiceImpl<SecretMapper, Secret> implements SecretService {
}
