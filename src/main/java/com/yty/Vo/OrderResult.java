package com.yty.Vo;

import com.yty.entity.Order;
import lombok.Data;

/**
 * 100 成功
 * 200 失败
 */
@Data
public class OrderResult {
    private Integer status;
    private Order data;
}
