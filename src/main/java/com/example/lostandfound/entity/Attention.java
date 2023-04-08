package com.example.lostandfound.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Description:
 *
 * @date:2023/4/8 10:21
 * @author: ilpvc
 */

@Getter
@Setter
@TableName("attention")
@ApiModel(value = "attention对象", description = "关注表")
public class Attention implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("ID")
    @TableField("id")
    private int id;

    @ApiModelProperty("关注用户Id")
    @TableField("attention_user_id")
    private Integer attentionUserId;

    @ApiModelProperty("被关注用户Id")
    @TableField("attentioned_user_id")
    private Integer attentionedUserId;
}
