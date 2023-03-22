package com.example.lostandfound.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
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
@TableName("task_users")
@ApiModel(value = "TaskUsers对象", description = "")
public class TaskUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "task_user_id", type = IdType.AUTO)
    private Integer taskUserId;

    @ApiModelProperty("任务ID")
    @TableField("task_id")
    private Integer taskId;

    @ApiModelProperty("用户ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("任务用户关系创建时间")
    @TableField("create_time")
    private LocalDate createTime;

    @ApiModelProperty("任务用户关系更新时间")
    @TableField("update_time")
    private LocalDate updateTime;


}
