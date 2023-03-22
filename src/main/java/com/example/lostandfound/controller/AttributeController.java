package com.example.lostandfound.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lostandfound.entity.Attribute;
import com.example.lostandfound.entity.User;
import com.example.lostandfound.entity.VO.AttributeQuery;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.entity.VO.UserQuery;
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
public class AttributeController {

    QueryWrapper<Attribute> queryWrapper;
    @Autowired
    AttributeService attributeService;
    @GetMapping("/")
    public R getAllAttribute(){
        return R.ok().data("list",attributeService.list());
    }

    @PostMapping("/condition")
    public R UserCondition(@RequestBody AttributeQuery attributeQuery) {
        queryWrapper = new QueryWrapper<>();
        setQueryWrapper(attributeQuery);
        List<Attribute> attributes = attributeService.list(queryWrapper);
        return R.ok().data("list", attributes).data("num", attributes.size());
    }



    private void setQueryWrapper(AttributeQuery attributeQuery){
        if (attributeQuery.getAttrKey() != null) {
            queryWrapper.eq("attr_key", attributeQuery.getAttrKey());
        }

    }
}
