package com.example.lostandfound.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
@TableName("user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID	")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty("密码")
    @TableField("password")
    private  String password;

    @ApiModelProperty("真实姓名")
    @TableField("real_name")
    private String realName;

    @ApiModelProperty("年龄")
    @TableField("age")
    private Integer age;

    @ApiModelProperty("所在班级")
    @TableField("clazz")
    private String clazz;

    @ApiModelProperty("电话号码")
    @TableField("phone_number")
    private String phoneNumber;

    @ApiModelProperty("性别 1、男 2、女")
    @TableField("gender")
    private Integer gender;

    @ApiModelProperty("邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("其他联系方式")
    @TableField("other_contacts")
    private String otherContacts;

    @ApiModelProperty("找回数量")
    @TableField("find_num")
    private Integer findNum;

    @ApiModelProperty("丢失数量")
    @TableField("lost_num")
    private Integer lostNum;

    @ApiModelProperty("0表示未删除，1表示删除")
    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty("状态 1、正常 2、封禁")
    @TableField("status")
    private Integer status;

    @ApiModelProperty("用户积分")
    @TableField("integral")
    private Integer integral;

    @ApiModelProperty("创建时间")
    @TableField("created_time")
    private Date createdTime;

    @ApiModelProperty("更新时间")
    @TableField("updated_time")
    private Date updatedTime;


}
