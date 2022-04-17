package org.csu.mypetstore.api.controller.front;

import org.csu.mypetstore.api.common.CommonResponse;
import org.csu.mypetstore.api.common.ResponseCode;
import org.csu.mypetstore.api.entity.CartItem;
import org.csu.mypetstore.api.service.CartService;
import org.csu.mypetstore.api.vo.AccountVO;
import org.csu.mypetstore.api.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cart/")
public class CartController {
    @Autowired
    private CartService cartService;

    //获取 已登录账号 的购物车
    @GetMapping("myCart")
    @ResponseBody
    public CommonResponse<CartVO> login(HttpSession session){
        AccountVO accountVO = (AccountVO) session.getAttribute("login_account");
        //判断是否登录
        if(accountVO == null)
            return CommonResponse.createForError(ResponseCode.NEED_LOGIN.getCode(), "请先登录！");

        CommonResponse<CartVO> response = cartService.getCart(accountVO.getUsername());
        return response;
    }

    // 改变cartItem的checked状态
    @PostMapping("myCart/changeChecked")
    @ResponseBody
    public CommonResponse<CartVO> changeChecked(
            HttpSession session,
            @RequestParam("itemId") String itemId,
            @RequestParam("checked") boolean checked){
        AccountVO accountVO = (AccountVO) session.getAttribute("login_account");
        //判断是否登录
        if(accountVO == null)
            return CommonResponse.createForError(ResponseCode.NEED_LOGIN.getCode(), "请先登录！");

        CommonResponse<CartVO> response = cartService.changeChecked(accountVO.getUsername(),itemId,checked);
        return response;
    }

    //全选或全不选
    @PostMapping("myCart/checkAll")
    @ResponseBody
    public CommonResponse<CartVO> checkAll(
            HttpSession session,
            @RequestParam("checked") boolean checked){
        AccountVO accountVO = (AccountVO) session.getAttribute("login_account");
        //判断是否登录
        if(accountVO == null)
            return CommonResponse.createForError(ResponseCode.NEED_LOGIN.getCode(), "请先登录！");

        CommonResponse<CartVO> response = cartService.checkAll(accountVO.getUsername(),checked);
        return response;
    }

    //删除选中的商品
    @PostMapping("myCart/removeChecked")
    @ResponseBody
    public CommonResponse<CartVO> removeChecked(HttpSession session){
        AccountVO accountVO = (AccountVO) session.getAttribute("login_account");
        //判断是否登录
        if(accountVO == null)
            return CommonResponse.createForError(ResponseCode.NEED_LOGIN.getCode(), "请先登录！");

        CommonResponse<CartVO> response = cartService.removeChecked(accountVO.getUsername());
        return response;
    }

    //结算，返回被选中的商品
    @PostMapping("myCart/checkout")
    @ResponseBody
    public CommonResponse<List<CartItem>> checkout(HttpSession session){
        AccountVO accountVO = (AccountVO) session.getAttribute("login_account");
        //判断是否登录
        if(accountVO == null)
            return CommonResponse.createForError(ResponseCode.NEED_LOGIN.getCode(), "请先登录！");

        CommonResponse<List<CartItem>> response = cartService.checkout(accountVO.getUsername());
        return response;
    }

    //向 已登录账号 的购物车中添加商品
    @PostMapping("myCart")
    @ResponseBody
    public CommonResponse<CartVO> addCartItem(
            HttpSession session,
            @RequestParam("itemId") String itemId){
        AccountVO accountVO = (AccountVO) session.getAttribute("login_account");
        //判断是否登录
        if(accountVO == null)
            return CommonResponse.createForError(ResponseCode.NEED_LOGIN.getCode(), "请先登录！");

        CommonResponse<CartVO> response = cartService.addCartItem(accountVO.getUsername(),itemId);
        return response;
    }

    //移除 已登录账号 购物车中的商品
    @DeleteMapping("myCart/cartItems")
    @ResponseBody
    public CommonResponse<CartVO> removeCartItem(
            HttpSession session,
            @RequestParam("itemId") String itemId){
        AccountVO accountVO = (AccountVO) session.getAttribute("login_account");
        //判断是否登录
        if(accountVO == null)
            return CommonResponse.createForError(ResponseCode.NEED_LOGIN.getCode(), "请先登录！");

        CommonResponse<CartVO> response = cartService.removeCartItem(accountVO.getUsername(),itemId);
        return response;
    }

    //修改 已登录账号 购物车中商品的数量
    @PostMapping("myCart/cartItems")
    @ResponseBody
    public CommonResponse<CartVO> updateQuantity(
            HttpSession session,
            @RequestParam("itemId") String itemId,
            @RequestParam("quantity") int quantity){
        AccountVO accountVO = (AccountVO) session.getAttribute("login_account");
        //判断是否登录
        if(accountVO == null)
            return CommonResponse.createForError(ResponseCode.NEED_LOGIN.getCode(), "请先登录！");

        CommonResponse<CartVO> response = cartService.updateQuantity(accountVO.getUsername(),itemId,quantity);
        return response;
    }
}
