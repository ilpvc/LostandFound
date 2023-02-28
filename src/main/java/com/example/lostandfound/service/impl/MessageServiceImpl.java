package com.example.lostandfound.service.impl;

import com.example.lostandfound.entity.Message;
import com.example.lostandfound.mapper.MessageMapper;
import com.example.lostandfound.service.MessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 消息表 服务实现类
 * </p>
 *
 * @author ilpvc
 * @since 2023-02-24 10:20:13
 */
@Service
@Transactional
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}
