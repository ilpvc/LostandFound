package com.example.lostandfound.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lostandfound.entity.Attention;
import com.example.lostandfound.mapper.AttentionMapper;
import com.example.lostandfound.service.AttentionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description:
 *
 * @date:2023/4/8 10:36
 * @author: ilpvc
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AttentionServiceImpl extends ServiceImpl<AttentionMapper, Attention> implements AttentionService {
}