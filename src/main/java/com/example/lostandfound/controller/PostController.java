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

import java.util.ArrayList;
import java.util.Arrays;
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
        log.info(post.getImage());
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


        setQueryWrapper(postQuery);
        List<Post> posts = postService.list(queryWrapper);
        return R.ok().data("list", posts).data("num", posts.size());
    }

    @PostMapping("/normalCondition")
    public R PostNormalCondition(@RequestBody PostQuery postQuery) {
        queryWrapper = new QueryWrapper<>();
        queryWrapper.in("status", postQuery.getStatus());
        queryWrapper.in("type", new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
        List<Post> posts = postService.list(queryWrapper);
        return R.ok().data("list", posts).data("num", posts.size());
    }


    @PostMapping("/rank")
    public R getPostByRank(@RequestBody PostQuery postQuery) {
        QueryWrapper<Post> queryWrapper1 = new QueryWrapper<>();
        if(postQuery.getTypes()!=null){
            queryWrapper1.in("type",postQuery.getTypes());
        }
        if (postQuery.getRankType()==1){
            queryWrapper1.orderByDesc("comment_num");
            queryWrapper1.last("limit 3");
            List<Post> posts = postService.list(queryWrapper1);
            return R.ok().data("list",posts);
        }else if (postQuery.getRankType()==2){
            queryWrapper1.orderByDesc("likes_num");
            queryWrapper1.last("limit 3");
            List<Post> posts = postService.list(queryWrapper1);
            return R.ok().data("list",posts);
        }else if(postQuery.getRankType()==3){
            queryWrapper1.orderByDesc("count");
            queryWrapper1.last("limit 3");
            List<Post> posts = postService.list(queryWrapper1);
            return R.ok().data("list",posts);
        }else{
            return R.error().message("错误参数");
        }
    }


    private void setQueryWrapper(PostQuery postQuery) {
        queryWrapper = new QueryWrapper<>();
        synchronized (this) {
            boolean flag = false;
            if (postQuery.getTitle() != null) {
                queryWrapper.like("title", postQuery.getTitle());
                flag = true;
            }
            if (postQuery.getSearchInfo() != null) {
                queryWrapper.like("title", postQuery.getSearchInfo()).or().like("content", postQuery.getSearchInfo());
                flag = true;
            }
            if (postQuery.getContent() != null) {
                queryWrapper.like("content", postQuery.getContent());
                flag = true;
            }
            if (postQuery.getUserId() != null) {
                queryWrapper.eq("user_id", postQuery.getUserId());
                flag = true;
            }
            if (postQuery.getStatus() != null && postQuery.getStatus().size() != 0) {
                queryWrapper.in("status", postQuery.getStatus());
            }
            if (postQuery.getCollection() != null && postQuery.getCollection().size() != 0) {
                queryWrapper.in("id", postQuery.getCollection());
                flag = true;
            }
            if (postQuery.getCollectionUserId() != null && postQuery.getCollectionUserId().size() != 0) {
                queryWrapper.in("user_id", postQuery.getCollectionUserId());
                flag = true;
            }
            if (postQuery.getTypes() != null && postQuery.getTypes().size() != 0) {
                queryWrapper.in("type", postQuery.getTypes());
                flag = true;
            }
            if (!flag) {
                queryWrapper.eq("id", 0);
            }
        }
    }
}
