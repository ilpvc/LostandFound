package com.example.lostandfound.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 点赞表
 * </p>
 *
 * @author ilpvc
 * @since 2023-02-24 10:20:13
 */
@Getter
@Setter
@TableName("thumb_up")
public class ThumbUp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 点赞记录ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 点赞记录所属的用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 被点赞的帖子ID
     */
    @TableField("item_id")
    private Long itemId;

    /**
     * 点赞记录的创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 点赞记录的删除时间
     */
    @TableField("delete_time")
    private Date deleteTime;

    /**
     * 表示该记录是否被删除，0表示未删除，1表示已删除
     */
    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;


}
