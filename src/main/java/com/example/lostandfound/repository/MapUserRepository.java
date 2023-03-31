package com.example.lostandfound.repository;

import com.example.lostandfound.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Description:
 *
 * @date:2023/3/31 9:30
 * @author: ilpvc
 */

@Slf4j
public class MapUserRepository implements UserRepository{

    private Map<String,User> emailToUser;


    public MapUserRepository(Map<String,User> emailToUser){
        this.emailToUser = emailToUser;
    }
    @Override
    public User findUserByEmail(String email) {
        log.info(emailToUser.toString());
        return this.emailToUser.get(email);
    }
}
