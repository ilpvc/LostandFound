package com.example.lostandfound.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 收藏表
 * </p>
 *
 * @author ilpvc
 * @since 2023-02-24 10:20:13
 */
@Getter
@Setter
@TableName("favorite")
public class Favorite implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 收藏记录ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 收藏记录所属的用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 收藏的帖子ID
     */
    @TableField("item_id")
    private Long itemId;

    /**
     * 收藏记录的创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 收藏记录的删除时间
     */
    @TableField("delete_time")
    private LocalDateTime deleteTime;

    /**
     * 表示该记录是否被删除，0表示未删除，1表示已删除
     */
    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;


}
