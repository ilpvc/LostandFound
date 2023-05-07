package com.example.lostandfound.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lostandfound.entity.Likes;
import com.example.lostandfound.entity.Message;
import com.example.lostandfound.entity.VO.LikesQuery;
import com.example.lostandfound.entity.VO.MessageQuery;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 *
 * @date:2023/5/7 20:56
 * @author: ilpvc
 */

@RestController
@RequestMapping("/lostandfound/message")
@CrossOrigin
@Slf4j
public class MessageController {

    MessageService messageService;
    @Autowired
    MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @GetMapping("/")
    public R getAllMessages() {
        return R.ok().data("list", messageService.list());
    }

    @GetMapping("/{id}")
    public R getMessageById(@PathVariable int id) {
        return R.ok().data("item", messageService.getById(id));
    }

    @GetMapping("pageMessage/{pageNo}/{pageCount}")
    public R pageConfig(@PathVariable int pageNo, @PathVariable int pageCount) {
        Page<Message> page = new Page<>(pageNo, pageCount);
        messageService.page(page, null);
        return R.ok().data("list", page);
    }

    @PostMapping("/addMessage")
    public R addMessage(@RequestBody Message message) {
        boolean save = messageService.save(message);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PutMapping("/updateMessage")
    public R updateMessage(@RequestBody Message message) {
        boolean b = messageService.updateById(message);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

//    @PostMapping("/delete")
//    public R deleteLikes(@RequestBody LikesQuery query) {
//        boolean flag = likesService.remove(
//                new QueryWrapper<Likes>()
//                        .eq("user_id", query.getUserId())
//                        .eq("post_id", query.getPostId())
//        );
//        if (flag) {
//            return R.ok().message("删除成功");
//        } else {
//            return R.error().message("删除失败");
//        }
//    }

    @DeleteMapping("/delete")
    public R deleteMessageById(@PathVariable int id){
        messageService.removeById(id);
        return R.ok();
    }

    @PostMapping("/condition")
    public R getMessageByCondition(@RequestBody MessageQuery query) {
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        setQueryWrapper(query,queryWrapper);
        List<Message> messages = messageService.list(queryWrapper);
        return R.ok().data("list", messages).data("num", messages.size());
    }

    private void setQueryWrapper(MessageQuery query,QueryWrapper<Message> queryWrapper) {
        if (query.getUserId() != null) {
            queryWrapper.eq("user_id", query.getUserId());
        }
        if (query.getType() != null) {
            queryWrapper.eq("type", query.getType());
        }
        if (query.getContent()!=null){
            queryWrapper.like("content",query.getContent());
        }

    }

}
