package com.example.lostandfound.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 *
 * @date:2023/4/4 22:16
 * @author: ilpvc
 */

@Getter
@Setter
@TableName("collection")
@ApiModel(value = "collections对象", description = "")
public class Collections implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableField("id")
    private int id;

    @ApiModelProperty("用户Id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("帖子Id")
    @TableField("post_id")
    private Integer postId;

    @TableField("status")
    private Integer status;

    @TableField("created_time")
    private Date createdTime;
}
