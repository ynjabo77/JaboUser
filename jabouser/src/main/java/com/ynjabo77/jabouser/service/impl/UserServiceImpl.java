package com.ynjabo77.jabouser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ynjabo77.jabouser.model.domain.User;
import com.ynjabo77.jabouser.service.UserService;
import com.ynjabo77.jabouser.mapper.UserMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ynjabo77.jabouser.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 用户服务实现类
 *
 * @author 恩嗯
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 盐值，混淆密码
     */
    public static final String SALT = "enen";

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        // 1. 校验字段非空
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return -1;
        }
        // 账号不能少于 4 位
        if (userAccount.length() < 4) {
            return -1;
        }
        // 密码不能少于 8 位
        if (userPassword.length() < 8) {
            return -1;
        }
        // 账户只能有大小写字母和数字
        String validPattern = "^[A-Za-z0-9]+$";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (!matcher.find()) {
            return -1;
        }
        // 密码和校验密码相同
        if (!userPassword.equals(checkPassword)) {
            return -1;
        }
        // 账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            return -1;
        }
        // 2. 密码加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 3. 插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        boolean saveResult = this.save(user);
        if (!saveResult) {
            return -1;
        }
        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        // 1. 校验字段非空
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        // 账号不能少于 4 位
        if (userAccount.length() < 4) {
            return null;
        }
        // 密码不能少于 8 位
        if (userPassword.length() < 8) {
            return null;
        }
        // 账户只能有大小写字母和数字
        String validPattern = "^[A-Za-z0-9]+$";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (!matcher.find()) {
            return null;
        }
        // 2. 密码加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 查询账户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            log.info("user login failed,userAccount cannot match userPassword");
            return null;
        }
        // 3. 用户脱敏
        User safetyUser = getSafetyUser(user);
        // 4. 记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, safetyUser);
        return safetyUser;
    }

    /**
     * 用户脱敏
     *
     * @param originUser
     * @return
     */
    @Override
    public User getSafetyUser(User originUser) {
        User safetyUser = new User();
        safetyUser.setId(originUser.getId());
        safetyUser.setUsername(originUser.getUsername());
        safetyUser.setUserAccount(originUser.getUserAccount());
        safetyUser.setUserAvatar(originUser.getUserAvatar());
        safetyUser.setUserRole(originUser.getUserRole());
        safetyUser.setSex(originUser.getSex());
        safetyUser.setEmail(originUser.getEmail());
        safetyUser.setUserStatus(originUser.getUserStatus());
        safetyUser.setCreateTime(originUser.getCreateTime());
        return safetyUser;
    }
}




