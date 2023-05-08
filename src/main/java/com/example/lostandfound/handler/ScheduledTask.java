package com.example.lostandfound.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lostandfound.entity.TaskUsers;
import com.example.lostandfound.service.TaskUsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class ScheduledTask {

    TaskUsersService taskUsersService;

    @Autowired
    ScheduledTask(TaskUsersService taskUsersService){
        this.taskUsersService = taskUsersService;
    }

    @Scheduled(cron = "0 0 4 * * ?")
    public void clearTaskUser(){
        taskUsersService.remove(new QueryWrapper<TaskUsers>());
        log.info(new Date().toString());
    }
}
