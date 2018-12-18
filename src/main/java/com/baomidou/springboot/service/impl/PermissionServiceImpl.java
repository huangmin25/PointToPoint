package com.baomidou.springboot.service.impl;

import com.baomidou.springboot.entity.Permission;
import com.baomidou.springboot.mapper.PermissionMapper;
import com.baomidou.springboot.service.IPermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
