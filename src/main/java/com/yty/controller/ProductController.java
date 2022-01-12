package com.yty.controller;

import com.yty.Vo.ProductListResult;
import com.yty.Vo.ProductResult;
import com.yty.entity.Product;
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

    /**
     *测试接口
     * @return
     */
    @RequestMapping("/test")
    private String test(){
        return productService.getProductList("1").toString();
    }

    /**
     * 获取商品信息
     * @param id
     * @return
     */
    @RequestMapping("/getinfo")
    public ProductResult getProductInfo(
            @RequestParam("id") String id) {
        ProductResult productResult = new ProductResult();
        Product product = productService.getProductInfo(id);
        if (product != null) {
            productResult.setStatus(101);
            productResult.setData(product);
        } else {
            productResult.setStatus(102);
            productResult.setData(product);
        }
        return productResult;
    }

    /**
     * 根据种类id获取商品
     * @param id
     * @return
     */
    @RequestMapping("/category")
    private ProductListResult getProductList(@RequestParam("id") String id){
        ProductListResult productListResult = new ProductListResult();
        productListResult.setProducts(productService.getProductList(id));
        productListResult.setStatus(100);
        return productListResult;
    }
}
