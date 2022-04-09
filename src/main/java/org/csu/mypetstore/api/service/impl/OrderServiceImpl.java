package org.csu.mypetstore.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.csu.mypetstore.api.common.CommonResponse;
import org.csu.mypetstore.api.entity.*;
import org.csu.mypetstore.api.persistence.LineItemMapper;
import org.csu.mypetstore.api.persistence.OrderInfoMapper;
import org.csu.mypetstore.api.persistence.OrderMapper;
import org.csu.mypetstore.api.persistence.SequenceMapper;
import org.csu.mypetstore.api.service.CartService;
import org.csu.mypetstore.api.service.OrderService;
import org.csu.mypetstore.api.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private SequenceMapper sequenceMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private LineItemMapper lineItemMapper;
    @Autowired
    private CartService cartService;

    @Override
    public int getNextId(String name) {
        Sequence sequence = sequenceMapper.selectById(name);
        sequence.setNextId(sequence.getNextId() + 1);
        sequenceMapper.updateById(sequence);
        return sequence.getNextId();
    }

    @Override
    public CommonResponse<Order> newOrder(Order order, CartVO cartVO) {
        orderMapper.insert(order);
        List<CartItem> cartItemList = cartVO.getItemList();
        int len = cartItemList.size();
        for(int i = 0;i < len;i++){
            OrderInfo orderInfo = getOrderInfo(order.getOrderId());
            LineItem lineItem = cartItemToLineItem(cartItemList.get(i), orderInfo);
            orderInfoMapper.insert(orderInfo);
            lineItemMapper.insert(lineItem);
        }
        //清空购物车
        cartService.clearCart(order.getUsername());
        return CommonResponse.createForSuccess(order);
    }

    @Override
    public CommonResponse<List<Order>> getOrders(String username) {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("username", username);
        List<Order> orderList = orderMapper.selectList(orderQueryWrapper);

        if(orderList.isEmpty())
            return CommonResponse.createForSuccessMessage("订单为空！");

        return CommonResponse.createForSuccess(orderList);
    }

    @Override
    public CommonResponse<Order> getOrderById(String orderId, String username) {
        Order order = orderMapper.selectById(orderId);
        if(order == null)
            return CommonResponse.createForError("您查询的订单不存在！");
        else if(order.getUsername().equals(username) || username.equals("admin")){
            return CommonResponse.createForSuccess(order);
        }
        else{
            return CommonResponse.createForError("您查询的订单不存在！");
        }
    }

    @Override
    public CommonResponse<List<LineItem>> getLineItemsByOrderId(String orderId, String username) {
        Order order = orderMapper.selectById(orderId);
        if(order == null)
            return CommonResponse.createForError("您查询的订单不存在！");
        else if(order.getUsername().equals(username) || username.equals("admin")){
            QueryWrapper<LineItem> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("orderId", orderId);
            List<LineItem> lineItemList = lineItemMapper.selectList(queryWrapper);
            return CommonResponse.createForSuccess(lineItemList);
        }
        else{
            return CommonResponse.createForError("您查询的订单不存在！");
        }
    }

    public OrderInfo getOrderInfo(int orderId){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(orderId);
        orderInfo.setLineNum(getNextId("linenum"));
        return orderInfo;
    }

    public LineItem cartItemToLineItem(CartItem cartItem, OrderInfo orderInfo){
        LineItem lineItem = new LineItem();

        lineItem.setOrderId(orderInfo.getOrderId());
        lineItem.setItemId(cartItem.getItemId());
        lineItem.setQuantity(cartItem.getQuantity());
        lineItem.setUnitPrice(cartItem.getTotalPrice());
        lineItem.setLineNumber(orderInfo.getLineNum());

        return lineItem;
    }
}
