package com.example.lostandfound.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 *
 * @date:2023/4/9 20:35
 * @author: ilpvc
 */

@Getter
@Setter
@TableName("report")
@ApiModel(value = "report对象", description = "举报贴")
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("举报ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "post_id")
    private Integer postId;

    @TableField(value = "content")
    private String content;

    @TableField(value = "status")
    private int status;

    @ApiModelProperty("创建时间")
    @TableField(value = "created_time",fill = FieldFill.INSERT)
    private Date createdTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "updated_time",fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;


}
