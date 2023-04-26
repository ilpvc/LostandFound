package com.example.lostandfound.service.impl;

import com.example.lostandfound.entity.UserSettings;
import com.example.lostandfound.mapper.UserSettingsMapper;
import com.example.lostandfound.service.UserSettingsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ilpvc
 * @since 2023-03-22 09:28:33
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserSettingsServiceImpl extends ServiceImpl<UserSettingsMapper, UserSettings> implements UserSettingsService {

}
