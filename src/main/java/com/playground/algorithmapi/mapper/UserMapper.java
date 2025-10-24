package com.playground.algorithmapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.playground.algorithmapi.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Mapper接口
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    // BaseMapper已提供基本的CRUD操作，如需自定义SQL可在此添加
}
