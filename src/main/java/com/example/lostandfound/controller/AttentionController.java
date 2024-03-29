package com.example.lostandfound.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lostandfound.entity.Attention;
import com.example.lostandfound.entity.User;
import com.example.lostandfound.entity.VO.AttentionQuery;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.service.AttentionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 *
 * @date:2023/4/8 10:38
 * @author: ilpvc
 */
@RestController
@RequestMapping("/lostandfound/attention")
@CrossOrigin
@Slf4j
public class AttentionController {


    AttentionService attentionService;
    AttentionController(AttentionService attentionService){
        this.attentionService = attentionService;
    }



    @PostMapping("/addAttention")
    public R addAttention(@RequestBody Attention attention) {
        if (attention.getAttentionUserId()==null){
            return R.error().message("还没有登录，关注失败");
        }
        boolean save = attentionService.save(attention);
        if (save) {
            return R.ok().message("关注成功");
        } else {
            return R.error().message("关注失败");
        }
    }

    @PutMapping("/updateAttention")
    public R updateAttention(@RequestBody Attention attention) {
        boolean save = attentionService.updateById(attention);
        if (save) {
            return R.ok().message("更新成功");
        } else {
            return R.error().message("更新失败");
        }
    }

    @PostMapping("/delete")
    public R deleteAttention(@RequestBody AttentionQuery query) {
        QueryWrapper<Attention> queryWrapper = new QueryWrapper<>();
        setQueryWrapper(query,queryWrapper);
        boolean save = attentionService.remove(queryWrapper);
        if (save) {
            return R.ok().message("取消关注");
        } else {
            return R.error().message("取消失败");
        }
    }
    @PostMapping("/condition")
    public R attentionCondition(@RequestBody AttentionQuery attentionQuery) {
        QueryWrapper<Attention> queryWrapper = new QueryWrapper<>();
        setQueryWrapper(attentionQuery,queryWrapper);
        List<Attention> attentions = attentionService.list(queryWrapper);
        return R.ok().data("list", attentions).data("num", attentions.size());
    }
    //
//
//
    private void setQueryWrapper(AttentionQuery attentionQuery,QueryWrapper<Attention> queryWrapper){
        if (attentionQuery.getAttentionUserId() != null) {
            queryWrapper.eq("attention_user_id", attentionQuery.getAttentionUserId());
        }
        if (attentionQuery.getAttentionedUserId() != null) {
            queryWrapper.eq("attentioned_user_id", attentionQuery.getAttentionedUserId());
        }
        if (attentionQuery.getStatus()!=null){
            queryWrapper.eq("status",attentionQuery.getStatus());
        }

    }
}