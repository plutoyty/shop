package com.yty.Vo;

import com.yty.entity.Product;
import lombok.Data;


/**
 * 101 返回成功
 * 102 未找到商品
 */
@Data
public class ProductResult {
    private Integer status;
    private Product data;
}
