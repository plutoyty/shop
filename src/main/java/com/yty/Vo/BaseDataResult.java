package com.yty.Vo;

import lombok.Data;

/**
 * 100 成功
 * 200 失败
 *
 */
@Data
public class BaseDataResult<T> {
    private Integer status;
    private T data;
}
