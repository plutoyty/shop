package com.yty.service;

import com.yty.entity.Comment;

import java.util.List;

public interface CommentService {

    /**
     * 发送评论
     * @param comment
     * @return
     */
    boolean sendComment(Comment comment);

    /**
     * 获取该商品的评论
     * @param goodsId
     * @return
     */
    List<Comment> getComments(String goodsId);
}
