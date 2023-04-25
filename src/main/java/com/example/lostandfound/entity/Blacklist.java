package com.example.lostandfound.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@TableName("blacklist")
@ApiModel(value = "Blacklist对象", description = "")
public class Blacklist implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    private Integer id;

    @TableField("user_id")
    private Integer userId;

    @TableField("other_user_id")
    private Integer otherUserId;

    @TableField(value = "created_time",fill = FieldFill.INSERT)
    private Date createdTime;
}
