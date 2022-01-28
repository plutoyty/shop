package com.yty.entity;

import lombok.Data;

/**
 * @ClassName: Comment
 * @Description: TODO
 * @author: yty
 * @Date: 2022/1/28 16:39
 * @Version: 1.0
 */

@Data
public class Comment {
    private Integer id;
    private Integer userId;
    private Integer goodsId;
    private String date;
    private String text;
    private Integer reply;
}
