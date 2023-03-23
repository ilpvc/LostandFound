package com.example.lostandfound.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lostandfound.entity.Follow;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户关注表 前端控制器
 * </p>
 *
 * @author ilpvc
 * @since 2023-03-22 09:28:33
 */
@RestController
@RequestMapping("/lostandfound/follow")
@CrossOrigin
public class FollowController {

    @Autowired
    FollowService followService;


    @GetMapping("/")
    public R getAllFollow() {

        return R.ok().data("list", followService.list());
    }

    @GetMapping("/{id}")
    public R getFollowById(@PathVariable int id) {
        return R.ok().data("item", followService.getById(id));
    }


    @GetMapping("pageFollow/{pageNo}/{pageCount}")
    public R pageConfig(@PathVariable int pageNo, @PathVariable int pageCount) {
        Page<Follow> page = new Page<>(pageNo, pageCount);
        followService.page(page, null);
        return R.ok().data("list", page);
    }


    @PostMapping("/addFollow")
    public R addFollow(@RequestBody Follow follow) {
        boolean save = followService.save(follow);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PutMapping("/updateFollow")
    public R updateFollow(@RequestBody Follow follow) {
        boolean b = followService.updateById(follow);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @DeleteMapping("/{id}")
    public R deleteFollow(@PathVariable int id) {

        boolean flag = followService.removeById(id);
        if (flag) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }

}
