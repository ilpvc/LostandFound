package com.example.lostandfound.service.impl;

import com.example.lostandfound.entity.Comments;
import com.example.lostandfound.mapper.CommentsMapper;
import com.example.lostandfound.service.CommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author ilpvc
 * @since 2023-03-22 09:28:33
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements CommentsService {

}
