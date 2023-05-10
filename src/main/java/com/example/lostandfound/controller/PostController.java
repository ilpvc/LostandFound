package com.example.lostandfound.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lostandfound.entity.*;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.entity.VO.PostQuery;
import com.example.lostandfound.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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

    @Autowired
    PostService postService;

    @Autowired
    CommentsService commentsService;

    @Autowired
    CollectionsService collectionsService;

    @Autowired
    LikesService likesService;

    @Autowired
    ReportService reportService;

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
        //删除此帖的所有评论
        QueryWrapper<Comments> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("post_id",id);
        commentsService.remove(queryWrapper);

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
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();

        setQueryWrapper(postQuery,queryWrapper);

        postService.page(page, queryWrapper);
        return R.ok().data("items", page);
    }

    @PostMapping("/condition")
    public R PostCondition(@RequestBody PostQuery postQuery) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        setQueryWrapper(postQuery,queryWrapper);
        List<Post> posts = postService.list(queryWrapper);
        return R.ok().data("list", posts).data("num", posts.size());
    }

    @PostMapping("/normalCondition")
    public R PostNormalCondition(@RequestBody PostQuery postQuery) {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
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


    private void setQueryWrapper(PostQuery postQuery,QueryWrapper<Post> queryWrapper) {
        synchronized (this) {
            boolean flag = false;
            if (postQuery.getTitle() != null) {
                queryWrapper.like("title", postQuery.getTitle());
                flag = true;
            }
            if (postQuery.getSearchInfo() != null) {
                queryWrapper.and(qw->qw.like("title", postQuery.getSearchInfo()).or().like("content", postQuery.getSearchInfo()));
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
                flag = true;
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
            if ("其他".equals(postQuery.getTags())){
                ArrayList<String> tags = new ArrayList<>(Arrays.asList("高数", "算法", "计算机", "四六级", "C语言"));
                for (String s : tags){
                    queryWrapper.notLike("tags",s);
                }
            } else if (postQuery.getTags()!=null){
                queryWrapper.like("tags",postQuery.getTags());
            }
            if (!flag) {
                queryWrapper.eq("id", 0);
            }
        }
    }

    public void deletePostByUserId(int id){


        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",id);

        List<Post> posts = postService.list(queryWrapper);
        for (Post post:posts){
            QueryWrapper<Comments> queryWrapper1 = new QueryWrapper<>();
            queryWrapper.eq("post_id",post.getId());
            commentsService.remove(queryWrapper1);
            QueryWrapper<Collections> collectionsQueryWrapper = new QueryWrapper<>();
            collectionsQueryWrapper.eq("post_id",post.getId());
            collectionsService.remove(collectionsQueryWrapper);

            QueryWrapper<Likes> likesQueryWrapper = new QueryWrapper<>();
            likesQueryWrapper.eq("post_id",post.getId());
            likesService.remove(likesQueryWrapper);

            QueryWrapper<Report> reportQueryWrapper = new QueryWrapper<>();
            reportQueryWrapper.eq("post_id",post.getId());
            reportService.remove(reportQueryWrapper);
        }
        postService.remove(queryWrapper);
    }
}
