package com.future.observeruser.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.future.observeruser.po.Role;
import com.future.observeruser.po.User;
import com.future.observeruser.service.RoleService;
import com.future.observeruser.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 在shiro中的自定义Realm
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();

        List<Role> roles = roleService.list(new QueryWrapper<Role>().eq("user_id", user.getId()));

        Set<String> set = new HashSet<>();
        for (Role role : roles) {
            set.add(role.getName());
        }

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(set);

        return authorizationInfo;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();

        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));

        if (user == null) {
            throw new UnknownAccountException();
        }

        return new SimpleAuthenticationInfo(
                user, // 用户信息
                user.getPassword(), // 数据源中的密码
                ByteSource.Util.bytes(user.getUsername()), // 密码匹配时的salt
                getName() // 当前Realm的名称
        );
    }
}
