package com.playground.algorithmapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.playground.algorithmapi.entity.User;
import com.playground.algorithmapi.mapper.UserMapper;
import com.playground.algorithmapi.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    // 继承ServiceImpl后，自动拥有基本的CRUD操作
}
