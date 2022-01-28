package com.yty.service.impl;


import com.yty.dao.CommentMapper;
import com.yty.entity.Comment;
import com.yty.service.CommentService;
import com.yty.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: CommentImp
 * @Description: TODO
 * @author: yty
 * @Date: 2022/1/28 16:33
 * @Version: 1.0
 */

@Service
public class CommentImp implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public boolean sendComment(Comment comment) {
        if(comment == null){
            return false;
        }
        comment.setDate(DateUtil.ptfDate());
        return commentMapper.sendComment(comment);
    }

    @Override
    public List<Comment> getComments(String goodsId) {
        if (goodsId == null || goodsId.equals("")){
            return null;
        }
        return commentMapper.getComments(Integer.valueOf(goodsId));
    }
}
