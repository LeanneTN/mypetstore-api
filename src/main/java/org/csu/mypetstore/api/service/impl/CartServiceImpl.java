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

import java.math.BigDecimal;
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
            return CommonResponse.createForSuccess(new CartVO());

        CartVO cartVO = new CartVO();
        cartVO.setItemList(cartItemList);
        return CommonResponse.createForSuccess(cartVO);
    }

    @Override
    public CommonResponse<CartVO> changeChecked(String username, String itemId, boolean checked) {
        QueryWrapper<CartItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("itemId", itemId);
        queryWrapper.eq("buyerName", username);
        CartItem cartItem = cartItemMapper.selectOne(queryWrapper);

        if(cartItem != null){
            cartItem.setChecked(checked);
            cartItemMapper.update(cartItem, queryWrapper);
            return CommonResponse.createForSuccess(getCart(username).getData());
        }
        else{
            return CommonResponse.createForError("失败！");
        }
    }

    @Override
    public CommonResponse<CartVO> checkAll(String username, boolean checked) {
        QueryWrapper<CartItem> listQueryWrapper = new QueryWrapper<>();
        listQueryWrapper.eq("buyerName", username);
        List<CartItem> cartItemList = cartItemMapper.selectList(listQueryWrapper);
        int len = cartItemList.size();

        for(int i = 0;i < len;i++){
            CartItem cartItem = cartItemList.get(i);
            QueryWrapper<CartItem> itemQueryWrapper = new QueryWrapper<>();
            itemQueryWrapper.eq("buyerName", username);
            itemQueryWrapper.eq("itemId", cartItem.getItemId());
            cartItem.setChecked(checked);
            cartItemMapper.update(cartItem, itemQueryWrapper);
        }
        return CommonResponse.createForSuccess(getCart(username).getData());
    }

    @Override
    public CommonResponse<CartVO> removeChecked(String username) {
        QueryWrapper<CartItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("buyerName", username);
        queryWrapper.eq("checked", 1);
        int result = cartItemMapper.delete(queryWrapper);
        if(result > 0){
            return CommonResponse.createForSuccess(getCart(username).getData());
        }else {
            return CommonResponse.createForError("出错！");
        }
    }

    @Override
    public CommonResponse<List<CartItem>> checkout(String username) {
        QueryWrapper<CartItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("buyerName", username);
        queryWrapper.eq("checked", 1);
        List<CartItem> cartItemList = cartItemMapper.selectList(queryWrapper);
        return CommonResponse.createForSuccess(cartItemList);
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
            cartItem.setTotalPrice(cartItem.getTotalPrice().add(cartItem.getListPrice()));
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
        cartItem.setTotalPrice(cartItem.getListPrice().multiply(new BigDecimal(quantity)));
        cartItemMapper.update(cartItem, queryWrapper);
        return getCart(username);
    }

    @Override
    public void clearCartChecked(String username) {
        QueryWrapper<CartItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("buyerName", username);
        queryWrapper.eq("checked", 1);
        cartItemMapper.delete(queryWrapper);
    }

    public CartItem itemVOToCartItem(ItemVO itemVO, String buyerName){
        CartItem cartItem = new CartItem();

        cartItem.setChecked(true);
        cartItem.setItemId(itemVO.getItemId());
        cartItem.setProductId(itemVO.getProductId());
        cartItem.setImage(itemVO.getImage());
        cartItem.setAttr1(itemVO.getAttribute1());
        cartItem.setName(itemVO.getName());
        cartItem.setDescn(itemVO.getDescription());
        cartItem.setInStock(itemVO.getQuantity()>0 ? true : false);
        cartItem.setQuantity(1);
        cartItem.setListPrice(itemVO.getListPrice());
        cartItem.setTotalPrice(itemVO.getListPrice());
        cartItem.setBuyerName(buyerName);

        return cartItem;
    }
}
