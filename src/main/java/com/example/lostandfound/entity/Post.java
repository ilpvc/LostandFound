package com.example.lostandfound.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 帖子表
 * </p>
 *
 * @author ilpvc
 * @since 2023-03-22 09:28:33
 */
@Getter
@Setter
@TableName("post")
@ApiModel(value = "Post对象", description = "帖子表")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("帖子ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("帖子类型（1：失物贴，2：招领贴，3：普通帖）")
    @TableField("type")
    private Integer type;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;


    @ApiModelProperty("图片url")
    @TableField("image")
    private String image;
    @ApiModelProperty("内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("热度")
    @TableField("count")
    private Integer count;
    @ApiModelProperty("作者ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("创建时间")
    @TableField(value = "created_time",fill = FieldFill.INSERT)
    private Date createdTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "updated_time",fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

    @ApiModelProperty("逻辑删除标识")
    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty("状态（1：正常，2：待审核，3：已删除，4：已禁用，5：已完成）")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("完成人的用户id")
    @TableField("back_user_id")
    private Integer backUserId;


}
