package com.example.lostandfound.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lostandfound.entity.Likes;
import com.example.lostandfound.entity.TaskUsers;
import com.example.lostandfound.entity.VO.LikesQuery;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.entity.VO.TaskUserQuery;
import com.example.lostandfound.service.TaskUsersService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/lostandfound/taskUsers")
@CrossOrigin
public class TaskUsersController {


    @Autowired
    TaskUsersService taskUsersService;


    @GetMapping("/")
    public R getAllTaskUsers() {

        return R.ok().data("list", taskUsersService.list());
    }

    @GetMapping("/{id}")
    public R getTaskUsersById(@PathVariable int id) {
        return R.ok().data("item", taskUsersService.getById(id));
    }


    @GetMapping("pageTaskUsers/{pageNo}/{pageCount}")
    public R pageConfig(@PathVariable int pageNo, @PathVariable int pageCount) {
        Page<TaskUsers> page = new Page<>(pageNo, pageCount);
        taskUsersService.page(page, null);
        return R.ok().data("list", page);
    }


    @PostMapping("/addTaskUsers")
    public R addTaskUsers(@RequestBody TaskUsers taskUsers) {
        boolean save = taskUsersService.save(taskUsers);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PutMapping("/updateTaskUsers")
    public R updateTaskUsers(@RequestBody TaskUsers taskUsers) {
        boolean b = taskUsersService.updateById(taskUsers);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @DeleteMapping("/{id}")
    public R deleteTaskUsers(@PathVariable int id) {

        boolean flag = taskUsersService.removeById(id);
        if (flag) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }

    @PostMapping("/condition")
    public R getTaskUserByCondition(@RequestBody TaskUserQuery query) {
        QueryWrapper<TaskUsers> queryWrapper = new QueryWrapper<>();
        setQueryWrapper(query, queryWrapper);
        List<TaskUsers> taskUsersList = taskUsersService.list(queryWrapper);
        return R.ok().data("list", taskUsersList).data("num", taskUsersList.size());
    }


    private void setQueryWrapper(TaskUserQuery query, QueryWrapper<TaskUsers> queryWrapper) {
        if (query.getUserId() != null) {
            queryWrapper.eq("user_id", query.getUserId());
        }
        if (query.getTaskId() != null) {
            queryWrapper.eq("task_id", query.getTaskId());
        }
        if (query.getStatus() != null) {
            queryWrapper.eq("status", query.getStatus());
        }
    }
}
