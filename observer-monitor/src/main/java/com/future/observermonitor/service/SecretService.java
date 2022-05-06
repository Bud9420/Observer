package com.future.observermonitor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.future.observercommon.dto.UserDTO;
import com.future.observermonitor.po.Secret;
import com.future.observermonitor.vo.SecretVO;

public interface SecretService extends IService<Secret> {

    /**
     * <根据用户名获取Secret>
     *
     * @param userDTO userDTO
     * @return SecretVO
     */
    SecretVO getOne(UserDTO userDTO);

    /**
     * <重新获取萤石开放平台的AccessToken>
     * <在数据库中更新AccessToken>
     * <返回更新后的AccessToken>
     *
     * @param userDTO userDTO
     * @return 更新后的AccessToken
     * @throws Exception ysopen-api服务异常
     */
    String getAccessTokenAgain(UserDTO userDTO) throws Exception;
}
