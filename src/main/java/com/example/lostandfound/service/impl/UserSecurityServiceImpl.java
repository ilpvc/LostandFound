package com.example.lostandfound.service.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lostandfound.entity.UserSecurity;
import com.example.lostandfound.mapper.UserSecurityMapper;
import com.example.lostandfound.service.UserSecurityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description:
 *
 * @date:2023/3/29 10:33
 * @author: ilpvc
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserSecurityServiceImpl extends ServiceImpl<UserSecurityMapper, UserSecurity> implements UserSecurityService {
}
