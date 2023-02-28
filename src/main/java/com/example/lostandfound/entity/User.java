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
 * 用户表
 * </p>
 *
 * @author ilpvc
 * @since 2023-02-24 10:20:13
 */
@Getter
@Setter
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 是否删除，0表示未删除，1表示已删除
     */
    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 删除时间
     */
    @TableField("delete_time")
    private Date deleteTime;


}
