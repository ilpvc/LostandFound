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
 * 举报表
 * </p>
 *
 * @author ilpvc
 * @since 2023-02-24 10:20:13
 */
@Getter
@Setter
@TableName("report")
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 举报类型，1表示举报物品，2表示举报用户
     */
    @TableField("report_type")
    private Integer reportType;

    /**
     * 举报内容
     */
    @TableField("report_content")
    private String reportContent;

    /**
     * 被举报的物品id，外键，关联item表
     */
    @TableField("item_id")
    private Long itemId;

    /**
     * 被举报的用户id，外键，关联user表
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 举报人id，外键，关联user表
     */
    @TableField("report_user_id")
    private Long reportUserId;

    /**
     * 举报时间
     */
    @TableField("report_time")
    private Date reportTime;

    /**
     * 逻辑删除标识，0表示未删除，1表示已删除
     */
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;

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


}
