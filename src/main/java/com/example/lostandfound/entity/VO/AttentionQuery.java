package com.example.lostandfound.entity.VO;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description:
 *
 * @date:2023/4/8 10:40
 * @author: ilpvc
 */
@Data
public class AttentionQuery {
    @ApiModelProperty("关注用户Id")
    @TableField("attention_user_id")
    private Integer attentionUserId;

    @ApiModelProperty("被关注用户Id")
    @TableField("attentioned_user_id")
    private Integer attentionedUserId;
}
