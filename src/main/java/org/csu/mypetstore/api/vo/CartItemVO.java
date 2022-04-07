package org.csu.mypetstore.api.vo;

import org.csu.mypetstore.api.entity.Item;

import java.math.BigDecimal;

public class CartItemVO {
    private Item item;
    private BigDecimal total;
    private String itemId;
    private String productId;
    private String descn;
    private boolean inStock;
    private int quantity;
    private BigDecimal listPrice;
    private BigDecimal totalPrice;
    private String buyerName;
}
