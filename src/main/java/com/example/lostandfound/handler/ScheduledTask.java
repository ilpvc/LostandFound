package com.example.lostandfound.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.lostandfound.entity.Attribute;
import com.example.lostandfound.entity.TaskUsers;
import com.example.lostandfound.entity.User;
import com.example.lostandfound.entity.UserSettings;
import com.example.lostandfound.service.AttributeService;
import com.example.lostandfound.service.TaskUsersService;
import com.example.lostandfound.service.UserService;
import com.example.lostandfound.service.UserSettingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class ScheduledTask {

    TaskUsersService taskUsersService;
    AttributeService attributeService;
    UserSettingsService userSettingsService;
    UserService userService;
    @Autowired
    ScheduledTask(TaskUsersService taskUsersService,
                  AttributeService attributeService,
                  UserService userService,
                  UserSettingsService userSettingsService) {
        this.taskUsersService = taskUsersService;
        this.attributeService = attributeService;
        this.userService = userService;
        this.userSettingsService = userSettingsService;
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

//    @Scheduled(cron = "0 31 9 * * ?")
//    public void synchronizationUserSetting(){
//        List<User> users = userService.list();
//        for (User user:users){
//            UserSettings u = new UserSettings();
//            u.setUserId(user.getId());
//            userSettingsService.save(u);
//        }
//    }

}
