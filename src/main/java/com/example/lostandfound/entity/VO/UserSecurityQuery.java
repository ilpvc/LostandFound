package com.example.lostandfound.entity.VO;

import lombok.Data;

/**
 * Description:
 *
 * @date:2023/4/13 19:47
 * @author: ilpvc
 */
@Data
public class UserSecurityQuery {
    private Integer userId;
    private String nickname;
    private String originPassword;
    private String password;
    private String email;
}
