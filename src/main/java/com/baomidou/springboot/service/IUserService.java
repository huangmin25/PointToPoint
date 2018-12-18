package com.baomidou.springboot.service;

import com.baomidou.springboot.entity.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author peter min带你飞
 * @since 2018-11-21
 */
public interface IUserService extends IService<User> {
    public User findUserByName(String name);
}
