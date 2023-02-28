package com.example.lostandfound.service.impl;

import com.example.lostandfound.entity.ThumbUp;
import com.example.lostandfound.mapper.ThumbUpMapper;
import com.example.lostandfound.service.ThumbUpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 点赞表 服务实现类
 * </p>
 *
 * @author ilpvc
 * @since 2023-02-24 10:20:13
 */
@Service
@Transactional
public class ThumbUpServiceImpl extends ServiceImpl<ThumbUpMapper, ThumbUp> implements ThumbUpService {

}
