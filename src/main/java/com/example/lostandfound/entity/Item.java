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
 * 物品表
 * </p>
 *
 * @author ilpvc
 * @since 2023-02-24 10:20:13
 */
@Getter
@Setter
@TableName("item")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 物品名称
     */
    @TableField("title")
    private String title;

    /**
     * 物品描述
     */
    @TableField("description")
    private String description;

    /**
     * 失物/招领，0表示失物，1表示招领
     */
    @TableField("lost_or_found")
    private Boolean lostOrFound;

    /**
     * 失物/招领时间
     */
    @TableField("lost_or_found_time")
    private LocalDateTime lostOrFoundTime;

    /**
     * 地点
     */
    @TableField("location")
    private String location;

    /**
     * 联系人
     */
    @TableField("contact")
    private String contact;

    /**
     * 联系人电话
     */
    @TableField("contact_phone")
    private String contactPhone;

    /**
     * 发布者ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 删除时间
     */
    @TableField("delete_time")
    private LocalDateTime deleteTime;

    /**
     * 是否删除，0表示未删除，1表示已删除
     */
    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;

    /**
     * 是否已完成失物招领，当该记录对应的物品已找到并归还时为TRUE，否则为FALSE。
     */
    @TableField("is_completed")
    private Boolean isCompleted;


}
