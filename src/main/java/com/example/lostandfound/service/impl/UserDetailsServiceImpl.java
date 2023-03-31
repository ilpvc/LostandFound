package com.example.lostandfound.service.impl;

import com.example.lostandfound.entity.MyUserDetails;
import com.example.lostandfound.entity.User;
import com.example.lostandfound.repository.UserRepository;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @date:2023/3/30 9:59
 * @author: ilpvc
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("username " + username + " is not found");
        }
        return new MyUserDetails(user.getNickname(),user.getPassword(),user.getEmail());
    }


}

