package com.yty.Vo;

import com.yty.entity.Address;
import lombok.Data;
import java.util.List;

/**
 * 100 成功
 * 200 失败
 */
@Data
public class AllAddressResult {
    private Integer status;
    private String msg;
    private List<Address> data;
}
