package com.example.lostandfound.controller;

import com.example.lostandfound.entity.VO.LoginParams;
import com.example.lostandfound.entity.VO.R;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    private AuthenticationManager authenticationManager;
    @PostMapping("/")
    public R login(@RequestBody LoginParams loginParams){
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginParams.getEmail(), loginParams.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);
        userController.getAllUserAndCache();
        return R.ok().data("token",authenticate);
    }
}
