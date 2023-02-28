package com.example.lostandfound.service.impl;

import com.example.lostandfound.entity.Permission;
import com.example.lostandfound.mapper.PermissionMapper;
import com.example.lostandfound.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author ilpvc
 * @since 2023-02-24 10:20:13
 */
@Service
@Transactional
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
