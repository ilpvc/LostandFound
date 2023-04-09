package com.example.lostandfound.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

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
@TableName("tasks")
@ApiModel(value = "Tasks对象", description = "")
public class Tasks implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "task_id", type = IdType.AUTO)
    private Integer taskId;

    @TableField("task_name")
    private String taskName;

    @ApiModelProperty("任务完成可获得积分")
    @TableField("points")
    private Integer points;

    @ApiModelProperty("任务详情描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("任务创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("任务更新时间")
    @TableField("update_time")
    private Date updateTime;


}
