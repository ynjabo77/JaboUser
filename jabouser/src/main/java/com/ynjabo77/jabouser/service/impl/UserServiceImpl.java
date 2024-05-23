package com.ynjabo77.jabouser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ynjabo77.jabouser.model.domain.User;
import com.ynjabo77.jabouser.service.UserService;
import com.ynjabo77.jabouser.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author ynjabo77
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2024-05-23 15:43:25
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




