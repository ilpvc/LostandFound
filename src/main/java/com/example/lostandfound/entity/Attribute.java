package com.example.lostandfound.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2023-03-22 09:28:32
 */
@Getter
@Setter
@TableName("attribute")
@ApiModel(value = "Attribute对象", description = "")
public class Attribute implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("ID")
    @TableField("id")
    private int id;

    @ApiModelProperty("键")
    @TableField("attr_key")
    private String attrKey;

    @ApiModelProperty("数字值")
    @TableField("number_value")
    private Integer numberValue;

    @ApiModelProperty("文本值")
    @TableField("text_value")
    private String textValue;


}
