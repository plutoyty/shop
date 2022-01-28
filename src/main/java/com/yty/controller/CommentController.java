package com.yty.controller;

import com.yty.Vo.BaseResult;
import com.yty.Vo.comment.CommentListResult;
import com.yty.entity.Comment;
import com.yty.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: CommentController
 * @Description: TODO
 * @author: yty
 * @Date: 2022/1/27 23:25
 * @Version: 1.0
 */

@CrossOrigin
@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 发送评论
     * @param comment
     * @return
     */
    @RequestMapping("send")
    public BaseResult sendComment(@RequestBody Comment comment) {
        BaseResult baseResult = new BaseResult();
        boolean f = commentService.sendComment(comment);
        if (f == true) {
            baseResult.setStatus(100);
            baseResult.setMsg("success");
        } else {
            baseResult.setStatus(200);
            baseResult.setMsg("failed");
        }
        return baseResult;
    }

    /**
     * 获取所有评论
     * @param goodsId
     * @return
     */
    @RequestMapping("getComment")
    public CommentListResult getComments(@RequestParam("goodsId")String goodsId){
        CommentListResult commentListResult = new CommentListResult();
        commentListResult.setStatus(100);
        commentListResult.setComments(commentService.getComments(goodsId));
        return commentListResult;
    }
}
