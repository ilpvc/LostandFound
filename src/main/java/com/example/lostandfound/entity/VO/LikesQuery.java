package com.example.lostandfound.entity.VO;

import lombok.Data;

import java.util.ArrayList;

/**
 * Description:
 *
 * @date:2023/4/10 16:14
 * @author: ilpvc
 */

@Data
public class LikesQuery {

    private Integer userId;

    private Integer postId;

    private ArrayList<Integer> postIds;
}
