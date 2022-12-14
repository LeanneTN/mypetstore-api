package org.csu.mypetstore.api.service;

import org.csu.mypetstore.api.common.CommonResponse;
import org.csu.mypetstore.api.entity.Category;
import org.csu.mypetstore.api.entity.Product;
import org.csu.mypetstore.api.vo.CategoryVO;
import org.csu.mypetstore.api.vo.ItemVO;

import java.util.List;

public interface CatalogService {
    public CommonResponse<List<CategoryVO>> getAllCategory();

    public CommonResponse<List<Category>> getCategoryList();

    public CommonResponse<Category> getCategoryById(String categoryId);

    public CommonResponse<List<Product>> getProductListById(String categoryId);

    public CommonResponse<Product> getProductById(String productId);

    public CommonResponse<List<ItemVO>> getItemListById(String productId);

    public CommonResponse<ItemVO> getItemById(String itemId);

    public CommonResponse<List<Product>> searchProductsByKeywords(String keywords);
}
