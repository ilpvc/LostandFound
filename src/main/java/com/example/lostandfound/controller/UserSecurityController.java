package com.example.lostandfound.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lostandfound.entity.User;
import com.example.lostandfound.entity.UserSecurity;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.entity.VO.UserSecurityQuery;
import com.example.lostandfound.service.UserSecurityService;
import com.example.lostandfound.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * Description:
 *
 * @date:2023/4/13 19:30
 * @author: ilpvc
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/lostandfound/user")
public class UserSecurityController {

    UserSecurityService userSecurityService;
    PasswordEncoder passwordEncoder;

    UserService userService;
    public UserSecurityController(UserSecurityService userSecurityService,
                                  PasswordEncoder passwordEncoder,
                                  UserService userService) {
        this.userSecurityService = userSecurityService;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PutMapping("/updateAccount")
    public R updateUserSecurity(@RequestBody UserSecurityQuery query) {

        if (!"".equals(query.getOriginPassword())) {
            UserSecurity userSecurity = userSecurityService.getOne(new QueryWrapper<UserSecurity>().eq("user_id", query.getUserId()));
            String oldEncodePassword = userSecurity.getPassword().split("}")[1];
            log.info(oldEncodePassword);
            if (!passwordEncoder.matches(query.getOriginPassword(),oldEncodePassword)){
                return R.error().message("原密码错误，修改失败");
            }else {
                UserSecurity userSecurity1 = new UserSecurity();
                userSecurity1.setPassword("{bcrypt}"+passwordEncoder.encode(query.getPassword()));
                boolean userId = userSecurityService.saveOrUpdate(
                        userSecurity1,
                        new QueryWrapper<UserSecurity>()
                                .eq("user_id", query.getUserId()));
                if (userId){
                    return R.ok().message("密码修改成功");
                }else {
                    return R.error().message("密码修改失败");
                }
            }
        }else if (!"".equals(query.getNickname())){
            UserSecurity userSecurity1 = new UserSecurity();
            userSecurity1.setNickname(query.getNickname());

            User user = new User();
            user.setNickname(query.getNickname());
            boolean userId = userSecurityService.saveOrUpdate(
                    userSecurity1,
                    new QueryWrapper<UserSecurity>()
                            .eq("user_id", query.getUserId()));
            userService.saveOrUpdate(
                    user,
                    new QueryWrapper<User>()
                            .eq("id", query.getUserId()));
            return R.ok().message("修改成功");
        }else if (!"".equals(query.getEmail())){
            UserSecurity userSecurity1 = new UserSecurity();
            userSecurity1.setEmail(query.getEmail());

            User user = new User();
            user.setEmail(query.getEmail());
            boolean userId = userSecurityService.saveOrUpdate(
                    userSecurity1,
                    new QueryWrapper<UserSecurity>()
                            .eq("user_id", query.getUserId()));
            userService.saveOrUpdate(
                    user,
                    new QueryWrapper<User>()
                            .eq("id", query.getUserId()));
            return R.ok().message("修改成功");
        }else {
            return R.error();
        }
    }


    @PostMapping("/reset")
    public R resetPassword(@RequestBody UserSecurity userSecurity){
        UserSecurity security = userSecurityService.getOne(new QueryWrapper<UserSecurity>()
                .eq("email", userSecurity.getEmail()));
        if (Objects.isNull(security)){
            return R.error();
        }
        security.setPassword("{bcrypt}"+passwordEncoder.encode(userSecurity.getPassword()));
        userSecurityService.updateById(security);
        return R.ok();
    }
}
