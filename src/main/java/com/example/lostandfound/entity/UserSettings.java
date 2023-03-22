package com.example.lostandfound.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author ilpvc
 * @since 2023-03-22 09:28:33
 */
@Getter
@Setter
@TableName("user_settings")
@ApiModel(value = "UserSettings对象", description = "")
public class UserSettings implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    @TableId("user_id")
    private Integer userId;

    @ApiModelProperty("是否接收系统消息推送")
    @TableField("push_notification")
    private Boolean pushNotification;

    @ApiModelProperty("是否接收别人关注我提醒")
    @TableField("follow_me")
    private Boolean followMe;

    @ApiModelProperty("是否接收举报消息提醒")
    @TableField("report_notification")
    private Boolean reportNotification;

    @ApiModelProperty("是否接收我关注别人提醒")
    @TableField("follow_others")
    private Boolean followOthers;

    @ApiModelProperty("是否接收帖子回复提醒")
    @TableField("reply_notification")
    private Boolean replyNotification;

    @ApiModelProperty("是否接收帖子被收藏提醒")
    @TableField("bookmark_notification")
    private Boolean bookmarkNotification;


}
