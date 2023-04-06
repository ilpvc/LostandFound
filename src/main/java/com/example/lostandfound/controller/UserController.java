package com.example.lostandfound.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lostandfound.entity.User;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.entity.VO.UserQuery;
import com.example.lostandfound.service.UserService;
import com.example.lostandfound.utils.RedisCache;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ilpvc
 * @since 2023-03-22 09:28:33
 */
@RestController
@RequestMapping("/lostandfound/user")
@CrossOrigin
@Slf4j
public class UserController {

    private QueryWrapper<User> queryWrapper;
    @Autowired
    UserService userService;
    @Autowired
    RedisCache redisCache;
    Logger logger = LoggerFactory.getLogger("UserController");

    /**
     * 获取所有角色信息并且做缓存
     * @return
     */
    @GetMapping("/")
    public R getAllUserAndCache() {

        List<User> users = userService.list();
        for (int i =0 ;i<users.size();i++){
            redisCache.setCacheObject("user-"+users.get(i).getId().toString(),users.get(i));
        }
        return R.ok().data("list", users);

    }

    /**
     * 按照排名获取角色
     * @return
     */
    @GetMapping("/ranking")
    public R getRankingUser(){
        queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("find_num");
        queryWrapper.last("limit 10");
        List<User> users = userService.list(queryWrapper);
        return R.ok().data("list",users);
    }

    /**
     * 根据id获取角色
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R getUserById(@PathVariable int id) {
        return R.ok().data("item", userService.getById(id));
    }

    /**
     * 根据id从缓存中获取角色
     * @param id
     * @return
     */
    @GetMapping("/cache/{id}")
    public R getCacheUserById(@PathVariable int id) {
        String userId = "user-"+id;
        User user = redisCache.getObject(userId);
        return R.ok().data("item", user);
    }

    /**
     * 分页查询用户
     * @param pageNo 当前页码
     * @param pageCount 每页数量
     * @return
     */
    @GetMapping("pageUser/{pageNo}/{pageCount}")
    public R pageConfig(@PathVariable int pageNo, @PathVariable int pageCount) {
        Page<User> page = new Page<>(pageNo, pageCount);
        userService.page(page, null);
        return R.ok().data("list", page);
    }


    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    public R addUser(@RequestBody User user) {
        boolean save = userService.save(user);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @PutMapping("/updateUser")
    public R updateUser(@RequestBody User user) {
        boolean b = userService.updateById(user);
        if (b) {
            updateCacheUserById(user);
            return R.ok();
        } else {
            return R.error();
        }
    }

    public void updateCacheUserById(User user){
        User byId = userService.getById(user.getId());
        redisCache.setCacheObject("user-"+user.getId().toString(),byId);
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R deleteUser(@PathVariable int id) {

        boolean flag = userService.removeById(id);
        if (flag) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }


    /**
     * 分页条件查询
     * @param pageNo 当前页码
     * @param pageCount 每页数量
     * @param userQuery 查询条件
     * @return
     */
    @PostMapping("/pageUserCondition/{pageNo}/{pageCount}")
    public R pageUserCondition(@PathVariable int pageNo,
                               @PathVariable int pageCount,
                               @RequestBody UserQuery userQuery) {
        Page<User> page = new Page<>(pageNo, pageCount);
        queryWrapper = new QueryWrapper<>();

        setQueryWrapper(userQuery);

        userService.page(page, queryWrapper);
        return R.ok().data("items", page);
    }

    /**
     * 条件查询
     * @param userQuery 条件
     * @return
     */
    @PostMapping("/condition")
    public R UserCondition(@RequestBody UserQuery userQuery) {
        queryWrapper = new QueryWrapper<>();
        setQueryWrapper(userQuery);
        List<User> users = userService.list(queryWrapper);
        return R.ok().data("list", users).data("num", users.size());
    }



    private void setQueryWrapper(UserQuery userQuery){
        if (userQuery.getAge() != null) {
            queryWrapper.eq("age", userQuery.getAge());
        }
        if (userQuery.getGender() != null) {
            queryWrapper.eq("gender", userQuery.getGender());
        }
        if (userQuery.getEmail() != null) {
            queryWrapper.eq("email", userQuery.getEmail());
        }
        if (userQuery.getClazz() != null) {
            queryWrapper.eq("clazz", userQuery.getClazz());
        }
        if (userQuery.getNickname() != null) {
            queryWrapper.eq("nickname", userQuery.getNickname());
        }
        if (userQuery.getStatus() != null) {
            queryWrapper.eq("status", userQuery.getStatus());
        }
    }
}
