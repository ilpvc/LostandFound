package com.example.lostandfound.service.impl;

import com.example.lostandfound.entity.User;
import com.example.lostandfound.mapper.UserMapper;
import com.example.lostandfound.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ilpvc
 * @since 2023-03-22 09:28:33
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
