package com.yty.Vo;

import com.yty.entity.Address;
import lombok.Data;

/**
 * 100 成功获取
 * 200 失败
 */
@Data
public class AddressResult {
    private Integer status;
    private Address data;
}
