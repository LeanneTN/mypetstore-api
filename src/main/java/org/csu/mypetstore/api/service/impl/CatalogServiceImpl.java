package org.csu.mypetstore.api.service.impl;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.csu.mypetstore.api.common.CommonResponse;
import org.csu.mypetstore.api.entity.Category;
import org.csu.mypetstore.api.entity.Item;
import org.csu.mypetstore.api.entity.ItemInventory;
import org.csu.mypetstore.api.entity.Product;
import org.csu.mypetstore.api.persistence.CategoryMapper;
import org.csu.mypetstore.api.persistence.ItemInventoryMapper;
import org.csu.mypetstore.api.persistence.ItemMapper;
import org.csu.mypetstore.api.persistence.ProductMapper;
import org.csu.mypetstore.api.service.CatalogService;
import org.csu.mypetstore.api.vo.CategoryVO;
import org.csu.mypetstore.api.vo.ItemVO;
import org.csu.mypetstore.api.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service("catalogService")
public class CatalogServiceImpl implements CatalogService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ItemInventoryMapper itemInventoryMapper;

    @Override
    public CommonResponse<List<CategoryVO>> getAllCategory() {
        List<CategoryVO> categoryVOList = new ArrayList<>();
        CommonResponse<List<Category>> response = getCategoryList();
        int len1 = response.getData().size();
        for(int i = 0;i < len1;i++){
            CategoryVO categoryVO = categoryToCategoryVO(response.getData().get(i));
            categoryVOList.add(categoryVO);
        }

        return CommonResponse.createForSuccess(categoryVOList);
    }

    @Override
    public CommonResponse<List<Category>> getCategoryList() {
        List<Category> categoryList = categoryMapper.selectList(null);
        if(categoryList.isEmpty())
            return CommonResponse.createForSuccessMessage("??????????????????");
        return CommonResponse.createForSuccess(categoryList);
    }

    @Override
    public CommonResponse<Category> getCategoryById(String categoryId) {
        Category category = categoryMapper.selectById(categoryId);
        if(category == null)
            return CommonResponse.createForSuccessMessage("?????????ID???Category");
        return CommonResponse.createForSuccess(category);
    }

    @Override
    public CommonResponse<List<Product>> getProductListById(String categoryId) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category", categoryId);
        List<Product> productList = productMapper.selectList(queryWrapper);
        if(productList.isEmpty())
            return CommonResponse.createForSuccessMessage("???Category?????????Product");
        return CommonResponse.createForSuccess(productList);
    }

    @Override
    public CommonResponse<Product> getProductById(String productId) {
        Product product = productMapper.selectById(productId);
        if(product == null)
            return CommonResponse.createForSuccessMessage("?????????ID???Product");
        return CommonResponse.createForSuccess(product);
    }

    @Override
    public CommonResponse<List<ItemVO>> getItemListById(String productId) {
        Product product = productMapper.selectById(productId);
        if(product == null)
            return CommonResponse.createForSuccessMessage("?????????ID???Product");

        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("productId", productId);
        List<Item> itemList = itemMapper.selectList(queryWrapper);
        if(itemList.isEmpty())
            return CommonResponse.createForSuccessMessage("???Product?????????Item");

        List<ItemVO> itemVOList = new ArrayList<>();
        for(Item item : itemList){
            ItemVO itemVO = itemToItemVO(item,product);
            itemVOList.add(itemVO);
        }

        return CommonResponse.createForSuccess(itemVOList);
    }

    @Override
    public CommonResponse<ItemVO> getItemById(String itemId) {
        Item item = itemMapper.selectById(itemId);
        if(item == null)
            return CommonResponse.createForSuccessMessage("?????????ID???Item");
        Product product = productMapper.selectById(item.getProductId());

        ItemVO itemVO = itemToItemVO(item,product);
        return CommonResponse.createForSuccess(itemVO);
    }

    @Override
    public CommonResponse<List<Product>> searchProductsByKeywords(String keywords) {
        //????????????
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",keywords);
        List<Product> productList = productMapper.selectList(queryWrapper);
        if(productList.isEmpty())
            return CommonResponse.createForSuccessMessage("???????????????????????????");
        return CommonResponse.createForSuccess(productList);
    }

    private ItemVO itemToItemVO(Item item,Product product){
        ItemVO itemVO = new ItemVO();
        //??????Item???????????????
        itemVO.setItemId(item.getItemId());
        itemVO.setProductId(item.getProductId());
        itemVO.setListPrice(item.getListPrice());
        itemVO.setUnitCost(item.getUnitCost());
        itemVO.setSupplierId(item.getSupplierId());
        itemVO.setStatus(item.getStatus());
        itemVO.setAttribute1(item.getAttribute1());
        itemVO.setAttribute2(item.getAttribute2());
        itemVO.setAttribute3(item.getAttribute3());
        itemVO.setAttribute4(item.getAttribute4());
        itemVO.setAttribute5(item.getAttribute5());

        //??????Product???????????????
        itemVO.setCategoryId(product.getCategoryId());
        itemVO.setName(product.getName());
        itemVO.setDescription(product.getDescription());
        itemVO.setImage(product.getImage());

        //??????ItemInventory?????????
        itemVO.setQuantity(itemInventoryMapper.selectById(item.getItemId()).getQuantity());

        return itemVO;
    }

    private CategoryVO categoryToCategoryVO(Category category){
        CategoryVO categoryVO = new CategoryVO();
        categoryVO.setCategoryId(category.getCategoryId());
        categoryVO.setName(category.getName());
        categoryVO.setDescription(category.getDescription());

        CommonResponse<List<Product>> response = getProductListById(category.getCategoryId());
        List<ProductVO> productVOList = new ArrayList<>();
        int len = response.getData().size();
        for(int i = 0;i < len;i++){
            productVOList.add(productToProductVO(response.getData().get(i)));
        }

        categoryVO.setProductVOList(productVOList);

        return categoryVO;
    }

    private ProductVO productToProductVO(Product product){
        ProductVO productVO = new ProductVO();
        productVO.setProductId(product.getProductId());
        productVO.setDescription(product.getDescription());
        productVO.setImage(product.getImage());
        productVO.setName(product.getName());
        productVO.setCategoryId(product.getCategoryId());
        productVO.setItemVOList(getItemListById(product.getProductId()).getData());

        return productVO;
    }


}
