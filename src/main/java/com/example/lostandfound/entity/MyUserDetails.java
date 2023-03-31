package com.example.lostandfound.entity;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

/**
 * Description:
 *
 * @date:2023/3/30 9:57
 * @author: ilpvc
 */
@Data
public class MyUserDetails extends User implements UserDetails {
    private String password;
    private final String username;
    private final String email; // 扩展字段，手机号放入用户信息中


    public MyUserDetails(String username, String password, String email) {
        this.password = password;
        this.email = email;
        this.username = username;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
