package com.example.lostandfound.entity.VO;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * Description:
 *
 * @date:2023/4/9 20:49
 * @author: ilpvc
 */
@Data
public class ReportQuery {

    private Integer userId;

    private Integer postId;

    private String content;

    private Integer status;
}
