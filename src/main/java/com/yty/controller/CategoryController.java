package com.yty.controller;

import com.alipay.easysdk.factory.Factory;
import com.yty.Vo.BaseResult;
import com.yty.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: Category
 * @Description: TODO
 * @author: yty
 * @Date: 2022/1/27 21:54
 * @Version: 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("add")
    public BaseResult addCategory(){
        BaseResult baseResult = new BaseResult();

        return baseResult;
    }



}
