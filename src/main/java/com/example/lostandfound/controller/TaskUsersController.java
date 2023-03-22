package com.example.lostandfound.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lostandfound.entity.TaskUsers;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.service.TaskUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ilpvc
 * @since 2023-03-22 09:28:33
 */
@RestController
@RequestMapping("/lostandfound/task-users")
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
}
