package com.example.lostandfound.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author ilpvc
 * @since 2023-03-22 09:28:33
 */
@Getter
@Setter
@TableName("comments")
@ApiModel(value = "Comments对象", description = "评论表")
public class Comments implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("关联帖子表id")
    @TableField("post_id")
    private Integer postId;

    @ApiModelProperty("评论人id")
    @TableField("commenter_id")
    private Integer commenterId;

    @ApiModelProperty("被评论人id")
    @TableField("commented_user_id")
    private Integer commentedUserId;

    @ApiModelProperty("评论内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("创建时间")
    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private Date createdTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "updated_time", fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

    @ApiModelProperty("删除时间，若为null则未删除")
    @TableField("deleted_time")
    private Date deletedTime;

    @ApiModelProperty("评论类型，1、一级评论 2、二级评论")
    @TableField("comment_type")
    private Integer commentType;

    @ApiModelProperty("父级评论id，若为null则为一级评论")
    @TableField("parent_id")
    private Integer parentId;
    @TableField("status")
    private Integer status;


}
