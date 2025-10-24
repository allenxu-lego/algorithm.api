package com.playground.algorithmapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.playground.algorithmapi.entity.User;

/**
 * 用户服务接口
 */
public interface IUserService extends IService<User> {
    // 继承IService后，自动拥有基本的CRUD操作
}
