package com.future.observermonitor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.future.observercommon.dto.UserDTO;
import com.future.observermonitor.po.Secret;
import com.future.observermonitor.vo.SecretVO;

public interface SecretService extends IService<Secret> {

    /**
     * <获取用户的Secret>
     *
     * @param userDTO 用户DTO
     * @return 该用户的Secret
     */
    SecretVO getOneByUserDTO(UserDTO userDTO);
}
