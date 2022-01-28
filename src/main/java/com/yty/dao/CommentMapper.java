package com.yty.dao;

import com.yty.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    /**
     * 发送评论
     * @param comment
     * @return
     */
    boolean sendComment(Comment comment);

    /**
     * 获取商品的评论
     * @param goodsId
     * @return
     */
    List<Comment> getComments(Integer goodsId);
}
