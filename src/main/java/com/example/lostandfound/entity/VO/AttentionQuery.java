package com.example.lostandfound.entity.VO;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * Description:
 *
 * @date:2023/4/8 10:40
 * @author: ilpvc
 */
@Data
public class AttentionQuery {

    private Integer attentionUserId;

    private Integer attentionedUserId;

    private Integer status;

}