package com.example.lostandfound.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lostandfound.entity.Comments;
import com.example.lostandfound.entity.VO.CommentsQuery;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 评论表 前端控制器
 * </p>
 *
 * @author ilpvc
 * @since 2023-03-22 09:28:33
 */
@RestController
@RequestMapping("/lostandfound/comments")
@CrossOrigin
public class CommentsController {

    @Autowired
    CommentsService commentsService;


    @GetMapping("/")
    public R getAllComments() {

        return R.ok().data("list", commentsService.list());
    }

    @GetMapping("/{id}")
    public R getCommentsById(@PathVariable int id) {
        return R.ok().data("item", commentsService.getById(id));
    }


    @GetMapping("pageComments/{pageNo}/{pageCount}")
    public R pageConfig(@PathVariable int pageNo, @PathVariable int pageCount) {
        Page<Comments> page = new Page<>(pageNo, pageCount);
        commentsService.page(page, null);
        return R.ok().data("list", page);
    }


    @PostMapping("/addComments")
    public R addComments(@RequestBody Comments comments) {
        boolean save = commentsService.save(comments);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PutMapping("/updateComments")
    public R updateComments(@RequestBody Comments comments) {
        boolean b = commentsService.updateById(comments);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @DeleteMapping("/{id}")
    public R deleteComments(@PathVariable int id) {

        boolean flag = commentsService.removeById(id);
        if (flag) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }



//
    @PostMapping("/pageCommentsCondition/{pageNo}/{pageCount}")
    public R pageCommentsCondition(@PathVariable int pageNo,
                               @PathVariable int pageCount,
                               @RequestBody CommentsQuery commentsQuery) {
        Page<Comments> page = new Page<>(pageNo, pageCount);
        QueryWrapper<Comments> queryWrapper = new QueryWrapper<>();

        setQueryWrapper(commentsQuery,queryWrapper);

        commentsService.page(page, queryWrapper);
        return R.ok().data("items", page);
    }

    @PostMapping("/condition")
    public R CommentsCondition(@RequestBody CommentsQuery commentsQuery) {
        QueryWrapper<Comments> queryWrapper = new QueryWrapper<>();
        setQueryWrapper(commentsQuery,queryWrapper);
        List<Comments> comments = commentsService.list(queryWrapper);
        return R.ok().data("list", comments).data("num", comments.size());
    }
//
//
//
    private void setQueryWrapper(CommentsQuery commentsQuery,QueryWrapper<Comments> queryWrapper){
        if (commentsQuery.getCommenterId() != null) {
            queryWrapper.eq("commenter_id", commentsQuery.getCommenterId());
        }
        if (commentsQuery.getCommentType() != null) {
            queryWrapper.eq("comment_type", commentsQuery.getCommentType());
        }
        if (commentsQuery.getCommentedUserId() != null) {
            queryWrapper.eq("commented_user_id", commentsQuery.getCommentedUserId());
        }
        if (commentsQuery.getPostIds() != null) {
            queryWrapper.eq("post_id", commentsQuery.getPostId());
        }
        if (commentsQuery.getPostId() != null) {
            queryWrapper.eq("post_id", commentsQuery.getPostId());
        }
        if (commentsQuery.getContent() != null) {
            queryWrapper.like("content", commentsQuery.getContent());
        }
        if (commentsQuery.getParentId() != null) {
            queryWrapper.eq("parent_id", commentsQuery.getParentId());
        }
        if (commentsQuery.getStatus()!=null){
            queryWrapper.eq("status",commentsQuery.getStatus());
        }
    }

}
