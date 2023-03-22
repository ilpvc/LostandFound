package com.example.lostandfound.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
@TableName("permissions")
@ApiModel(value = "Permissions对象", description = "")
public class Permissions implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("权限ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("权限名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("权限描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("创建时间")
    @TableField("created_time")
    private LocalDate createdTime;

    @ApiModelProperty("更新时间")
    @TableField("updated_time")
    private LocalDate updatedTime;

    @ApiModelProperty("是否逻辑删除")
    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;


}
