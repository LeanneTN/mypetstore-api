package org.csu.mypetstore.api.service;

import org.csu.mypetstore.api.common.CommonResponse;
import org.csu.mypetstore.api.entity.LineItem;
import org.csu.mypetstore.api.entity.Order;
import org.csu.mypetstore.api.vo.CartVO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


public interface OrderService {
    public int getNextId(String name);

    public CommonResponse<Order> newOrder(Order order, CartVO cartVO);

    public CommonResponse<List<Order>> getOrders(String username);

    public CommonResponse<Order> getOrderById(String orderId, String username);

    public CommonResponse<List<LineItem>> getLineItemsByOrderId(String orderId, String username);
}
