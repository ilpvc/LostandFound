package com.example.lostandfound.service.impl;

import com.example.lostandfound.entity.Attribute;
import com.example.lostandfound.mapper.AttributeMapper;
import com.example.lostandfound.service.AttributeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ilpvc
 * @since 2023-03-22 09:28:32
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AttributeServiceImpl extends ServiceImpl<AttributeMapper, Attribute> implements AttributeService {

}
