package org.csu.mypetstore.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.csu.mypetstore.api.common.CommonResponse;
import org.csu.mypetstore.api.common.ResponseCode;
import org.csu.mypetstore.api.entity.CartItem;
import org.csu.mypetstore.api.entity.Item;
import org.csu.mypetstore.api.entity.Product;
import org.csu.mypetstore.api.persistence.CartItemMapper;
import org.csu.mypetstore.api.persistence.ItemMapper;
import org.csu.mypetstore.api.persistence.ProductMapper;
import org.csu.mypetstore.api.service.CartService;
import org.csu.mypetstore.api.service.CatalogService;
import org.csu.mypetstore.api.vo.CartVO;
import org.csu.mypetstore.api.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cartService")
public class CartServiceImpl implements CartService {
    @Autowired
    private CartItemMapper cartItemMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CatalogService catalogService;

    //获取已登录账号的购物车
    @Override
    public CommonResponse<CartVO> getCart(String username) {
        QueryWrapper<CartItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("buyername", username);
        List<CartItem> cartItemList = cartItemMapper.selectList(queryWrapper);
        if(cartItemList.isEmpty())
            return CommonResponse.createForSuccessMessage("购物车为空");

        CartVO cartVO = new CartVO();
        cartVO.setItemList(cartItemList);
        return CommonResponse.createForSuccess(cartVO);
    }

    //向购物车添加商品
    @Override
    public CommonResponse<CartVO> addCartItem(String username, String itemId) {
        QueryWrapper<CartItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("itemId", itemId);
        queryWrapper.eq("buyerName", username);
        CartItem cartItem = cartItemMapper.selectOne(queryWrapper);

        //用户购物车中没有该商品
        if(cartItem == null){
            CommonResponse<ItemVO> response = catalogService.getItemById(itemId);
            ItemVO itemVO = response.getData();
            cartItem = itemVOToCartItem(itemVO, username);
            cartItemMapper.insert(cartItem);
        }
        //用户购物车中已有该商品
        else{
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItemMapper.update(cartItem, queryWrapper);
        }

        return getCart(username);
    }

    @Override
    public CommonResponse<CartVO> removeCartItem(String username, String itemId) {
        QueryWrapper<CartItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("itemId", itemId);
        queryWrapper.eq("buyerName", username);

        CartItem cartItem = cartItemMapper.selectOne(queryWrapper);
        if(cartItem == null)
            return CommonResponse.createForError("购物车中没有该商品!");
        cartItemMapper.delete(queryWrapper);
        return getCart(username);
    }

    @Override
    public CommonResponse<CartVO> updateQuantity(String username, String itemId, int quantity) {
        QueryWrapper<CartItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("itemId", itemId);
        queryWrapper.eq("buyerName", username);

        CartItem cartItem = cartItemMapper.selectOne(queryWrapper);
        if(cartItem == null)
            return CommonResponse.createForError("购物车中没有该商品!");

        if(quantity == 0)
            return removeCartItem(username, itemId);

        cartItem.setQuantity(quantity);
        cartItemMapper.update(cartItem, queryWrapper);
        return getCart(username);
    }

    @Override
    public void clearCart(String username) {
        QueryWrapper<CartItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("buyerName", username);
        cartItemMapper.delete(queryWrapper);
    }

    public CartItem itemVOToCartItem(ItemVO itemVO, String buyerName){
        CartItem cartItem = new CartItem();

        cartItem.setItemId(itemVO.getItemId());
        cartItem.setProductId(itemVO.getProductId());
        cartItem.setDescn(itemVO.getDescription());
        cartItem.setInStock(itemVO.getQuantity()>0 ? true : false);
        cartItem.setQuantity(1);
        cartItem.setListPrice(itemVO.getListPrice());
        cartItem.setTotalPrice(itemVO.getListPrice());
        cartItem.setBuyerName(buyerName);

        return cartItem;
    }
}
