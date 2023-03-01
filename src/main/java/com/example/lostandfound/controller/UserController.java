package com.example.lostandfound.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lostandfound.entity.User;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/laf/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public R getUserById(@PathVariable int id){
        return R.ok().data("item",userService.getById(id));
    }

    @GetMapping("/allUser")
    public R getUsers(){
        return R.ok().data("items",userService.list());
    }

    @GetMapping("pageUser/{pageNo}/{pageCount}")
    public R pageUser(@PathVariable int pageNo, @PathVariable int pageCount) {
        Page<User> page = new Page<>(pageNo, pageCount);
        userService.page(page, null);
        return R.ok().data("items", page);
    }


}
