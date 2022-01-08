package com.yty.controller;

import com.yty.Vo.ProductResult;
import com.yty.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("getinfo")
    public ProductResult getProductInfo(@RequestParam("id") String  id) {
        ProductResult productResult = new ProductResult();
        productResult.setStatus(101);
        productResult.setData(productService.getProductInfo(id));
        return productResult;
    }
}
