package com.example.lostandfound.entity.VO;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description:
 *
 * @date:2023/3/22 14:28
 * @author: ilpvc
 */

@Data
public class CommentsQuery {

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


    @ApiModelProperty("评论类型，1、一级评论 2、二级评论")
    @TableField("comment_type")
    private Integer commentType;

    @ApiModelProperty("父级评论id，若为null则为一级评论")
    @TableField("parent_id")
    private Integer parentId;

}
