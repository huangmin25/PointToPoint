package com.baomidou.springboot.service.impl;

import com.baomidou.springboot.entity.Role;
import com.baomidou.springboot.mapper.RoleMapper;
import com.baomidou.springboot.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
