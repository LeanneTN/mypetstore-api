package org.csu.mypetstore.api.controller.front;

import org.csu.mypetstore.api.common.CommonResponse;
import org.csu.mypetstore.api.common.ResponseCode;
import org.csu.mypetstore.api.entity.CartItem;
import org.csu.mypetstore.api.entity.LineItem;
import org.csu.mypetstore.api.entity.Order;
import org.csu.mypetstore.api.service.CartService;
import org.csu.mypetstore.api.service.OrderService;
import org.csu.mypetstore.api.vo.AccountVO;
import org.csu.mypetstore.api.vo.CartVO;
import org.csu.mypetstore.api.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.sound.sampled.Line;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/order/")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    //生成新的订单
    @PostMapping("orders/newOrder")
    @ResponseBody
    public CommonResponse<Order> newOrder(
            HttpSession session,
            @RequestParam("expiryDate")String expiryDate,
            @RequestParam("billToFirstName")String billToFirstName,
            @RequestParam("creditCard")String creditCard,
            @RequestParam("billToLastName")String billToLastName,
            @RequestParam("billAddress1")String billAddress1,
            @RequestParam("billAddress2")String billAddress2,
            @RequestParam("billCity")String billCity,
            @RequestParam("billState")String billState,
            @RequestParam("billZip")String billZip,
            @RequestParam("billCountry")String billCountry,
            @RequestParam("cardType")String cardType,
            @RequestParam("shippingAddressRequired")Boolean shippingAddressRequired,
            @RequestParam(value = "shipToFirstName", required = false)String shipToFirstName,
            @RequestParam(value = "shipToLastName", required = false)String shipToLastName,
            @RequestParam(value = "shipAddress1", required = false)String shipAddress1,
            @RequestParam(value = "shipAddress2", required = false)String shipAddress2,
            @RequestParam(value = "shipCity", required = false)String shipCity,
            @RequestParam(value = "shipState", required = false)String shipState,
            @RequestParam(value = "shipZip", required = false)String shipZip,
            @RequestParam(value = "shipCountry", required = false)String shipCountry){
        AccountVO accountVO = (AccountVO) session.getAttribute("login_account");
        //判断是否登录
        if(accountVO == null)
            return CommonResponse.createForError(ResponseCode.NEED_LOGIN.getCode(), "请先登录！");

        Order order = new Order();

        CommonResponse<CartVO> cartVOResponse = cartService.getCart(accountVO.getUsername());
        CartVO cartVO = cartVOResponse.getData();
        order.setOrderId(orderService.getNextId("ordernum"));
        order.setUsername(accountVO.getUsername());
        order.setOrderDate(new Date());
        order.setBillAddress1(billAddress1);
        order.setBillAddress2(billAddress2);
        order.setBillCity(billCity);
        order.setBillState(billState);
        order.setBillZip(billZip);
        order.setBillCountry(billCountry);
        order.setCourier("UPS");
        order.setTotalPrice(cartVO.getSubTotal());
        order.setBillToFirstName(billToFirstName);
        order.setBillToLastName(billToLastName);
        order.setCardType(cardType);
        order.setCreditCard(creditCard);
        order.setExpiryDate(expiryDate);
        order.setLocale("CSU");
        order.setStatus("待发货");
        order.setTimestamp(order.getOrderDate());

        if(shippingAddressRequired){
            order.setShipAddress1(shipAddress1);
            order.setShipAddress2(shipAddress2);
            order.setShipCity(shipCity);
            order.setShipState(shipState);
            order.setShipZip(shipZip);
            order.setShipCountry(shipCountry);
            order.setShipToFirstName(shipToFirstName);
            order.setShipToLastName(shipToLastName);
        }else{
            order.setShipAddress1(billAddress1);
            order.setShipAddress2(billAddress2);
            order.setShipCity(billCity);
            order.setShipState(billState);
            order.setShipZip(billZip);
            order.setShipCountry(billCountry);
            order.setShipToFirstName(billToFirstName);
            order.setShipToLastName(billToLastName);
        }

        CommonResponse<Order> response = orderService.newOrder(order, cartVO);

        return response;
    }

    @GetMapping("orders")
    @ResponseBody
    //获取已登录账号的订单
    public CommonResponse<List<Order>> getOrders(HttpSession session){
        AccountVO accountVO = (AccountVO) session.getAttribute("login_account");
        //判断是否登录
        if(accountVO == null)
            return CommonResponse.createForError(ResponseCode.NEED_LOGIN.getCode(), "请先登录！");

        return orderService.getOrders(accountVO.getUsername());
    }

    //根据订单号获取订单
    @GetMapping("orders/{orderId}")
    @ResponseBody
    public CommonResponse<Order> getOrderById(
            HttpSession session,
            @PathVariable("orderId") String orderId){
        AccountVO accountVO = (AccountVO)session.getAttribute("login_account");
        //判断是否登录
        if(accountVO == null)
            return CommonResponse.createForError(ResponseCode.NEED_LOGIN.getCode(), "请先登录！");
        return orderService.getOrderById(orderId, accountVO.getUsername());
    }

    //根据订单号获取订单购买的商品
    @GetMapping("orders/{orderId}/items")
    @ResponseBody
    public CommonResponse<List<LineItem>> getLineItemsByOrderId(
            HttpSession session,
            @PathVariable("orderId") int orderId){
        AccountVO accountVO = (AccountVO)session.getAttribute("login_account");
        //判断是否登录
        if(accountVO == null)
            return CommonResponse.createForError(ResponseCode.NEED_LOGIN.getCode(), "请先登录！");
        return orderService.getLineItemsByOrderId(orderId, accountVO.getUsername());
    }

    //获取OrderVO的list
    @GetMapping("orders/myOrders")
    @ResponseBody
    public CommonResponse<List<OrderVO>> getMyOrders(HttpSession session){
        AccountVO accountVO = (AccountVO)session.getAttribute("login_account");
        //判断是否登录
        if(accountVO == null)
            return CommonResponse.createForError(ResponseCode.NEED_LOGIN.getCode(), "请先登录！");
        return orderService.getMyOrders(accountVO.getUsername());
    }
}
