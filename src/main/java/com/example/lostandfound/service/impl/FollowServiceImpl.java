package com.example.lostandfound.service.impl;

import com.example.lostandfound.entity.Follow;
import com.example.lostandfound.mapper.FollowMapper;
import com.example.lostandfound.service.FollowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户关注表 服务实现类
 * </p>
 *
 * @author ilpvc
 * @since 2023-03-22 09:28:33
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowService {

}
