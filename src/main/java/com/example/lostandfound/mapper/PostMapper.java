package com.example.lostandfound.mapper;

import com.example.lostandfound.entity.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 帖子表 Mapper 接口
 * </p>
 *
 * @author ilpvc
 * @since 2023-03-22 09:28:33
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {

}
