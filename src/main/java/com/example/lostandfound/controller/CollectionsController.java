package com.example.lostandfound.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lostandfound.entity.Attention;
import com.example.lostandfound.entity.Collections;
import com.example.lostandfound.entity.Likes;
import com.example.lostandfound.entity.VO.AttentionQuery;
import com.example.lostandfound.entity.VO.CollectionQuery;
import com.example.lostandfound.entity.VO.LikesQuery;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.service.CollectionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 *
 * @date:2023/4/4 22:26
 * @author: ilpvc
 */
@RestController
@RequestMapping("/lostandfound/Collections")
@CrossOrigin
public class CollectionsController {


    @Autowired
    CollectionsService collectionsService;

    @PostMapping("/addCollections")
    public R addCollection(@RequestBody Collections collections) {
        boolean save = collectionsService.save(collections);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PutMapping("/updateCollections")
    public R updateCollection(@RequestBody Collections collections) {
        boolean save = collectionsService.updateById(collections);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PostMapping("/delete")
    public R deleteCollections(@RequestBody CollectionQuery query) {
        boolean flag = collectionsService.remove(
                new QueryWrapper<Collections>()
                        .eq("user_id",query.getUserId())
                        .eq("post_id",query.getPostId())
        );
        if (flag) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }

    @PostMapping("/condition")
    public R getCollectionByCondition(@RequestBody CollectionQuery query) {
        QueryWrapper<Collections> queryWrapper = new QueryWrapper<>();
        setQueryWrapper(query,queryWrapper);
        List<Collections> collections = collectionsService.list(queryWrapper);
        return R.ok().data("list", collections).data("num", collections.size());
    }

    private void setQueryWrapper(CollectionQuery query,QueryWrapper<Collections> queryWrapper){
        if (query.getUserId() != null) {
            queryWrapper.eq("user_id", query.getUserId());
        }
        if (query.getPostId() != null) {
            queryWrapper.eq("post_id", query.getPostId());
        }
        if (query.getPostIds()!=null&&query.getPostIds().size()!=0){
            queryWrapper.in("post_id", query.getPostIds());
        }
        if (query.getStatus()!=null){
            queryWrapper.eq("status",query.getStatus());
        }
    }
}
