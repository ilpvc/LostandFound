package com.example.lostandfound.entity.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Description:
 *
 * @date:2023/3/22 14:37
 * @author: ilpvc
 */

@Data
public class PostQuery {

    @ApiModelProperty("帖子类型（1：失物贴，2：招领贴，3：普通帖）")
    private Integer type;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("作者ID")
    private Integer userId;

    @ApiModelProperty("创建时间")
    private Date createdTime;

    @ApiModelProperty("更新时间")
    private Date updatedTime;

    @ApiModelProperty("状态（1：正常，2：待审核，3：已删除，4：已禁用，5：已完成）")
    private Collection<Integer> status;

    private String searchInfo;

    private ArrayList<Integer> types;

    /**
     * 按照帖子id集合查询帖子
     */
    private Collection<Integer> collection;

    /**
     * 按照用户集合匹配帖子
     */
    private Collection<Integer> collectionUserId;

    /**
     * 排名类型(1 comment,2 likes, 3,count)
     */
    private Integer rankType;

    private String tags;
}
