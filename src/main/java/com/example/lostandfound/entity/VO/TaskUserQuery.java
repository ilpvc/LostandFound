package com.example.lostandfound.entity.VO;

import lombok.Data;

@Data
public class TaskUserQuery {

    private Integer userId;

    private Integer taskId;

    private Integer status;
}
