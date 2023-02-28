package com.example.lostandfound.controller;


import com.example.lostandfound.entity.User;
import com.example.lostandfound.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author ilpvc
 * @since 2023-02-24 10:20:13
 */
@Slf4j
@RestController
@RequestMapping("/lostandfound/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id){
        return userService.getById(id);
    }

    @GetMapping("/")
    public List<User> getUsers(){
        log.info("测试成功");
        return userService.list();
    }
}
