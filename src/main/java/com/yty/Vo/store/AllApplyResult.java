package com.yty.Vo.store;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: AllApplyResult
 * @Description:
 * @author: yty
 * @Date: 2022/1/24 22:35
 * @Version: 1.0
 */
@Data
public class AllApplyResult {
    private Integer status;
    private String msg;
    private List<ShopApply> list;
}
