package com.example.lostandfound.mapper;

import com.example.lostandfound.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 评论表 Mapper 接口
 * </p>
 *
 * @author ilpvc
 * @since 2023-02-24 10:20:13
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
