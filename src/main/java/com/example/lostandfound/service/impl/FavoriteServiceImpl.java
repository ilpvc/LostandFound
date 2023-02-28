package com.example.lostandfound.service.impl;

import com.example.lostandfound.entity.Favorite;
import com.example.lostandfound.mapper.FavoriteMapper;
import com.example.lostandfound.service.FavoriteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 收藏表 服务实现类
 * </p>
 *
 * @author ilpvc
 * @since 2023-02-24 10:20:13
 */
@Service
@Transactional
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite> implements FavoriteService {

}
