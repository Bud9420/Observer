package com.future.observeruser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.future.observeruser.dto.ModifyPasswordDTO;
import com.future.observercommon.dto.UserDTO;
import com.future.observeruser.po.User;
import com.future.observeruser.vo.UserVO;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;

import java.io.IOException;

public interface UserService extends IService<User> {

    /**
     * <注册>
     *
     * @param userDTO 注册的用户信息
     * @throws IOException 头像创建失败
     */
    void regist(UserDTO userDTO) throws IOException;

    /**
     * <登录>
     *
     * @param userDTO 登录的用户信息
     * @return userVO
     * @throws UnknownAccountException       用户名不存在
     * @throws IncorrectCredentialsException 密码不正确
     * @throws AuthenticationException       登录失败
     * @throws IOException                   找不到用户头像
     */
    UserVO login(UserDTO userDTO) throws UnknownAccountException, IncorrectCredentialsException, AuthenticationException, IOException;

    /**
     * <注销>
     */
    void logout();

    /**
     * <修改密码>
     *
     * @param modifyPasswordDTO 原密码和新密码
     */
    void modifyPassword(ModifyPasswordDTO modifyPasswordDTO) throws IncorrectCredentialsException;

    /**
     * <更新用户信息>
     *
     * @param userDTO 更新的用户信息
     * @throws IOException 头像修改失败
     */
    void modifyUser(UserDTO userDTO) throws IOException;
}
