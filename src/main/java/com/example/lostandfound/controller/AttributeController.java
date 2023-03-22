package com.example.lostandfound.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lostandfound.entity.Attribute;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.service.AttributeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

    AttributeService attributeService;
    @GetMapping("/")
    public R getAllAttribute(){
        QueryWrapper<Attribute> queryWrapper = new QueryWrapper<Attribute>();
        queryWrapper.eq("key","question");
        return R.ok().data("list",attributeService.getOne(queryWrapper));
    }
}
