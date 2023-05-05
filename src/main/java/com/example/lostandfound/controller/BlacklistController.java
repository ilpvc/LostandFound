package com.example.lostandfound.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lostandfound.entity.Attention;
import com.example.lostandfound.entity.Blacklist;
import com.example.lostandfound.entity.VO.AttentionQuery;
import com.example.lostandfound.entity.VO.BlacklistQuery;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.service.BlacklistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lostandfound/blacklist")
@CrossOrigin
@Slf4j
public class BlacklistController {

    @Autowired
    BlacklistService blacklistService;

    @PostMapping("/addBlacklist")
    public R addBlacklist(@RequestBody Blacklist blacklist) {
        if (blacklist.getUserId()==null){
            return R.error().message("还没有登录，拉黑失败");
        }
        boolean save = blacklistService.save(blacklist);
        if (save) {
            return R.ok().message("关注成功");
        } else {
            return R.error().message("关注失败");
        }
    }

    @PostMapping("/delete")
    public R deleteBlacklist(@RequestBody BlacklistQuery query) {
        QueryWrapper<Blacklist> queryWrapper = new QueryWrapper<>();
        setQueryWrapper(query,queryWrapper);
        boolean save = blacklistService.remove(queryWrapper);
        if (save) {
            return R.ok().message("取消关注");
        } else {
            return R.error().message("取消失败");
        }
    }
    @PostMapping("/condition")
    public R blacklistCondition(@RequestBody BlacklistQuery query) {
        QueryWrapper<Blacklist> queryWrapper = new QueryWrapper<>();
        setQueryWrapper(query,queryWrapper);
        List<Blacklist> blacklists = blacklistService.list(queryWrapper);
        return R.ok().data("list", blacklists).data("num", blacklists.size());
    }
    //
//
//
    private void setQueryWrapper(BlacklistQuery query,QueryWrapper<Blacklist> queryWrapper){
        if (query.getUserId() != null) {
            queryWrapper.eq("user_id", query.getUserId());
        }
        if (query.getOtherUserId() != null) {
            queryWrapper.eq("other_user_id", query.getOtherUserId());
        }

    }
}
