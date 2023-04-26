package com.example.lostandfound.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lostandfound.entity.Report;
import com.example.lostandfound.mapper.ReportMapper;
import com.example.lostandfound.service.ReportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description:
 *
 * @date:2023/4/9 20:45
 * @author: ilpvc
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService {
}
