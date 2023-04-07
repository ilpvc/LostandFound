package com.example.lostandfound.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.example.lostandfound.entity.User;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Timer;

/**
 * Description:
 *
 * @date:2023/4/1 22:08
 * @author: ilpvc
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject,"header",String.class,"http://q1.qlogo.cn/g?b=qq&nk=2694485351&s=640");
        this.strictInsertFill(metaObject,"created_time", LocalDateTime.class,LocalDateTime.now());

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject,"updated_time", LocalDateTime.class,LocalDateTime.now());
    }
}
