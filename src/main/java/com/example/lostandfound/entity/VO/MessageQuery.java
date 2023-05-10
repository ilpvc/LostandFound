package com.example.lostandfound.entity.VO;

import com.example.lostandfound.entity.User;
import lombok.Data;

/**
 * Description:
 *
 * @date:2023/5/7 23:41
 * @author: ilpvc
 */
@Data
public class MessageQuery {

    private Integer type;

    private Integer userId;

    private String content;

    private String result;

    private Integer status;

}
