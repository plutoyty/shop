package com.yty.controller;

import com.yty.Vo.ProductListResult;
import com.yty.service.ProductService;
import com.yty.service.impl.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @ClassName: Test
 * @Description: TODO
 * @author: yty
 * @Date: 2022/1/27 16:07
 * @Version: 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/test")
public class TestResourceController  {

    @Autowired
    private ElasticSearchService elasticSearchService;

    @Autowired
    private ProductService productService;

//    private static final Logger log = LoggerFactory.getLogger(TestResource.class);
    //条件 查询
    @PostMapping("/test")
    public String query( ) {
        String queryResult =null;
        try {
            queryResult = elasticSearchService.search();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return queryResult;
    }

    @RequestMapping("test1")
    private ProductListResult query(@RequestParam("name")String name){
        ProductListResult productListResult = new ProductListResult();
        productListResult.setStatus(100);
        productListResult.setProducts(productService.query(name));
        return productListResult;
    }
}
