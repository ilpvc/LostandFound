package com.example.lostandfound.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lostandfound.entity.Blacklist;
import com.example.lostandfound.mapper.BlacklistMapper;
import com.example.lostandfound.service.BlacklistService;
import org.springframework.stereotype.Service;

@Service
public class BlacklistServiceImpl extends ServiceImpl<BlacklistMapper, Blacklist> implements BlacklistService {
}
