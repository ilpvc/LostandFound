package com.example.lostandfound.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lostandfound.entity.Report;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.entity.VO.ReportQuery;
import com.example.lostandfound.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 *
 * @date:2023/4/9 20:47
 * @author: ilpvc
 */
@RestController
@Slf4j
@RequestMapping("/lostandfound/report")
@CrossOrigin
public class ReportController {

    @Autowired
    ReportService reportService;

    private QueryWrapper<Report> queryWrapper;

    @PostMapping("/condition")
    public R getReportByCondition(@RequestBody ReportQuery reportQuery) {
        queryWrapper = new QueryWrapper<>();
        setQueryWrapper(reportQuery);
        List<Report> reports = reportService.list(queryWrapper);
        return R.ok().data("list", reports).data("num", reports.size());
    }

    //
//
//
    private void setQueryWrapper(ReportQuery reportQuery) {
        if (reportQuery.getUserId() != null) {
            queryWrapper.eq("user_id", reportQuery.getUserId());
        }
        if (reportQuery.getPostId() != null) {
            queryWrapper.eq("post_id", reportQuery.getPostId());
        }
        if (reportQuery.getContent() != null) {
            queryWrapper.like("content",reportQuery.getContent());
        }
        if (reportQuery.getStatus() != null){
            queryWrapper.eq("status",reportQuery.getStatus());
        }

    }
}
