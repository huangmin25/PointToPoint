package com.baomidou.springboot.service.impl;

import com.baomidou.springboot.entity.User;
import com.baomidou.springboot.mapper.UserMapper;
import com.baomidou.springboot.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author peter min带你飞
 * @since 2018-11-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User findUserByName(String name) {
        return baseMapper.findUserByName(name);
    }
}
