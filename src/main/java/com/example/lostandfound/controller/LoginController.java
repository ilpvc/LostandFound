package com.example.lostandfound.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lostandfound.entity.MyUserDetails;
import com.example.lostandfound.entity.User;
import com.example.lostandfound.entity.VO.LoginParams;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.service.UserService;
import com.example.lostandfound.service.impl.UserDetailsServiceImpl;
import com.example.lostandfound.utils.JwtUtil;
import com.example.lostandfound.utils.RedisCache;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @date:2023/3/29 9:11
 * @author: ilpvc
 */

@RestController
@RequestMapping("/lostandfound/login")
@CrossOrigin
@Slf4j
public class LoginController {


    @Autowired
    UserController userController;

    @Autowired
    RedisCache redisCache;
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/")
    public R login(@RequestBody LoginParams loginParams){
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginParams.getEmail(), loginParams.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);

        MyUserDetails myUserDetails = (MyUserDetails) userDetailsService.loadUserByUsername(loginParams.getEmail());
        if (myUserDetails == null){
            return R.error().message("账号不存在，登录失败");
        }
        String jwt = JwtUtil.createJWT(myUserDetails.toString());
        //更新缓存
        userController.getAllUserAndCache();
        setCacheLoginUser(myUserDetails.getEmail(),jwt,2);

        User email = userService.getOne(new QueryWrapper<User>().eq("email", loginParams.getEmail()));

        return R.ok().data("token",jwt).data("user",email);
    }


    private void setCacheLoginUser(String s,String token,int time){
        redisCache.setCacheObject("token-user-"+s,token,time, TimeUnit.DAYS);
    }
}
