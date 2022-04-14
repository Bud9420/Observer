package com.future.observeruser.controller;

import com.future.observercommon.constant.StatusCode;
import com.future.observercommon.vo.ResponseResult;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class UserExceptionAdvice {

    @ExceptionHandler(UnknownAccountException.class)
    public ResponseResult unknownAccount() {
        return ResponseResult.fail(StatusCode.FORBIDDEN, "用户名不存在");
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    public ResponseResult incorrectCredentials() {
        return ResponseResult.fail(StatusCode.FORBIDDEN, "密码不正确");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseResult authentication() {
        return ResponseResult.fail(StatusCode.INTERNAL_SERVER_ERROR, "登录失败");
    }

    @ExceptionHandler(IOException.class)
    public ResponseResult io() {
        return ResponseResult.fail(StatusCode.INTERNAL_SERVER_ERROR, "头像获取或更新失败");
    }
}
