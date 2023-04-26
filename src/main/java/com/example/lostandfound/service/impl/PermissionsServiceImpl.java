package com.example.lostandfound.service.impl;

import com.example.lostandfound.entity.Permissions;
import com.example.lostandfound.mapper.PermissionsMapper;
import com.example.lostandfound.service.PermissionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ilpvc
 * @since 2023-03-22 09:28:33
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions> implements PermissionsService {

}
