package com.example.lostandfound.entity.VO;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Description:
 *
 * @date:2023/3/22 15:49
 * @author: ilpvc
 */

@Data
public class AttributeQuery {

    @ApiModelProperty("键")
    private String attrKey;
}
