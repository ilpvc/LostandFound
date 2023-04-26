package com.example.lostandfound.service.impl;

import com.example.lostandfound.entity.Tasks;
import com.example.lostandfound.mapper.TasksMapper;
import com.example.lostandfound.service.TasksService;
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
public class TasksServiceImpl extends ServiceImpl<TasksMapper, Tasks> implements TasksService {

}
