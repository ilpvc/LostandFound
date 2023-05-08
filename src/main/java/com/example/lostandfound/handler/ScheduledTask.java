package com.example.lostandfound.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lostandfound.entity.Attribute;
import com.example.lostandfound.entity.TaskUsers;
import com.example.lostandfound.service.AttributeService;
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
    AttributeService attributeService;

    @Autowired
    ScheduledTask(TaskUsersService taskUsersService, AttributeService attributeService) {
        this.taskUsersService = taskUsersService;
        this.attributeService = attributeService;
    }

    @Scheduled(cron = "0 0 4 * * ?")
    public void clearTaskUser() {
        taskUsersService.remove(new QueryWrapper<TaskUsers>());
        Attribute attribute = new Attribute();
        attribute.setId(1);
        attribute.setAttrKey("complete_nowaday");
        attribute.setNumberValue(0);
        attributeService.updateById(attribute);
    }
}
