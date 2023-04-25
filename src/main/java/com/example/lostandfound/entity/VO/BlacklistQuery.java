package com.example.lostandfound.entity.VO;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class BlacklistQuery {

    private Integer userId;

    private Integer otherUserId;
}
