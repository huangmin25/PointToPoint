package com.baomidou.springboot.service.impl;

import com.baomidou.springboot.entity.UserRole;
import com.baomidou.springboot.mapper.UserRoleMapper;
import com.baomidou.springboot.service.IUserRoleService;
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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
