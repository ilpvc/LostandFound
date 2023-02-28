package com.example.lostandfound.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 消息表
 * </p>
 *
 * @author ilpvc
 * @since 2023-02-24 10:20:13
 */
@Getter
@Setter
@TableName("message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息ID
     */
    @TableId("id")
    private Integer id;

    /**
     * 发送消息的用户ID
     */
    @TableField("from_user_id")
    private Integer fromUserId;

    /**
     * 接收消息的用户ID
     */
    @TableField("to_user_id")
    private Integer toUserId;

    /**
     * 消息关联的物品ID
     */
    @TableField("item_id")
    private Integer itemId;

    /**
     * 消息类型：1-评论，2-回复，3-系统消息
     */
    @TableField("type")
    private Integer type;

    /**
     * 消息内容
     */
    @TableField("content")
    private String content;

    /**
     * 消息状态：0-未读，1-已读
     */
    @TableField("status")
    private Integer status;

    /**
     * 是否删除
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
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 删除时间
     */
    @TableField("delete_time")
    private Date deleteTime;


}
