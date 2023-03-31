package com.example.lostandfound.repository;

import com.example.lostandfound.entity.User;

/**
 * Description:
 *
 * @date:2023/3/31 9:06
 * @author: ilpvc
 */
public interface UserRepository {

    User findUserByEmail(String email);
}
