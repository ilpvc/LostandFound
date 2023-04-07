package com.example.lostandfound.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lostandfound.entity.Post;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.entity.VO.PostQuery;
import com.example.lostandfound.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 帖子表 前端控制器
 * </p>
 *
 * @author ilpvc
 * @since 2023-03-22 09:28:33
 */
@RestController
@RequestMapping("/lostandfound/post")
@CrossOrigin
@Slf4j
public class PostController {


    private QueryWrapper<Post> queryWrapper;
    @Autowired
    PostService postService;


    @GetMapping("/")
    public R getAllPost() {

        return R.ok().data("list", postService.list());
    }

    @GetMapping("/{id}")
    public R getPostById(@PathVariable int id) {
        return R.ok().data("item", postService.getById(id));
    }


    @GetMapping("pagePost/{pageNo}/{pageCount}")
    public R pageConfig(@PathVariable int pageNo, @PathVariable int pageCount) {
        Page<Post> page = new Page<>(pageNo, pageCount);
        postService.page(page, null);
        return R.ok().data("list", page);
    }


    @PostMapping("/addPost")
    public R addPost(@RequestBody Post post) {
        boolean save = postService.save(post);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PutMapping("/updateUser")
    public R updatePost(@RequestBody Post post) {
        boolean b = postService.updateById(post);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @DeleteMapping("/{id}")
    public R deletePost(@PathVariable int id) {

        boolean flag = postService.removeById(id);
        if (flag) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }


    @PostMapping("/pagePostCondition/{pageNo}/{pageCount}")
    public R pagePostCondition(@PathVariable int pageNo,
                               @PathVariable int pageCount,
                               @RequestBody PostQuery postQuery) {
        Page<Post> page = new Page<>(pageNo, pageCount);
        queryWrapper = new QueryWrapper<>();

        setQueryWrapper(postQuery);

        postService.page(page, queryWrapper);
        return R.ok().data("items", page);
    }

    @PostMapping("/condition")
    public R PostCondition(@RequestBody PostQuery postQuery) {
        log.info(postQuery.getCollection().toString());
        queryWrapper = new QueryWrapper<>();
        setQueryWrapper(postQuery);
        List<Post> posts = postService.list(queryWrapper);
        return R.ok().data("list", posts).data("num", posts.size());
    }





    private void setQueryWrapper(PostQuery postQuery){
        if (postQuery.getType() != null) {
            queryWrapper.eq("type", postQuery.getType());
        }
        if (postQuery.getTitle() != null) {
            queryWrapper.eq("title", postQuery.getTitle());
        }
        if (postQuery.getContent() != null) {
            queryWrapper.eq("content", postQuery.getContent());
        }
        if (postQuery.getUserId() != null) {
            queryWrapper.eq("user_id", postQuery.getUserId());
        }
        if (postQuery.getStatus() != null) {
            queryWrapper.eq("status", postQuery.getStatus());
        }
        if (postQuery.getCollection()!=null){
            queryWrapper.in("id",postQuery.getCollection());
        }
    }
}
