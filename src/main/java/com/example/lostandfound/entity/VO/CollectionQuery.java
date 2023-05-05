package com.example.lostandfound.entity.VO;
import lombok.Data;

import java.util.ArrayList;

/**
 * Description:
 *
 * @date:2023/4/8 10:01
 * @author: ilpvc
 */

@Data
public class CollectionQuery {

    private Integer userId;

    private Integer postId;

    private ArrayList<Integer> postIds;

    private Integer status;
}