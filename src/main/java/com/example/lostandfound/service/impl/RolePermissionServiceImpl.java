package com.example.lostandfound.service.impl;

import com.example.lostandfound.entity.RolePermission;
import com.example.lostandfound.mapper.RolePermissionMapper;
import com.example.lostandfound.service.RolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 角色权限关联表 服务实现类
 * </p>
 *
 * @author ilpvc
 * @since 2023-02-24 10:20:13
 */
@Service
@Transactional
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

}
