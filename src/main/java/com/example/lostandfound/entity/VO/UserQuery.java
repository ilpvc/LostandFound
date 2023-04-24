package com.example.lostandfound.entity.VO;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collection;
import java.util.Date;

/**
 * Description:
 *
 * @date:2023/3/22 11:03
 * @author: ilpvc
 */

@Data
public class UserQuery {

    private String nickname;

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("所在班级")
    private String clazz;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("电话号码")
    private String phoneNumber;

    @ApiModelProperty("性别 1、男 2、女")
    private Integer gender;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("邮箱验证码")
    private String emailCode;

    @ApiModelProperty("找回数量")
    private Integer findNum;

    @ApiModelProperty("丢失数量")
    private Integer lostNum;

    @ApiModelProperty("状态 1、正常 2、封禁")
    private Integer status;

    /**
     * 按照用户集合匹配帖子
     */
    private Collection<Integer> userIds;

    @ApiModelProperty("用户积分")
    private Integer integral;

    @ApiModelProperty("创建时间")
    private Date createdTime;

    @ApiModelProperty("更新时间")
    private Date updatedTime;
}
