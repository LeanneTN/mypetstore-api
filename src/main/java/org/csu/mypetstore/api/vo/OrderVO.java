package org.csu.mypetstore.api.vo;

import lombok.Data;
import org.csu.mypetstore.api.entity.LineItem;
import org.csu.mypetstore.api.entity.Order;

import java.util.List;

@Data
public class OrderVO {
    private Order order;
    private LineItem firstLineItem;
    private List<LineItem> lineItemList;
    private int len;
}
