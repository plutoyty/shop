package com.yty.controller;

import com.yty.Vo.BaseResult;
import com.yty.Vo.ProductListResult;
import com.yty.Vo.ProductResult;
import com.yty.entity.Product;
import com.yty.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 测试接口
     *
     * @return
     */
    @RequestMapping("/test")
    private String test() {
        return productService.getProductList("1").toString();
    }

    /**
     * 获取商品信息
     *
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
     *
     * @param id
     * @return
     */
    @RequestMapping("/category")
    private ProductListResult getProductList(@RequestParam("id") String id) {
        ProductListResult productListResult = new ProductListResult();
        productListResult.setProducts(productService.getProductList(id));
        productListResult.setStatus(100);
        return productListResult;
    }

    /**
     * 更新浏览时间
     *
     * @param email
     * @param goodsId
     * @return
     */
    @RequestMapping("/lookup")
    private BaseResult lookup(@RequestParam("email") String email,
                              @RequestParam("goodsId") String goodsId) {
        BaseResult baseResult = new BaseResult();
        boolean f = productService.lookup(email, goodsId);
        if (f == true) {
            baseResult.setStatus(100);
            baseResult.setMsg("success");
        } else {
            baseResult.setStatus(200);
            baseResult.setMsg("failed");
        }
        return baseResult;
    }

    /**
     * 获取商品浏览记录
     *
     * @param email
     * @return
     */
    @RequestMapping("/getLookup")
    private ProductListResult getLookup(@RequestParam("email") String email) {
        ProductListResult productListResult = new ProductListResult();
        productListResult.setProducts(productService.getLookup(email));
        productListResult.setStatus(100);
        return productListResult;
    }

    /**
     * 添加商品
     *
     * @param product
     * @return
     */
    @RequestMapping("/addGoods")
    private BaseResult addGoods(@RequestBody Product product) {
        BaseResult baseResult = new BaseResult();
        boolean f = productService.addGoods(product);
        if (f == true) {
            baseResult.setStatus(100);
            baseResult.setMsg("success");
        } else {
            baseResult.setStatus(200);
            baseResult.setMsg("failed");
        }
        return baseResult;
    }

    /**
     * 查询商品
     *
     * @return
     */
    @RequestMapping("getGoods")
    private ProductListResult getGoods(@RequestParam("name") String title,
                                       @RequestParam("category") String category,
                                       @RequestParam("pageIndex") String pageIndex,
                                       @RequestParam("pageSize") String pageSize) {
        title = title.trim();
        category = category.trim();
        ProductListResult p = new ProductListResult();
        p.setProducts(productService.getGoods(title, category, pageIndex, pageSize));
        p.setStatus(100);
        p.setMsg("success");
        return p;
    }

    /**
     * 删除商品
     *
     * @param id
     * @return
     */
    @RequestMapping("deleteGood")
    private BaseResult deleteGood(@RequestParam("id") String id) {
        BaseResult baseResult = new BaseResult();
        boolean f = productService.deleteGood(id);
        if (f == true) {
            baseResult.setStatus(100);
            baseResult.setMsg("success");
        } else {
            baseResult.setStatus(200);
            baseResult.setMsg("failed");
        }
        return baseResult;
    }

}
