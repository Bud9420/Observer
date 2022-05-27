package com.future.observeruser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.future.observercommon.dto.CompanyDTO;
import com.future.observercommon.dto.ImgBasePath;
import com.future.observercommon.util.BeanUtil;
import com.future.observercommon.util.FileUtil;
import com.future.observeruser.dto.ModifyPasswordDTO;
import com.future.observercommon.dto.UserDTO;
import com.future.observeruser.mapper.UserMapper;
import com.future.observeruser.mapper.UserRoleMapper;
import com.future.observeruser.po.Company;
import com.future.observeruser.po.User;
import com.future.observeruser.po.UserRole;
import com.future.observeruser.service.CompanyService;
import com.future.observeruser.service.UserService;
import com.future.observeruser.dto.PasswordDTO;
import com.future.observeruser.vo.CompanyVO;
import com.future.observeruser.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.IOException;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    @SuppressWarnings("all")
    private UserRoleMapper userRoleMapper;

    @Autowired
    private ImgBasePath imgBasePath;

    @Override
    public void regist(UserDTO userDTO) throws IOException {
        /*
         * 加密
         */
        PasswordDTO passwordDTO = new PasswordDTO();
        passwordDTO.setPassword(userDTO.getPassword());
        passwordDTO.setSalt(userDTO.getUsername());
        String encryptPassword = passwordService.encryptPassword(passwordDTO);

        /*
         * 插入用户
         */
        userDTO.setPassword(encryptPassword);
        User user = new User();
        BeanUtil.copyBeanProp(user, userDTO);
        // 创建默认头像
        String path = imgBasePath.getUserHeadImgPath() + user.getUsername() + "/" + user.getUsername() + ".jpg";
        File headImg = FileUtil.createFile(path);
        FileUtil.copy(new File(imgBasePath.getUserHeadImgPath() + "default.jpg"), headImg);
        user.setHeadPath(path);
        // 指定企业
        Company company = companyService.getOne(new QueryWrapper<Company>().eq("name", userDTO.getCompanyName()));
        if (company != null) {
            user.setCompanyId(company.getId());
        }
        save(user);

        /*
         * 初始化用户权限
         */
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(1);
        userRoleMapper.insert(userRole);
    }

    @Override
    public UserVO login(UserDTO userDTO) throws UnknownAccountException, IncorrectCredentialsException, AuthenticationException, IOException {
        /*
         * shiro登录
         */
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userDTO.getUsername(), userDTO.getPassword());
        token.setRememberMe(true);
        subject.login(token);

        /*
         * 将user的id保存到session
         * 将userVO保存到session，并返回
         */
        Session shiroSession = subject.getSession();
        User user = (User) subject.getPrincipal();

        // 将user的id保存到session
        shiroSession.setAttribute("user_id", user.getId());

        // userVO保存到session，并返回
        UserVO userVO = new UserVO();
        BeanUtil.copyBeanProp(userVO, user);
        // 获取头像
        userVO.setHeadImg(Base64Utils.encodeToString(FileUtil.readFileAsBytes(user.getHeadPath())));
        // 获取所在企业
        Company company = companyService.getOne(new QueryWrapper<Company>().eq("id", user.getCompanyId()));
        CompanyVO companyVO = new CompanyVO();
        BeanUtil.copyBeanProp(companyVO, company);
        companyVO.setLicense(Base64Utils.encodeToString(FileUtil.readFileAsBytes(company.getLicensePath())));
        userVO.setCompanyVO(companyVO);
        // 保存到session，并返回
        shiroSession.setAttribute("user", userVO);
        return userVO;
    }

    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

    @Override
    public void modifyPassword(ModifyPasswordDTO modifyPasswordDTO) throws IncorrectCredentialsException {
        /*
         * 获取数据源的原密码
         */
        User user = getOne(new QueryWrapper<User>().eq("username", modifyPasswordDTO.getUsername()));
        PasswordDTO passwordDTO = new PasswordDTO();
        passwordDTO.setPassword(modifyPasswordDTO.getPassword());
        passwordDTO.setSalt(modifyPasswordDTO.getUsername());
        String encryptPassword = passwordService.encryptPassword(passwordDTO);

        /*
         * 修改密码
         */
        if (encryptPassword.equals(user.getPassword())) {
            passwordDTO.setPassword(modifyPasswordDTO.getNewPassword());
            user.setPassword(passwordService.encryptPassword(passwordDTO));
            update(user, new UpdateWrapper<User>().eq("id", user.getId()));
        } else {
            throw new IncorrectCredentialsException();
        }
    }

    @Override
    public void modifyUser(UserDTO userDTO) throws IOException {
        User user = new User();
        BeanUtil.copyBeanProp(user, userDTO);

        /*
         * 设置用户所在的企业
         */
        Company company = companyService.getOne(new QueryWrapper<Company>().eq("name", userDTO.getCompanyName()));
        if (company != null) {
            user.setCompanyId(company.getId());
        } else {
            user.setCompanyId(null);
        }

        /*
         * 更新用户信息
         */
        update(null, new UpdateWrapper<User>()
                .set("phone", user.getPhone())
                .set("company_id", user.getCompanyId())
                .eq("username", user.getUsername())
        );

        /*
         * 更新头像
         */
        user = getOne(new QueryWrapper<User>().eq("username", user.getUsername()));
        FileUtil.copy(Base64Utils.decodeFromString(userDTO.getHeadImg()), new File(user.getHeadPath()));
    }
}
