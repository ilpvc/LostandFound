package com.example.lostandfound.service.impl;

import com.example.lostandfound.entity.User;
import com.example.lostandfound.mapper.UserMapper;
import com.example.lostandfound.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ilpvc
 * @since 2023-02-24 10:20:13
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
