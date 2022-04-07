package org.csu.mypetstore.api.controller.front;

import org.csu.mypetstore.api.common.CommonResponse;
import org.csu.mypetstore.api.entity.Category;
import org.csu.mypetstore.api.entity.Product;
import org.csu.mypetstore.api.service.CatalogService;
import org.csu.mypetstore.api.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/catalog/")
public class CategoryController {
    @Autowired
    private CatalogService catalogService;

    @GetMapping("categories")
    @ResponseBody
    public CommonResponse<List<Category>> getCategoryList(){
        return catalogService.getCategoryList();
    }

    @GetMapping("categories/{id}")
    @ResponseBody
    public CommonResponse<Category> getCategoryById(@PathVariable("id") String categoryId){
        return catalogService.getCategoryById(categoryId);
    }

    @GetMapping("categories/{id}/products")
    @ResponseBody
    public CommonResponse<List<Product>> getProductListById(@PathVariable("id") String categoryId){
        return catalogService.getProductListById(categoryId);
    }

    @GetMapping("products/{id}")
    @ResponseBody
    public CommonResponse<Product> getProductById(@PathVariable("id") String productId){
        return catalogService.getProductById(productId);
    }

    @GetMapping("products/{id}/items")
    @ResponseBody
    public CommonResponse<List<ItemVO>> getItemListById(@PathVariable("id") String productId){
        return catalogService.getItemListById(productId);
    }

    @GetMapping("items/{id}")
    @ResponseBody
    public CommonResponse<ItemVO> getItemById(@PathVariable("id") String itemId){
        return catalogService.getItemById(itemId);
    }

    @GetMapping("products/search")
    @ResponseBody
    public CommonResponse<List<Product>> searchProductsByKeywords(@RequestParam("keywords") String keywords){
        return catalogService.searchProductsByKeywords(keywords);
    }
}
