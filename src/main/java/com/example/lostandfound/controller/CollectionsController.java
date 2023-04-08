package com.example.lostandfound.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lostandfound.entity.Collections;
import com.example.lostandfound.entity.VO.CollectionQuery;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.service.CollectionsService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CollectionsController {


    @Autowired
    CollectionsService collectionsService;
    QueryWrapper<Collections> queryWrapper;

    @PostMapping("/condition")
    public R collectionCondition(@RequestBody CollectionQuery collectionQuery) {
        queryWrapper = new QueryWrapper<>();
        setQueryWrapper(collectionQuery);
        List<Collections> collections = collectionsService.list(queryWrapper);
        return R.ok().data("list", collections).data("num", collections.size());
    }
    //
//
//
    private void setQueryWrapper(CollectionQuery collectionQuery){
        if (collectionQuery.getUserId() != null) {
            queryWrapper.eq("user_id", collectionQuery.getUserId());
        }
        if (collectionQuery.getPostId() != null) {
            queryWrapper.eq("post_id", collectionQuery.getPostId());
        }

    }
}
