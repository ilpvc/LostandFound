package com.example.lostandfound.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lostandfound.entity.Collections;
import com.example.lostandfound.entity.Likes;
import com.example.lostandfound.entity.VO.CollectionQuery;
import com.example.lostandfound.entity.VO.LikesQuery;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.service.LikesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
@RequestMapping("/lostandfound/likes")
@CrossOrigin
@Slf4j
public class LikesController {

    @Autowired
    LikesService likesService;


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


    @PostMapping("/addLikes")
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

    @PostMapping("/delete")
    public R deleteLikes(@RequestBody LikesQuery query) {
        boolean flag = likesService.remove(
                new QueryWrapper<Likes>()
                        .eq("user_id", query.getUserId())
                        .eq("post_id", query.getPostId())
        );
        if (flag) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }

    @GetMapping("/User/{id}")
    public R getPostIdByLikeUserId(@PathVariable int id) {
        QueryWrapper<Likes> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        List<Likes> likes = likesService.list(queryWrapper);
        return R.ok().data("list", likes);
    }


    @PostMapping("/condition")
    public R getCollectionByCondition(@RequestBody LikesQuery query) {
        QueryWrapper<Likes> localQueryWrapper = new QueryWrapper<>();
        setQueryWrapper(query,localQueryWrapper);
        List<Likes> likes = likesService.list(localQueryWrapper);
        return R.ok().data("list", likes).data("num", likes.size());
    }

    private void setQueryWrapper(LikesQuery query,QueryWrapper<Likes> queryWrapper) {
        log.info(query.toString());
        if (query.getUserId() != null) {
            queryWrapper.eq("user_id", query.getUserId());
        }
        if (query.getPostId() != null) {
            queryWrapper.eq("post_id", query.getPostId());
        }
        if (query.getStatus()!=null){
            queryWrapper.eq("status",query.getStatus());
        }
        if (query.getPostIds() != null && query.getPostIds().size() != 0) {
            queryWrapper.in("post_id",query.getPostIds());
        }

    }

}
