package com.example.lostandfound;

import com.example.lostandfound.entity.User;
import com.example.lostandfound.service.UserService;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Component
public class UserTest {
    @Autowired
    UserService userService;

    @Test
    public void getUser(){
        if (userService!=null) {
            User byId = userService.getById(9);
            System.out.println(byId.toString());
        }else{
            System.out.println("null");
        }
    }
}
