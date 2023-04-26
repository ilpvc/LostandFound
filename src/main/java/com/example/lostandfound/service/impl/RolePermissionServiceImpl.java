package com.example.lostandfound.service.impl;

import com.example.lostandfound.entity.RolePermission;
import com.example.lostandfound.mapper.RolePermissionMapper;
import com.example.lostandfound.service.RolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 角色权限表 服务实现类
 * </p>
 *
 * @author ilpvc
 * @since 2023-03-22 09:28:33
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

}
