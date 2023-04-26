package com.example.lostandfound.service.impl;

import com.example.lostandfound.entity.Post;
import com.example.lostandfound.mapper.PostMapper;
import com.example.lostandfound.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 帖子表 服务实现类
 * </p>
 *
 * @author ilpvc
 * @since 2023-03-22 09:28:33
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

}
