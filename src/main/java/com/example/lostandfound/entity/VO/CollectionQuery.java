package com.example.lostandfound.entity.VO;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description:
 *
 * @date:2023/4/8 10:01
 * @author: ilpvc
 */

@Data
public class CollectionQuery {
    @ApiModelProperty("用户Id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("帖子Id")
    @TableField("post_id")
    private Integer postId;
}
