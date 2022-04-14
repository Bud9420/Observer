package com.future.observeruser.controller;

import com.future.observercommon.vo.ResponseResult;
import com.future.observercommon.vo.UserVO;
import com.future.observeruser.dto.ModifyPasswordDTO;
import com.future.observercommon.dto.UserDTO;
import com.future.observeruser.service.UserService;
import io.swagger.annotations.*;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Api("用户的API")
@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("注册")
    @ApiImplicitParam(name = "userDTO", value = "注册的用户信息", required = true)
    @PostMapping("/regist")
    public ResponseResult regist(@RequestBody UserDTO userDTO) throws IOException {
        userService.regist(userDTO);
        return ResponseResult.success();
    }

    @ApiOperation("登录")
    @ApiImplicitParam(name = "userDTO", value = "登录的用户信息", required = true)
    @PostMapping("/login")
    public ResponseResult login(@RequestBody UserDTO userDTO) throws UnknownAccountException, IncorrectCredentialsException, AuthenticationException, IOException {
        UserVO userVo = userService.login(userDTO);
        return ResponseResult.success(userVo);
    }

    @ApiOperation("注销")
    @GetMapping("/logout")
    public void logout() {
        userService.logout();
    }

    @ApiOperation("修改密码")
    @ApiImplicitParam(name = "passwordDTO", value = "用户名、原密码、新密码", required = true)
    @PutMapping("/password")
    public ResponseResult putPassword(@RequestBody ModifyPasswordDTO modifyPasswordDTO) {
        userService.modifyPassword(modifyPasswordDTO);
        return ResponseResult.success();
    }

    @ApiOperation("修改用户")
    @ApiImplicitParam(name = "user", value = "用户信息", required = true)
    @PutMapping
    public ResponseResult putUser(@RequestBody UserDTO userDTO) throws IOException {
        userService.modifyUser(userDTO);
        return ResponseResult.success();
    }

    // @ApiOperation("根据用户id删除用户")
    // @ApiImplicitParam(name = "id", value = "用户id", required = true)
    // @DeleteMapping("/{id}")
    // public ResponseResult deleteUserById(@PathVariable("id") Integer id) {
    //     if (userService.removeById(id)) {
    //         return ResponseResult.success();
    //     }
    //     return ResponseResult.success("用户不存在");
    // }

    // @ApiModelProperty("获取用户列表")
    // @GetMapping
    // public ResponseResult getUserList() {
    //     List<User> users = userService.list();
    //     return ResponseResult.success(users);
    // }
}
