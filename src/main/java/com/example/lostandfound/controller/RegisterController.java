package com.example.lostandfound.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lostandfound.entity.User;
import com.example.lostandfound.entity.UserSecurity;
import com.example.lostandfound.entity.UserSettings;
import com.example.lostandfound.entity.VO.LoginParams;
import com.example.lostandfound.entity.VO.Mail;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.entity.VO.UserQuery;
import com.example.lostandfound.service.LikesService;
import com.example.lostandfound.service.UserSecurityService;
import com.example.lostandfound.service.UserService;
import com.example.lostandfound.service.UserSettingsService;
import com.example.lostandfound.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserSecurityService userSecurityService;

    @Autowired
    UserSettingsService userSettingsService;
    @Autowired
    RedisCache redisCache;

    @PostMapping("/")
    public R register(@RequestBody LoginParams loginParams) {
        User user = new User();
        UserSecurity userSecurity = new UserSecurity();
        QueryWrapper<UserSecurity> userSecurityQueryWrapper = new QueryWrapper<>();
        userSecurityQueryWrapper.eq("nickname",loginParams.getNickName())
                .or().eq("email",loginParams.getEmail());

        List<UserSecurity> userSecurities = userSecurityService.list(userSecurityQueryWrapper);
        if (userSecurities.size()==0){
            user.setNickname(loginParams.getNickName());
            userSecurity.setNickname(loginParams.getNickName());

            String code = redisCache.getCacheObject("email-code-" + loginParams.getEmail());
            if (!Objects.equals(code, loginParams.getCode())){
                return R.error().message("验证码错误或邮箱不存在");
            }
            user.setEmail(loginParams.getEmail());
            userSecurity.setEmail(loginParams.getEmail());
            userSecurity.setPassword("{bcrypt}"+passwordEncoder.encode(loginParams.getPassword()));
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("nickname",user.getNickname());
            userService.save(user);
            User one = userService.getOne(queryWrapper);
            userSecurity.setUserId(one.getId());
            userSecurityService.save(userSecurity);

            //为用户新建设置表
            UserSettings userSettings = new UserSettings();
            userSettings.setUserId(user.getId());
            userSettingsService.save(userSettings);

            return R.ok().message("注册成功");
        }else if (userSecurities.size()!=0){
            return R.error().message("此用户名或邮箱已经被注册");
        }
        return R.error().message("注册失败");

    }

    /**
     * 邮箱验证
     */
    @PostMapping("/email")
    public R verificationMailbox(@RequestBody Mail mail){
        String code = redisCache.getCacheObject("email-code-" + mail.getUserEmail());
        log.info(mail.toString());
        if (!Objects.equals(code, mail.getCode())){
            return R.error().message("验证码错误或邮箱不存在");
        }else {
            return R.ok();
        }
    }
}
