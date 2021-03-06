package com.baomidou.springboot.mapper;

import com.baomidou.springboot.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peter min带你飞
 * @since 2018-11-21
 */
public interface UserMapper extends BaseMapper<User> {
    public User findUserByName(String name);
}
