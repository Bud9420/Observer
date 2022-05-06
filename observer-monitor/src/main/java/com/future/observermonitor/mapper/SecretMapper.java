package com.future.observermonitor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.future.observermonitor.po.Secret;

public interface SecretMapper extends BaseMapper<Secret> {

    Secret selectOneByUsername(String username);

    void update(Secret secret);
}
