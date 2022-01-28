package com.yty.Vo.comment;

import com.yty.entity.Comment;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: CommentList
 * @Description: TODO
 * @author: yty
 * @Date: 2022/1/28 17:33
 * @Version: 1.0
 */
@Data
public class CommentListResult {
    private Integer status;
    private String msg;
    private List<Comment> comments;
}
