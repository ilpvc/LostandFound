package com.example.lostandfound.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lostandfound.entity.User;
import com.example.lostandfound.entity.UserSecurity;
import com.example.lostandfound.entity.VO.LoginParams;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.entity.VO.UserQuery;
import com.example.lostandfound.service.LikesService;
import com.example.lostandfound.service.UserSecurityService;
import com.example.lostandfound.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 *
 * @date:2023/3/29 9:11
 * @author: ilpvc
 */

@RestController
@RequestMapping("/lostandfound/register")
@CrossOrigin
@Slf4j
public class RegisterController {

    @Autowired
    UserService userService;

    QueryWrapper<UserSecurity> userSecurityQueryWrapper;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserSecurityService userSecurityService;

    @PostMapping("/")
    public R register(@RequestBody LoginParams loginParams) {
        User user = new User();
        UserSecurity userSecurity = new UserSecurity();
        userSecurityQueryWrapper = new QueryWrapper<>();
        userSecurityQueryWrapper.eq("nickname",loginParams.getNickName())
                .or().eq("email",loginParams.getEmail());

        List<UserSecurity> userSecurities = userSecurityService.list(userSecurityQueryWrapper);
        if (userSecurities.size()==0){
            user.setNickname(loginParams.getNickName());
            userSecurity.setNickname(loginParams.getNickName());

            user.setEmail(loginParams.getEmail());
            userSecurity.setEmail(loginParams.getEmail());
            userSecurity.setPassword(passwordEncoder.encode(loginParams.getPassword()));

            userService.save(user);
            userSecurityService.save(userSecurity);
            return R.ok().message("注册成功");
        }else if (userSecurities.size()!=0){
            return R.error().message("此用户名或邮箱已经被注册");
        }
        return R.error().message("注册失败");

    }
}
