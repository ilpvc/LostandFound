package com.example.lostandfound.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lostandfound.entity.Attribute;
import com.example.lostandfound.entity.VO.AttributeQuery;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ilpvc
 * @since 2023-03-22 09:28:32
 */
@RestController
@RequestMapping("/lostandfound/attribute")
@CrossOrigin
public class AttributeController {

    QueryWrapper<Attribute> queryWrapper;
    @Autowired
    AttributeService attributeService;
    @GetMapping("/")
    public R getAllAttribute(){
        return R.ok().data("list",attributeService.list());
    }

    @GetMapping("/only/{key}")
    public R getQuestionByOnlyKey(@PathVariable String key) {

        queryWrapper = new QueryWrapper<>();

        if (!"".equals(key)){
            queryWrapper.eq("attr_key",key);
        }
        Attribute attributes = attributeService.getOne(queryWrapper);
        return R.ok().data("item", attributes);
    }

    @GetMapping("/{key}")
    public R getQuestionByKey(@PathVariable String key) {

        queryWrapper = new QueryWrapper<>();

        if (!"".equals(key)){
            queryWrapper.eq("attr_key",key);
        }
        List<Attribute> attributes = attributeService.list(queryWrapper);
        return R.ok().data("list", attributes);
    }

    @GetMapping("/like/{key}")
    public R getQuestionLikeKey(@PathVariable String key) {

        queryWrapper = new QueryWrapper<>();

        if (!"".equals(key)){
            queryWrapper.like("attr_key",key);
        }
        List<Attribute> attributes = attributeService.list(queryWrapper);
        return R.ok().data("list", attributes);
    }

    @PostMapping("/update")
    public R updateAttrByKey(@RequestBody Attribute attribute){
        attributeService.updateById(attribute);
        return R.ok();
    }

    private void setQueryWrapper(AttributeQuery attributeQuery){
        if (attributeQuery.getAttrKey() != null) {
            queryWrapper.eq("attr_key", attributeQuery.getAttrKey());
        }

    }
}
