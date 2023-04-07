package com.example.lostandfound.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lostandfound.entity.Likes;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ilpvc
 * @since 2023-03-22 09:28:33
 */
@RestController
@RequestMapping("/lostandfound/likes")
@CrossOrigin
public class LikesController {

    @Autowired
    LikesService likesService;

    QueryWrapper<Likes> queryWrapper;


    @GetMapping("/")
    public R getAllLikes() {

        return R.ok().data("list", likesService.list());
    }

    @GetMapping("/{id}")
    public R getLikesById(@PathVariable int id) {
        return R.ok().data("item", likesService.getById(id));
    }


    @GetMapping("pageLikes/{pageNo}/{pageCount}")
    public R pageConfig(@PathVariable int pageNo, @PathVariable int pageCount) {
        Page<Likes> page = new Page<>(pageNo, pageCount);
        likesService.page(page, null);
        return R.ok().data("list", page);
    }


    @PostMapping("/addlikes")
    public R addLikes(@RequestBody Likes likes) {
        boolean save = likesService.save(likes);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PutMapping("/updateLikes")
    public R updateLikes(@RequestBody Likes likes) {
        boolean b = likesService.updateById(likes);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @DeleteMapping("/{id}")
    public R deleteLikes(@PathVariable int id) {

        boolean flag = likesService.removeById(id);
        if (flag) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }

    @GetMapping("/User/{id}")
    public R getPostIdByLikeUserId(@PathVariable int id){
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",id);
        List<Likes> likes = likesService.list(queryWrapper);
        return R.ok().data("list",likes);
    }

}
