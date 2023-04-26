package com.example.lostandfound.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lostandfound.entity.MyUserDetails;
import com.example.lostandfound.entity.UserSecurity;
import com.example.lostandfound.mapper.UserSecurityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description:
 *
 * @date:2023/3/30 9:59
 * @author: ilpvc
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserDetailsServiceImpl implements UserDetailsService {

//    @Autowired
    private UserSecurityMapper userSecurityMapper;
    @Autowired
    UserDetailsServiceImpl(UserSecurityMapper userSecurityMapper){
        this.userSecurityMapper = userSecurityMapper;
    }

    private QueryWrapper<UserSecurity> queryWrapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",username);
        UserSecurity userSecurity = userSecurityMapper.selectOne(queryWrapper);
        if (userSecurity == null) {
            throw new UsernameNotFoundException("username " + username + " is not found");
        }
        return new MyUserDetails(userSecurity.getNickname(),userSecurity.getPassword(),userSecurity.getEmail());
    }


}

