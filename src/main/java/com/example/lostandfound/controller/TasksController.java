package com.example.lostandfound.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lostandfound.entity.Tasks;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.service.TasksService;
import com.example.lostandfound.utils.RedisCache;
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
@RequestMapping("/lostandfound/tasks")
@CrossOrigin
public class TasksController {


    @Autowired
    TasksService tasksService;

    @Autowired
    RedisCache redisCache;

    @GetMapping("/")
    public R getAllTasks() {
        return R.ok().data("list", tasksService.list());
    }

    @GetMapping("/{id}")
    public R getTasksById(@PathVariable int id) {
        return R.ok().data("item", tasksService.getById(id));
    }


    @GetMapping("pageTasks/{pageNo}/{pageCount}")
    public R pageConfig(@PathVariable int pageNo, @PathVariable int pageCount) {
        Page<Tasks> page = new Page<>(pageNo, pageCount);
        tasksService.page(page, null);
        return R.ok().data("list", page);
    }


    @PostMapping("/addTasks")
    public R addTasks(@RequestBody Tasks tasks) {
        boolean save = tasksService.save(tasks);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PutMapping("/updateTasks")
    public R updateTasks(@RequestBody Tasks tasks) {
        boolean b = tasksService.updateById(tasks);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @DeleteMapping("/{id}")
    public R deleteTasks(@PathVariable int id) {

        boolean flag = tasksService.removeById(id);
        if (flag) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }
}
