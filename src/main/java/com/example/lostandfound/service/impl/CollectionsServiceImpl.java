package com.example.lostandfound.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lostandfound.entity.Collections;
import com.example.lostandfound.mapper.CollectionsMapper;
import com.example.lostandfound.service.CollectionsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description:
 *
 * @date: 2023/4/4 22:23
 * @author: ilpvc
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CollectionsServiceImpl extends ServiceImpl<CollectionsMapper, Collections> implements CollectionsService {
}
