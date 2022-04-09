package org.csu.mypetstore.api.service;

import org.csu.mypetstore.api.common.CommonResponse;
import org.csu.mypetstore.api.vo.CartVO;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

public interface CartService {
    public CommonResponse<CartVO> getCart(String username);

    public CommonResponse<CartVO> addCartItem(String username, String itemId);

    public CommonResponse<CartVO> removeCartItem(String username, String itemId);

    public CommonResponse<CartVO> updateQuantity(String username, String itemId, int quantity);

    public void clearCart(String username);
}
