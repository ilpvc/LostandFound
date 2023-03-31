package com.example.lostandfound.controller;

import com.example.lostandfound.entity.User;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.entity.VO.UserQuery;
import com.example.lostandfound.service.UserSecurityService;
import com.example.lostandfound.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    UserSecurityService userSecurityService;

    @PostMapping("/")
    public R register(@RequestBody UserQuery userQuery) {
        User user = new User();
        user.setEmail(userQuery.getEmail());
        user.setNickname(userQuery.getNickname());
        log.info(user.getEmail());
        return R.ok();
    }
}
