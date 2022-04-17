package org.csu.mypetstore.api.service;

import org.csu.mypetstore.api.common.CommonResponse;
import org.csu.mypetstore.api.entity.CartItem;
import org.csu.mypetstore.api.vo.CartVO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface CartService {
    public CommonResponse<CartVO> getCart(String username);

    public CommonResponse<CartVO> changeChecked(String username, String itemId, boolean checked);

    public CommonResponse<CartVO> checkAll(String username, boolean checked);

    public CommonResponse<CartVO> removeChecked(String username);

    public CommonResponse<List<CartItem>> checkout(String username);

    public CommonResponse<CartVO> addCartItem(String username, String itemId);

    public CommonResponse<CartVO> removeCartItem(String username, String itemId);

    public CommonResponse<CartVO> updateQuantity(String username, String itemId, int quantity);

    public void clearCart(String username);
}
