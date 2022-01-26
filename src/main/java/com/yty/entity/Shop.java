package com.yty.entity;

import lombok.Data;

/**
 * @ClassName: Shop
 * @Description: 店铺实体类
 * @author: yty
 * @Date: 2022/1/24 16:53
 * @Version: 1.0
 */
@Data
public class Shop {
    private String id;
    private String name;
    private String head;
    private Integer userId;
    private String creatDate;
}
