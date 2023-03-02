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

    /**
     * 按照id用户查询
     * @param id
     * @return R
     */
    @GetMapping("/{id}")
    public R getUserById(@PathVariable int id){
        return R.ok().data("item",userService.getById(id));
    }

    /**
     * 查询所有用户
     * @return R
     */
    @GetMapping("/allUser")
    public R getUsers(){
        return R.ok().data("items",userService.list());
    }

    /**
     * 分页查询用户
     * @param pageNo
     * @param pageCount
     * @return
     */
    @GetMapping("pageUser/{pageNo}/{pageCount}")
    public R pageUser(@PathVariable int pageNo, @PathVariable int pageCount) {
        Page<User> page = new Page<>(pageNo, pageCount);
        userService.page(page, null);
        return R.ok().data("items", page);
    }

    /**
     * 根据id更新用户信息
     * @param user
     * @return
     */
    @PutMapping("/updateUser")
    public R updateUser(@RequestBody User user){
        boolean b = userService.updateById(user);
        if (b){
            return R.ok().message("更新成功");
        }else {
            return R.error().message("更新失败");
        }
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    public R addUser(@RequestBody User user) {
        boolean b = userService.save(user);
        if (b){
            return R.ok().message("添加成功");
        }else {
            return R.error().message("添加失败");
        }
    }

    @DeleteMapping("/delUser/{id}")
    public R deleteById(@PathVariable("id") Long id) {
        boolean b = userService.removeById(id);
        if (b) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }


}
